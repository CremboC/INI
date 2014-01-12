package seprini.controllers;

import java.util.ArrayList;
import java.util.Random;

import seprini.data.Art;
import seprini.data.Config;
import seprini.data.Debug;
import seprini.data.GameDifficulty;
import seprini.data.GameState;
import seprini.models.Aircraft;
import seprini.models.Airspace;
import seprini.models.Exitpoint;
import seprini.models.Map;
import seprini.models.Waypoint;
import seprini.models.types.AircraftType;
import seprini.screens.EndScreen;
import seprini.screens.GameScreen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Buttons;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

public final class AircraftController extends InputListener implements
		Controller {

	Random rand = new Random();

	// aircraft and aircraft type lists
	private final ArrayList<AircraftType> aircraftTypeList = new ArrayList<AircraftType>();
	private final ArrayList<Aircraft> aircraftList = new ArrayList<Aircraft>();

	// waypoint lists
	private final ArrayList<Waypoint> permanentWaypointList = new ArrayList<Waypoint>();
	private final ArrayList<Waypoint> userWaypointList = new ArrayList<Waypoint>();
	private final ArrayList<Waypoint> entryList = new ArrayList<Waypoint>();
	private final ArrayList<Exitpoint> exitList = new ArrayList<Exitpoint>();

	private final int maxAircraft;
	private final int timeBetweenGenerations;
	private float time;
	private float lastGenerated;

	private final AircraftType defaultAircraft = new AircraftType();
	private Aircraft selectedAircraft;

	private final GameDifficulty difficulty;

	// ui related
	private final Airspace airspace;
	private SidebarController sidebar;
	private GameScreen screen;

	private int aircraftId = 0;

	public AircraftController(GameDifficulty diff, Airspace airspace,
			Table sidebar, GameScreen screen) {
		this.difficulty = diff;
		this.airspace = airspace;
		this.screen = screen;

		this.sidebar = new SidebarController(sidebar, this, screen);

		// insert code here to initialise variables (eg max no of aircraft) to
		// wanted value for that difficulty level.
		switch (difficulty) {
			case EASY :
				maxAircraft = 1;
				timeBetweenGenerations = 1;
				break;
			case MEDIUM :
				maxAircraft = 2;
				timeBetweenGenerations = 5;
				break;
			case HARD :
				maxAircraft = 10;
				timeBetweenGenerations = 2;
				break;
			default :
				maxAircraft = 1;
				timeBetweenGenerations = 1;
				break;
		}
	}

	public void init() {
		// add the background
		airspace.addActor(new Map());

		// add entry waypoints to entryList
		Waypoint e = new Waypoint(new Vector2(0, 0), true);
		entryList.add(e);

		Waypoint en = new Waypoint(new Vector2(0, 720), true);
		entryList.add(en);

		Waypoint ent = new Waypoint(new Vector2(0, 420), true);
		entryList.add(ent);

		Waypoint entr = new Waypoint(new Vector2(1080, 360), true);
		entryList.add(entr);

		Waypoint entry = new Waypoint(new Vector2(1080, 0), true);
		entryList.add(entry);

		// add exit waypoints to exitList
		Exitpoint f = new Exitpoint(new Vector2(1080, 720));
		exitList.add(f);

		Exitpoint fi = new Exitpoint(new Vector2(1080, 0));
		exitList.add(fi);

		Exitpoint fin = new Exitpoint(new Vector2(0, 420));
		exitList.add(fin);

		// add some waypoints
		createWaypoint(500, 200, true);
		createWaypoint(200, 600, true);
		createWaypoint(800, 250, true);
		createWaypoint(700, 500, true);

		// initialise aircraft types.
		defaultAircraft.setCoords(new Vector2(0, 0)).setActive(true)
				.setMaxClimbRate(0).setMaxSpeed(1).setMaxTurningSpeed(0.4f)
				.setRadius(10).setSeparationRadius(40)
				.setTexture(Art.getTextureRegion("aircraft"))
				.setVelocity(new Vector2(1, 1));

		// add aircraft types to airplaneTypes array.
		aircraftTypeList.add(defaultAircraft);

		sidebar.init();
	}

	/**
	 * Updates the aircraft positions. Generates a new aircraft and adds it to
	 * the stage. Collision Detection. Removes aircraft if inactive.
	 */
	public void update() {

		Aircraft planeI, planeJ;

		time += Gdx.graphics.getDeltaTime();

		// Updates aircraft in turn
		// Removes aircraft which are no longer active from aircraftList.
		// Manages collision detection.
		for (int i = 0; i < aircraftList.size(); i++) {
			// Update aircraft.
			(planeI = aircraftList.get(i)).act();
			planeI.isBreaching(false);

			// Collision Detection + Separation breach detection.
			for (int j = 0; j < aircraftList.size(); j++) {

				// Quite simply checks if distance between the centres of both
				// the aircraft <= the radius of aircraft i + radius of aircraft
				// j
				planeJ = aircraftList.get(j);

				if (!planeI.equals(planeJ)
				// Check difference in altitude.
						&& Math.abs(planeI.getHeight() - planeJ.getHeight()) < 100
						// Check difference in horizontal 2d plane.
						&& planeI.getCentreCoords().dst(
								planeJ.getCentreCoords()) < planeI.getRadius()
								+ planeJ.getRadius()) {
					collisionHasOccured(planeI, planeJ);
				}

				// Checking for breach of separation.
				if (!planeI.equals(planeJ)
						// Check difference in altitude.
						&& Math.abs(planeI.getHeight() - planeJ.getHeight()) < planeI
								.getSeparationRadius()
						// Check difference in horizontal 2d plane.
						&& planeI.getCentreCoords().dst(
								planeJ.getCentreCoords()) < planeI
								.getSeparationRadius()) {
					planeI.isBreaching(true);
					planeJ.isBreaching(true);

					separationRulesBreached(planeI, planeJ);
				}
			}

			// Remove inactive aircraft.
			if (!planeI.isActive()) {
				removeAircraft(i);
			}

		}

		final Aircraft generatedAircraft = generateAircraft();

		// if the newly generated aircraft is not null (ie checking one was
		// generated), add it as an actor to the stage
		if (generatedAircraft != null) {

			// makes the aircraft clickable. Once clicked it is set as the
			// selected aircraft.
			generatedAircraft.addListener(new ClickListener() {

				@Override
				public void clicked(InputEvent event, float x, float y) {
					selectAircraft(generatedAircraft);
				}

			});

			generatedAircraft.toFront();
			airspace.addActor(generatedAircraft);
		}

		sidebar.update();

	}

	private void collisionHasOccured(Aircraft a, Aircraft b) {
		// End the game
		// TODO remove debug code, put in game ending code
		screen.setScreen(new EndScreen());
	}

	private void separationRulesBreached(Aircraft a, Aircraft b) {
		// for scoring mechanisms, if applicable
		// TODO remove debug code.
	}

	/**
	 * Generates aircraft of random type with 'random' flight plan.
	 * <p>
	 * Checks if maximum number of aircraft is not exceeded. If it isn't, a new
	 * aircraft is generated with the arguments randomAircraftType() and
	 * generateFlightPlan().
	 */
	private Aircraft generateAircraft() {
		if (aircraftList.size() == maxAircraft)
			return null;

		// difference between aircraft generated - 5 seconds, needs to be
		// dependable on difficulty
		if (time - lastGenerated < timeBetweenGenerations)
			return null;

		Aircraft newAircraft = new Aircraft(randomAircraftType(),
				generateFlightPlan(), aircraftId++);

		aircraftList.add(newAircraft);

		lastGenerated = time;

		return newAircraft;
	}

	private ArrayList<Waypoint> generateFlightPlan() {

		ArrayList<Waypoint> flightPlan = new ArrayList<Waypoint>();
		Waypoint entryWaypoint = setStartpoint();
		Waypoint lastWaypoint = setEndpoint(entryWaypoint);
		flightPlan.add(entryWaypoint);
		flightPlan.add(lastWaypoint);
		return flightPlan;
		// return FlightPlanGenerator(flightPlan, currentWaypoint,
		// lastWaypoint);
	}

	private ArrayList<Waypoint> FlightPlanGenerator(
			ArrayList<Waypoint> flightPlan, Waypoint currentWaypoint,
			Waypoint lastWaypoint) {

		if (currentWaypoint.equals(lastWaypoint)) {
			return flightPlan;
		}

		else {

			Vector2 vectorFromCurrentToLast = new Vector2(lastWaypoint
					.getCoords().sub(currentWaypoint.getCoords()));

			ArrayList<Waypoint> waypointSelectionList = permanentWaypointList;
			waypointSelectionList.addAll(exitList);

			for (Waypoint waypoint : waypointSelectionList) {

			}

		}

		return flightPlan;
	}

	/**
	 * Selects random waypoint from entrypointList.
	 * 
	 * @return Waypoint
	 */
	private Waypoint setStartpoint() {
		return entryList.get(rand.nextInt(entryList.size()));
	}

	/**
	 * Selects random exitpoint from exitpointList.
	 * 
	 * @return Exitpoint
	 */
	private Exitpoint setEndpoint(Waypoint entryWaypoint) {
		Exitpoint chosenExitPoint = exitList.get(rand.nextInt(exitList.size()));
		if (chosenExitPoint.getCoords().dst(entryWaypoint.getCoords()) < 500) {
			chosenExitPoint = setEndpoint(entryWaypoint);
		}

		return chosenExitPoint;
	}

	/**
	 * Selects random aircraft type from aircraftTypeList.
	 * 
	 * @return AircraftType
	 */
	private AircraftType randomAircraftType() {
		return aircraftTypeList.get(rand.nextInt(aircraftTypeList.size()));
	}

	/**
	 * Removes aircraft from aircraftList at index i.
	 * 
	 * @param i
	 */
	private void removeAircraft(int i) {
		Aircraft aircraft = aircraftList.get(i);

		// removes the aircraft from the list of aircrafts on screen
		aircraftList.remove(i);

		// removes the aircraft from the stage
		aircraft.remove();

		return;
	}

	/**
	 * Creates a new waypoint.
	 * <p>
	 * Creates a new user waypoint when the user left-clicks within the airspace
	 * window.
	 * 
	 * Also is convinience method for generated permanent waypoints
	 * 
	 * @param x
	 * @param y
	 * @param permanent
	 */
	private boolean createWaypoint(float x, float y, boolean permanent) {
		
		Debug.msg("Creating waypoint at: " + x + ":" + y);

		if (userWaypointList.size() == Config.USER_WAYPOINT_LIMIT && !permanent)
			return false;

		Debug.msg("Waypoint at: " + x + ":" + y + " created");

		final Waypoint waypoint = new Waypoint(x, y, permanent);

		// add it to the correct list according to whether it is user created or
		// not
		if (permanent)
			permanentWaypointList.add(waypoint);
		else
			userWaypointList.add(waypoint);

		airspace.addActor(waypoint);

		// if it's permanent it doesn't need a listener, return;
		if (permanent)
			return true;

		waypoint.addListener(new ClickListener() {

			/**
			 * Removes a user waypoint if a user waypoint is right-clicked.
			 * Alternatively, should call redirection method.
			 */
			@Override
			public boolean touchDown(InputEvent event, float x, float y,
					int pointer, int button) {

				if (button == Buttons.RIGHT) {
					userWaypointList.remove(waypoint);
					airspace.removeActor(waypoint);
				} else if (button == Buttons.RIGHT
						&& (sidebar.isAllowRedirection() == true)) {
					redirectAircraft(waypoint);
				}

				return true;
			}
		});

		return true;
	}

	/**
	 * Selects an aircraft.
	 * 
	 * @param aircraft
	 */
	private void selectAircraft(Aircraft aircraft) {
		// make sure old selected aircraft is no longer selected in its own
		// object
		if (selectedAircraft != null)
			selectedAircraft.selected(false);

		// set new selected aircraft
		selectedAircraft = aircraft;

		// make new aircraft know it's selected
		selectedAircraft.selected(true);
	}

	/**
	 * Redirects aircraft to another waypoint.
	 * 
	 * @param object
	 */
	private void redirectAircraft(Waypoint waypoint) {
		// TODO if an aircraft is selected, if the user right-clicks on a
		// waypoint redirect the aircraft to that waypoint:
		// Call selected aircrafts insertWaypoint() method.
		getSelectedAircraft().insertWaypoint(waypoint);
	}

	public Aircraft getSelectedAircraft() {
		return selectedAircraft;
	}

	@Override
	public boolean touchDown(InputEvent event, float x, float y, int pointer,
			int button) {
		if (button == Buttons.LEFT && sidebar.allowNewWaypoints()) {
			createWaypoint(x, y, false);
		}

		return false;
	}

	@Override
	public boolean keyDown(InputEvent event, int keycode) {
		if (keycode == Keys.SPACE)
			GameState.paused = (GameState.paused) ? false : true;

		if (keycode == Keys.ESCAPE)
			screen.setScreen(new EndScreen());

		if (selectedAircraft != null) {

			if (keycode == Keys.LEFT)
				selectedAircraft.turnLeft();

			if (keycode == Keys.RIGHT)
				selectedAircraft.turnRight();

		}

		return false;
	}

}

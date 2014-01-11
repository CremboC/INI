package seprini.controllers;

import java.util.ArrayList;
import java.util.Random;

import seprini.data.Art;
import seprini.data.GameDifficulty;
import seprini.data.GameState;
import seprini.models.Aircraft;
import seprini.models.Airspace;
import seprini.models.Exitpoint;
import seprini.models.Map;
import seprini.models.Waypoint;
import seprini.models.types.AircraftType;

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

	private final ArrayList<AircraftType> aircraftTypeList = new ArrayList<AircraftType>();
	private final ArrayList<Aircraft> aircraftList = new ArrayList<Aircraft>();

	private final ArrayList<Waypoint> permanentWaypointList = new ArrayList<Waypoint>();
	private final ArrayList<Waypoint> userWaypointList = new ArrayList<Waypoint>();
	private final ArrayList<Waypoint> entryList = new ArrayList<Waypoint>();
	private final ArrayList<Exitpoint> exitList = new ArrayList<Exitpoint>();

	private final int maxAircraft = 5;

	private float time;
	private float lastGenerated;

	private final AircraftType defaultAircraft = new AircraftType();
	private Aircraft selectedAircraft;

	private final GameDifficulty difficulty;

	private final Airspace airspace;

	private SidebarController sidebar;

	public AircraftController(GameDifficulty diff, Airspace airspace,
			Table sidebar) {
		this.difficulty = diff;
		this.airspace = airspace;

		this.sidebar = new SidebarController(sidebar, this);
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

		// add exitpoint to waypoint list, FIXME
		permanentWaypointList.add(f);
		permanentWaypointList.add(fi);
		permanentWaypointList.add(fin);

		// initialise aircraft types.
		defaultAircraft.setCoords(new Vector2(0, 0)).setActive(true)
				.setMaxClimbRate(0).setMaxSpeed(1).setMaxTurningSpeed(0.4f)
				.setRadius(0).setSeparationRadius(0)
				.setTexture(Art.getTextureRegion("aircraft"))
				.setVelocity(new Vector2(1, 1));

		// add aircraft types to airplaneTypes array.
		aircraftTypeList.add(defaultAircraft);

		sidebar.init();
	}

	/**
	 * Updates the aircraft positions, generates a new aircraft and adds it to
	 * the stage
	 * 
	 */
	public void update() {

		time += Gdx.graphics.getDeltaTime();

		// removes aircraft which are no longer active
		for (int i = 0; i < aircraftList.size(); i++) {
			aircraftList.get(i).act();
			if (!aircraftList.get(i).isActive()) {
				removeAircraft(i);
			}
		}

		final Aircraft generatedAircraft = generateAircraft();

		// if the newly generated aircraft is not null (as in, it indeed
		// generated one), add it as an actor to the stage
		if (generatedAircraft != null) {

			// makes the aircraft clickable, once clicked, it is set as the
			// selected aircraft
			generatedAircraft.addListener(new ClickListener() {

				@Override
				public void clicked(InputEvent event, float x, float y) {
					System.out.println("Clicked: x: " + x + " y: " + y);
					selectAircraft(generatedAircraft);
				}

			});

			generatedAircraft.toFront();
			airspace.addActor(generatedAircraft);
		}

		sidebar.update();
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
		if (time - lastGenerated < 5)
			return null;

		Aircraft newAircraft = new Aircraft(randomAircraftType(),
				generateFlightPlan());

		aircraftList.add(newAircraft);
		
		lastGenerated = time;

		return newAircraft;
	}

	private ArrayList<Waypoint> generateFlightPlan() {

		ArrayList<Waypoint> flightPlan = new ArrayList<Waypoint>();

		flightPlan.add(setStartpoint());
		flightPlan.add(setEndpoint());

		Waypoint currentWaypoint = flightPlan.get(0);
		Waypoint lastWaypoint = flightPlan.get(flightPlan.size() - 1);

		return recurseFlightPlanGen(flightPlan, currentWaypoint, lastWaypoint);
	}

	/**
	 * Recursive function to generate sensible flightplan. <br>
	 * 1. Generates flightplan between currentWaypoint and exitpoint. Initially,
	 * currentWaypoint will be the entryWaypoint. <br>
	 * 2. Calculates angle between currentWaypoint and exitWaypoint, then
	 * chooses a waypoint within 0.25 radians of that angle. <br>
	 * 3. Adds that waypoint to the flightplan, between existing waypoints and
	 * exitWaypoint. <br>
	 * 4. Recurse, using chosen waypoint as currentWaypoint.
	 * 
	 * 
	 * @param flightPlan
	 * @param currentWaypoint
	 * @param lastWaypoint
	 * @return ArrayList<Waypoint>
	 */
	private ArrayList<Waypoint> recurseFlightPlanGen(
			ArrayList<Waypoint> flightPlan, Waypoint currentWaypoint,
			Waypoint lastWaypoint) {

		// Base case
		if (currentWaypoint.equals(lastWaypoint)) {

			return flightPlan;

		} else {
			// I get the feeling the vectors below should be normalised first; I
			// suppose we'll find out the hard way.

			Vector2 vectorFromCurrentToLast = lastWaypoint.getCoords().cpy()
					.sub(currentWaypoint.getCoords());

			Waypoint chosenWaypoint = null;

			ArrayList<Waypoint> tempList = permanentWaypointList;
			tempList.addAll(userWaypointList);

			for (Waypoint waypoint : tempList) {

				Vector2 vectorFromCurrentToPotential = waypoint.getCoords()
						.cpy().sub(currentWaypoint.getCoords());

				// The following chooses the first waypoint that is found to be
				// within the 'cone' of selectable waypoints. Currently this
				// cone is set to 0.25 radians on either side, so a total size
				// of 0.5 (aka 90 degrees).

				if (!waypoint.equals(flightPlan.get(flightPlan.size() - 2))
						&& vectorFromCurrentToLast.angle()
								- vectorFromCurrentToPotential.angle() < 50
						&& vectorFromCurrentToPotential.angle()
								- vectorFromCurrentToLast.angle() < 50) {

					flightPlan.add(flightPlan.size() - 1, waypoint);
					chosenWaypoint = waypoint;

					break;
				}
			}

			if (chosenWaypoint == null) {
				chosenWaypoint = lastWaypoint;
			}

			return recurseFlightPlanGen(flightPlan, chosenWaypoint,
					lastWaypoint);
		}
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
	private Exitpoint setEndpoint() {
		return exitList.get(rand.nextInt(exitList.size()));
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
	private void createWaypoint(float x, float y, boolean permanent) {

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
			return;

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

		return;
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

		System.out.println(x + " " + y);
		if (button == Buttons.LEFT && sidebar.allowNewWaypoints()) {
			createWaypoint(x, y, false);
		}

		return false;
	}

	@Override
	public boolean keyDown(InputEvent event, int keycode) {
		if (keycode == Keys.SPACE) {
			GameState.paused = (GameState.paused) ? false : true;
		}

		return false;
	}

}

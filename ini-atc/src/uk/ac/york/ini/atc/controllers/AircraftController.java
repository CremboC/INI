package uk.ac.york.ini.atc.controllers;

import java.util.ArrayList;
import java.util.Random;

import uk.ac.york.ini.atc.data.GameDifficulty;
import uk.ac.york.ini.atc.handlers.Art;
import uk.ac.york.ini.atc.models.Aircraft;
import uk.ac.york.ini.atc.models.Exitpoint;
import uk.ac.york.ini.atc.models.Waypoint;
import uk.ac.york.ini.atc.screens.GameScreen.InputHandler;

import com.badlogic.gdx.Input.Buttons;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

public class AircraftController {

	Random rand = new Random();

	private final ArrayList<AircraftType> aircraftTypeList = new ArrayList<AircraftType>();
	private final ArrayList<Aircraft> aircraftList = new ArrayList<Aircraft>();

	private final ArrayList<Waypoint> permanentWaypointList = new ArrayList<Waypoint>();
	private final ArrayList<Waypoint> userWaypointList = new ArrayList<Waypoint>();
	private final ArrayList<Waypoint> entryList = new ArrayList<Waypoint>();
	private final ArrayList<Exitpoint> exitList = new ArrayList<Exitpoint>();

	private final int maxAircraft = 1;
	private final AircraftType defaultAircraft = new AircraftType();
	private Aircraft selectedAircraft;

	private final GameDifficulty difficulty;

	private final Stage stage;
	private final InputHandler input;

	public AircraftController(GameDifficulty diff, Stage stage,
			InputHandler input) {

		this.difficulty = diff;
		this.stage = stage;
		this.input = input;

		// add entry waypoints to entryList
		Waypoint e = new Waypoint(new Vector2(0, 0));
		entryList.add(e);

		// add exit waypoints to exitList
		Exitpoint f = new Exitpoint(new Vector2(1080, 720));
		exitList.add(f);

		// Waypoint a = new Waypoint(new Vector2(200, 300));
		// permanentWaypointList.add(a);
		// stage.addActor(a);
		//
		// Waypoint b = new Waypoint(new Vector2(500, 300));
		// permanentWaypointList.add(b);
		// stage.addActor(b);

		Waypoint g = new Waypoint(new Vector2(500, 200));
		permanentWaypointList.add(g);
		stage.addActor(g);

		Waypoint h = new Waypoint(new Vector2(800, 250));
		permanentWaypointList.add(h);
		stage.addActor(h);

		Waypoint i = new Waypoint(new Vector2(700, 500));
		permanentWaypointList.add(i);
		stage.addActor(i);
		permanentWaypointList.add(f);

		// initialise aircraft types.
		defaultAircraft.setCoords(new Vector3(0, 0, 0)).setActive(true)
				.setMaxClimbRate(0).setMaxSpeed(2).setMaxTurningSpeed(0)
				.setRadius(0).setSeparationRadius(0)
				.setTexture(Art.getTextureRegion("aircraft"))
				.setVelocity(new Vector3(1, 1, 0));

		// add aircraft types to airplaneTypes array.
		aircraftTypeList.add(defaultAircraft);

		// initialise list of way points
	}

	/**
	 * Updates the aircraft positions, generates a new aircraft and adds it to
	 * the stage
	 * 
	 */
	public void update() {

		// removes aircraft which are no longer active
		for (int i = 0; i < aircraftList.size(); i++) {
			aircraftList.get(i).act();
			if (!aircraftList.get(i).isActive()) {
				removeAircraft(i);
			}
		}

		// handles waypoint creation
		// createWaypoint();

		final Aircraft generatedAircraft = generateAircraft();

		// if the newly generated aircraft is not null (as in, it indeed
		// generated one), add it as an actor to the stage
		if (generatedAircraft != null) {

			// makes the aircraft clickable, once clicked, it is set as the
			// selected aircraft
			generatedAircraft.addListener(new ClickListener() {

				public void clicked(InputEvent event, float x, float y) {
					selectedAircraft = generatedAircraft;
				}

			});

			stage.addActor(generatedAircraft);
		}
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

		Aircraft newAircraft = new Aircraft(randomAircraftType(),
				generateFlightPlan());

		aircraftList.add(newAircraft);

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
		if (currentWaypoint == lastWaypoint) {

			return flightPlan;

		} else {
			// I get the feeling the vectors below should be normalised first; I
			// suppose we'll find out the hard way.

			Vector2 vectorFromCurrentToLast = lastWaypoint.getCoords().cpy()
					.sub(currentWaypoint.getCoords());

			Waypoint chosenWaypoint = null;

			for (Waypoint waypoint : permanentWaypointList) {

				Vector2 vectorFromCurrentToPotential = waypoint.getCoords()
						.cpy().sub(currentWaypoint.getCoords());

				// The following chooses the first waypoint that is found to be
				// within the 'cone' of selectable waypoints. Currently this
				// cone is set to 0.25 radians on either side, so a total size
				// of 0.5 (aka 90 degrees).

				if (vectorFromCurrentToLast.angle()
						- vectorFromCurrentToPotential.angle() < 40
						&& vectorFromCurrentToPotential.angle()
								- vectorFromCurrentToLast.angle() < 40) {

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
	 */
	private void createWaypoint() {
		// TODO when the user left clicks inside the game window and no waypoint
		// or aircraft exists there, create a waypoint at that location.
		// This check should be done before this method is called.
		Integer buttonPressed = input.getButtonPressed();

		if (buttonPressed == null)
			return;

		if (buttonPressed == Buttons.LEFT) {
			Waypoint userWaypoint = new Waypoint(input.getMousePosition());
			userWaypointList.add(userWaypoint);
			stage.addActor(userWaypoint);
		}

	}

	/**
	 * Removes a user-created waypoint.
	 * <p>
	 * Removes a user waypoint if a user waypoint is right-clicked.
	 */
	private void removeWaypoint() {
		// TODO when the user right clicks on a user-made waypoint, remove that
		// waypoint from the userWaypointList
		int buttonPressed = input.getButtonPressed();
		if (buttonPressed == Buttons.RIGHT) {
			for (Waypoint userWaypoint : userWaypointList) {
				if (input.getMousePosition().x == userWaypoint.getCoords().x) {
					if (input.getMousePosition().y == userWaypoint.getCoords().y) {
						userWaypointList.remove(userWaypoint);
					}
				}
			}
		}
	}

	/**
	 * Selects an aircraft.
	 */
	private void selectAircraft() {
		// TODO when the user left-clicks on an aircraft, select it.
	}

	/**
	 * Redirects aircraft to another waypoint.
	 */
	private void redirectAircraft() {
		// TODO if an aircraft is selected, if the user right-clicks on a
		// waypoint redirect the aircraft to that waypoint:
		// Call selected aircrafts insertWaypoint() method.
	}
}

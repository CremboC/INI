package uk.ac.york.ini.atc.controllers;

import java.util.ArrayList;
import java.util.Random;

import uk.ac.york.ini.atc.data.GameDifficulty;
import uk.ac.york.ini.atc.handlers.Art;
import uk.ac.york.ini.atc.models.Aircraft;
import uk.ac.york.ini.atc.models.Exitpoint;
import uk.ac.york.ini.atc.models.Waypoint;

import com.badlogic.gdx.scenes.scene2d.Stage;

public class AircraftController {

	Random rand = new Random();
	private final ArrayList<AircraftType> aircraftTypeList;
	private final ArrayList<Aircraft> aircraftList;
	private ArrayList<Waypoint> permanentWaypointList;
	private ArrayList<Waypoint> userWaypointList;
	private ArrayList<Waypoint> entryList;
	private ArrayList<Exitpoint> exitList;
	private final int maxAircraft = 0;
	private final AircraftType defaultAircraft = new AircraftType();

	private final GameDifficulty difficulty;

	private final Stage stage;

	public AircraftController(GameDifficulty diff, Stage stage) {

		this.difficulty = diff;
		this.stage = stage;

		aircraftTypeList = new ArrayList<AircraftType>();
		aircraftList = new ArrayList<Aircraft>();

		// initialise aircraft types.
		defaultAircraft.setCoords(null).setActive(true).setMaxClimbRate(0)
				.setMaxSpeed(0).setMaxTurningSpeed(0).setRadius(0)
				.setSeparationRadius(0)
				.setTexture(Art.getTextureRegion("aircraft")).setVelocity(null);

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
		for (int i = 0; i < aircraftList.size(); i++) {
			if (!aircraftList.get(i).isActive()) {
				removeAircraft(i);
			}
		}

		Aircraft generatedAircraft = generateAircraft();

		System.out.println(generatedAircraft);

		// if the newly generated aircraft is not null (as in, it indeed
		// generated one), add it as an actor to the stage
		if (generatedAircraft != null) {
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

	@SuppressWarnings("null")
	private ArrayList<Waypoint> generateFlightPlan() {
		ArrayList<Waypoint> flightPlan = null;
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
		}
		// I get the feeling the vectors below should be normalised first; I
		// suppose we'll find out the hard way.
		else {
			float angleFromCurrentToLast = currentWaypoint.getCoords().dot(
					lastWaypoint.getCoords());
			Waypoint chosenWaypoint = null;
			for (Waypoint waypoint : permanentWaypointList) {
				float angleFromCurrentToPotential = currentWaypoint.getCoords()
						.dot(waypoint.getCoords());
				// The following chooses the first waypoint that is found to be
				// within the 'cone' of selectable waypoints. Currently this
				// cone is set to 0.25 radians on either side, so a total size
				// of 0.5 (aka 90 degrees).
				if (angleFromCurrentToLast - angleFromCurrentToPotential < 0.25
						|| angleFromCurrentToPotential - angleFromCurrentToLast < 0.25) {
					flightPlan.add(flightPlan.size() - 1, waypoint);
					chosenWaypoint = waypoint;
					continue;
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
		return entryList.get(rand.nextInt(entryList.size() - 1));
	}

	/**
	 * Selects random exitpoint from exitpointList.
	 * 
	 * @return Exitpoint
	 */
	private Exitpoint setEndpoint() {
		return exitList.get(rand.nextInt(exitList.size() - 1));
	}

	/**
	 * Selects random aircraft type from aircraftTypeList.
	 * 
	 * @return AircraftType
	 */
	private AircraftType randomAircraftType() {
		return aircraftTypeList.get(rand.nextInt(aircraftTypeList.size() - 1));
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
	}

	/**
	 * Removes a user-created waypoint.
	 * <p>
	 * Removes a user waypoint if a user waypoint is right-clicked.
	 */
	private void removeWaypoint() {
		// TODO when the user right clicks on a user-made waypoint, remove that
		// waypoint from the userWaypointList
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
package uk.ac.york.ini.atc.controllers;

import java.util.ArrayList;
import java.util.Random;

import uk.ac.york.ini.atc.handlers.Input;
import uk.ac.york.ini.atc.models.Aircraft;
import uk.ac.york.ini.atc.models.Exitpoint;
import uk.ac.york.ini.atc.models.Waypoint;

public class AircraftController {
	Random rand = new Random();
	private ArrayList<Aircraft> airplaneTypes;
	private ArrayList<Aircraft> aircraftList;
	private ArrayList<Waypoint> permanentWaypointList;
	private ArrayList<Waypoint> userWaypointList;
	private ArrayList<Waypoint> entryList;
	private ArrayList<Exitpoint> exitList;
	private int maxAircraft;

	public AircraftController() {

		// initiate airplaneTypes array, will consist of all the available types
		// initialise list of way points
	}

	public void update(Input input) {
		for (int i = 0; i < aircraftList.size(); i++) {
			if (aircraftList.get(i).isActive()) {
				aircraftList.get(i).update(input);
			} else {
				removeAircraft(i);
			}
		}
		generateAircraft();
	}

	private void generateAircraft() {
		if (aircraftList.size() == maxAircraft)
			return;
		else {
			Aircraft e = null;
			aircraftList.add(e);
		}
	}

	private ArrayList generateFlightPlan() {
		ArrayList<Waypoint> flightPlan = null;
		flightPlan.add(setStartpoint());
		flightPlan.add(setEndpoint());
		Waypoint currentWaypoint = flightPlan.get(0);
		Waypoint lastWaypoint = flightPlan.get(flightPlan.size() - 1);
		return recurseFlightPlanGen(flightPlan, currentWaypoint, lastWaypoint);
	}

	private ArrayList<Waypoint> recurseFlightPlanGen(
			ArrayList<Waypoint> flightPlan, Waypoint currentWaypoint,
			Waypoint lastWaypoint) {
		if (currentWaypoint == lastWaypoint) {
			return flightPlan;
		}
		// I get the feeling the vectors below should be normalised first; I
		// suppose we'll find out the hard way.
		else {
			float angleFromCurrentToLast = currentWaypoint.getCoords().dot(
					lastWaypoint.getCoords());
			Waypoint nextWaypoint = null;
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
					nextWaypoint = waypoint;
					continue;
				}
			}
			if (nextWaypoint == null) {
				nextWaypoint = lastWaypoint;
			}
			return recurseFlightPlanGen(flightPlan, nextWaypoint, lastWaypoint);
		}
	}

	private Waypoint setStartpoint() {
		return entryList.get(rand.nextInt(entryList.size() - 1));
	}

	private Exitpoint setEndpoint() {
		return exitList.get(rand.nextInt(exitList.size() - 1));
	}

	private void removeAircraft(int i) {
		aircraftList.remove(i);
		return;
	}

}

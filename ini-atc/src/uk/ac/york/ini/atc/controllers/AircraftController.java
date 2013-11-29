package uk.ac.york.ini.atc.controllers;

import java.util.ArrayList;
import java.util.Random;

import uk.ac.york.ini.atc.models.Aircraft;
import uk.ac.york.ini.atc.models.Exitpoint;
import uk.ac.york.ini.atc.models.Waypoint;

import com.badlogic.gdx.Input;

public class AircraftController {
	Random rand = new Random();
	private ArrayList<Aircraft> airplaneTypes;
	private ArrayList<Aircraft> aircraftList;
	private ArrayList<Waypoint> waypointList;
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
				aircraftList.get(i).update();
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
		flightPlan.add(getStartpoint());
		flightPlan.add(getEndpoint());

		Waypoint currentWaypoint = flightPlan.get(0);
		return recurseFlightPlanGen(flightPlan, currentWaypoint);
	}

	private ArrayList<Waypoint> recurseFlightPlanGen(
			ArrayList<Waypoint> flightPlan, Waypoint currentWaypoint) {
		if (currentWaypoint.getCoords() == flightPlan
				.get(flightPlan.size() - 1).getCoords()) {
			return flightPlan;
		} else {
			return recurseFlightPlanGen(flightPlan, currentWaypoint);
		}
	}

	private Waypoint getStartpoint() {
		return entryList.get(rand.nextInt(entryList.size() - 1));
	}

	private Exitpoint getEndpoint() {
		return exitList.get(rand.nextInt(exitList.size() - 1));
	}

	private void removeAircraft(int i) {
		aircraftList.remove(i);
		return;
	}

}

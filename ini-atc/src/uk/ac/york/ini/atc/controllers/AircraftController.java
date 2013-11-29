package uk.ac.york.ini.atc.controllers;

import java.util.ArrayList;

import uk.ac.york.ini.atc.models.Aircraft;
import uk.ac.york.ini.atc.models.Exitpoint;
import uk.ac.york.ini.atc.models.Waypoint;

import com.badlogic.gdx.Input;

public class AircraftController {

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
		return aircraftList;

	}

	private void removeAircraft(int i) {
		aircraftList.remove(i);
		return;
	}

}

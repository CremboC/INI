package uk.ac.york.ini.atc.controllers;

import java.util.ArrayList;

import uk.ac.york.ini.atc.models.Aeroplane;
import uk.ac.york.ini.atc.models.Exitpoint;
import uk.ac.york.ini.atc.models.Waypoint;

public class AircraftController {

	private ArrayList<Aeroplane> airplaneTypes;
	private ArrayList<Aeroplane> aircraftList;
	private ArrayList<Waypoint> waypointList;
	private ArrayList<Waypoint> entryList;
	private ArrayList<Exitpoint> exitList;
	private int maxAircraft;

	public AircraftController() {

		// initiate airplaneTypes array, will consist of all the available types
		airplaneTypes = new ArrayList<Aeroplane>();
	}

	public ArrayList<Aeroplane> getAirplaneTypes() {
		return airplaneTypes;
	}

}

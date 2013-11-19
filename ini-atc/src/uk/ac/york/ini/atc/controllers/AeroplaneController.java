package uk.ac.york.ini.atc.controllers;

import java.util.ArrayList;

import uk.ac.york.ini.atc.models.Aeroplane;

public class AeroplaneController {

    public ArrayList<Aeroplane> airplaneTypes;
    public ArrayList<Aeroplane> airplanesOnScreen;

    public AeroplaneController() {

	// initiate airplaneTypes array, will consist of all the available types
	airplaneTypes = new ArrayList<Aeroplane>();

	// initiate airplanes on screen array, which consists of all the
	// airplanes that are rendered atm
	airplanesOnScreen = new ArrayList<Aeroplane>();
    }

    /**
     * add airplane types
     * 
     * @param Aeroplane
     *            type
     */
    public void addType(Aeroplane type) {
	airplaneTypes.add(type);
    }

    /**
     * Generates a random aeroplane type
     * 
     * @return Aeroplane
     */
    // public Aeroplane pickRandomType() {
    // Aeroplane copy;
    // copy = airplaneTypes.get(ThreadLocalRandom.current().nextInt(
    // airplaneTypes.size()));
    // return copy;
    // }

    public ArrayList<Aeroplane> getAirplaneTypes() {
	return airplaneTypes;
    }

}

package uk.ac.york.ini.atc.models;

import com.badlogic.gdx.math.Vector3;

public class Waypoint {

    public Waypoint() {
	// TODO Auto-generated constructor stub
    }

    protected Vector3 coords;

    public Vector3 getCoords() {
	return coords;
    }

    public void setCoords(Vector3 coords) {
	this.coords = coords;
    }

    public float getAltitude() {
	return coords.z;
    }

    public void setAltitude(float altitude) {
	this.coords.z = altitude;
    }

}

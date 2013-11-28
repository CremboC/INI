package uk.ac.york.ini.atc.models;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;

public class Waypoint {

	protected Vector3 coords;

	public Vector3 getCoords() {
		return coords;
	}

	public void setXCoord(float x) {
		this.coords.x = x;
	}

	public void setYCoord(float y) {
		this.coords.y = y;
	}

	public void setCoords(Vector2 newCoords) {
		this.coords.x = newCoords.x;
		this.coords.y = newCoords.y;
	}

	public float getAltitude() {
		return coords.z;
	}

	public void setAltitude(float altitude) {
		this.coords.z = altitude;
	}

}

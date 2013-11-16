package uk.ac.york.ini.atc.models;

import uk.ac.york.ini.atc.types.Vector2d;

/**
 * @author Crembo
 * 
 * Every airplane can have multiple waypoints, 
 * they define how it move around the airspace.
 *
 */
public class Waypoint {
	
	protected Vector2d coords;
	protected int altitude;
	
	public Vector2d getCoords() {
		return coords;
	}
	public void setCoords(Vector2d coords) {
		this.coords = coords;
	}
	public int getAltitude() {
		return altitude;
	}
	public void setAltitude(int altitude) {
		this.altitude = altitude;
	}
	
	

}

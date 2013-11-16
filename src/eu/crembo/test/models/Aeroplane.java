package eu.crembo.test.models;

import java.util.ArrayList;

import eu.crembo.test.types.Vector2d;

/**
 * @author Crembo
 *
 */
public class Aeroplane {
	
	protected Vector2d coords;
	protected int altitude;
	private int speed;
	protected int radius;
	
	protected ArrayList<Waypoint> waypoints;
	
	protected String texture;


	/**
	 * Regular getter for speed
	 * @return int speed
	 */
	public int getSpeed() {
		return speed;
	}

	/**
	 * Set the speed
	 * @param speed int
	 * 
	 */
	public void setSpeed(int speed) {
		this.speed = speed;
	}
	

}

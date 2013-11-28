package uk.ac.york.ini.atc.models;

import java.util.ArrayList;

import com.badlogic.gdx.math.Vector2;

public class Aeroplane {

	protected Vector2 coords;
	protected int altitude;
	private int speed; // vectorise
	protected int radius;

	protected ArrayList<Waypoint> waypoints;

	protected String texture;

	public Aeroplane() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * Regular getter for speed
	 * 
	 * @return int speed
	 */
	public int getSpeed() {
		return speed;
	}

	/**
	 * Set the speed More detail on this method
	 * 
	 * @param speed
	 *            int
	 * 
	 */
	public void setSpeed(int speed) {
		this.speed = speed;
	}

}

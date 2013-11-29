package uk.ac.york.ini.atc.models;

import java.util.ArrayList;

import uk.ac.york.ini.atc.handlers.Input;

import com.badlogic.gdx.math.Vector2;

public class Aircraft extends Entity {

	protected Vector2 coords;
	protected int altitude;
	private int speed; // vectorise
	protected int radius;

	protected ArrayList<Waypoint> waypoints;

	protected String texture;

	public Aircraft() {
		// TODO Auto-generated constructor stub
	}

	public void update(Input input) {
		// TODO implement method
	}

	public void draw() {
		// TODO implement method
	}

	public boolean isActive() {
		// TODO implement method
		return false;
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

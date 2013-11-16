package eu.crembo.test.models;

import java.util.ArrayList;

import eu.crembo.test.types.Vector2d;

public class Aeroplane {
	
	protected Vector2d coords;
	protected int altitude;
	private int speed;
	protected int radius;
	
	protected ArrayList<Waypoint> waypoints;
	
	protected String texture;

	public int getSpeed() {
		return speed;
	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}
	

}

package uk.ac.york.ini.atc.models;

import java.util.ArrayList;

import uk.ac.york.ini.atc.controllers.AircraftType;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector3;

import java.util.Calendar;

public class Aircraft extends Entity {

	protected Vector3 coords;
	protected Vector3 velocity;
	protected int radius;
	protected int separationRadius;
	protected String texture;
	protected int maxTurningRate;
	protected int maxClimbRate;
	protected int maxSpeed;
	protected boolean isActive;
	protected boolean turningFlag; // May not be used
	protected ArrayList<Waypoint> waypoints;
	protected int sepRulesBreachCounter;
	protected int lastTimeTurned;

	public Aircraft(AircraftType aircraftType, ArrayList<Waypoint> flightPlan) {
		// TODO Auto-generated constructor stub
		// Unsure what to implement here.
	}

	public void act() {
		this.updateCoords();
		this.calculateVelocity();
		this.isActive();
	}

	public void draw() {
		// TODO implement method
	}

	public void calculateVelocity() {

		// Object nextWaypointCoords = waypoints.get(0).getCoords();
		Vector3 nextWaypointNewCoords = new Vector3();

		// converts waypoints coordinates into 3d vectors to enabled
		// subtraction.
		nextWaypointNewCoords.set(waypoints.get(0).getCoords().x, waypoints
				.get(0).getCoords().y, 2 * coords.z);

		velocity = nextWaypointNewCoords.sub(coords);

		isAtNextWaypoint(velocity);

		checkSpeed();
	}

	public void insertWaypoint(Waypoint newWaypoint) {
		waypoints.add(0, newWaypoint);
	}

	public void turnRight() {
		Vector3 zAxis = new Vector3();
		zAxis.set(0, 0, 1);
		velocity.rotate(zAxis, 5);
	}

	public void turnLeft() {
		Vector3 zAxis = new Vector3();
		zAxis.set(0, 0, 1);
		velocity.rotate(zAxis, -5);
	}
	
	public boolean delay(){
    	Calendar cal = Calendar.getInstance();
    	long currentTime = cal.getTimeInMillis();
    	long previousTime = currentTime;
    	if (currentTime - previousTime >= 2000);
    		return true;
	}
	
	public void increaseAltitude() {
		this.velocity.add(0, 0, 5);
		if (this.velocity.z > maxClimbRate) {
			this.velocity.z = maxClimbRate;
		}
	}

	public void decreaseAltitude() {
		this.velocity.add(0, 0, -5);
		if (this.velocity.z > -maxClimbRate) {
			this.velocity.z = -maxClimbRate;
		}

	}

	public void updateCoords() {
		coords.add(velocity);
	}

	public boolean isAtNextWaypoint(Vector3 vectorToWaypoint) {

		if (vectorToWaypoint.len() < 10) {
			isActive();
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Regular regular getter for radius
	 * 
	 * @return int radius
	 */
	public int getRadius() {
		return radius;
	}

	/**
	 * Regular Set the radius
	 * 
	 * @param int radius
	 */
	public void setRadius(int radius) {
		this.radius = radius;
	}

	/**
	 * Regular getter for separationRadius
	 * 
	 * @return int separationRadius
	 */
	public int getSeparationRadius() {
		return separationRadius;
	}

	/**
	 * Regular Set the separationRadius
	 * 
	 * @param int separationRadius
	 */
	public void setSeparationRadius(int separationRadius) {
		this.separationRadius = separationRadius;
	}

	/**
	 * Regular getter for Texture
	 * 
	 * @return String Texture
	 */
	public String getTexture() {
		return texture;
	}

	/**
	 * Regular Set the Texture
	 * 
	 * @param String
	 *            Texture
	 */
	public void setTexture(String texture) {
		this.texture = texture;
	}

	/**
	 * Regular Set the maxTurningSpeed
	 * 
	 * @param int maxTurningSpeed
	 */
	public void setMaxTurningRate(int maxTurningRate) {
		this.maxTurningRate = maxTurningRate;
	}

	/**
	 * Regular Set the maxClimbRate
	 * 
	 * @param int maxClimbRate
	 */
	public void setMaxClimbRate(int maxClimbRate) {
		this.maxClimbRate = maxClimbRate;
	}

	/**
	 * Regular Set the maxSpeed
	 * 
	 * @param int maxSpeed
	 */
	public void setMaxSpeed(int maxSpeed) {
		this.maxSpeed = maxSpeed;
	}

	public boolean getIsActive() {
		return isActive;
	}

	public boolean isActive() {
		if (waypoints.size() == 1) {
			this.isActive = false;
		}
		return isActive;
	}

	public void checkSpeed() {
		this.velocity.clamp(0, this.maxSpeed);
	}

	/**
	 * Will be used to draw the aircraft
	 */
	public TextureRegion getRegion() {
		// stub
		return null;
	}
}

package uk.ac.york.ini.atc.models;

import java.util.ArrayList;
import java.util.Calendar;

import uk.ac.york.ini.atc.controllers.AircraftType;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;

public class Aircraft extends Entity {

	protected Vector3 coords = new Vector3();
	protected Vector3 velocity = new Vector3();
	protected Vector2 size = new Vector2(76, 63);
	protected int radius;
	protected int separationRadius;
	protected TextureRegion texture = new TextureRegion();
	protected int maxTurningRate;
	protected int maxClimbRate;
	protected int maxSpeed;
	protected boolean isActive;
	protected boolean turningFlag; // May not be used
	protected ArrayList<Waypoint> waypoints = new ArrayList<Waypoint>();
	protected int sepRulesBreachCounter;
	protected int lastTimeTurned;

	public Aircraft(AircraftType aircraftType, ArrayList<Waypoint> flightPlan) {
		// TODO Auto-generated constructor stub
		// Unsure what to implement here.
	}

	@Override
	public void act() {
		this.updateCoords();
		this.calculateVelocity();
		this.isActive();
	}

	public void calculateVelocity() {

		// Creates a new vector to store the new velocity in temporarily
		Vector3 nextWaypointNewCoords = new Vector3();

		// converts waypoints coordinates into 3d vectors to enabled
		// subtraction.
		nextWaypointNewCoords.set(waypoints.get(0).getCoords().x, waypoints
				.get(0).getCoords().y, 2 * coords.z);

		// Calculating velocity and making sure it is under the max and before
		// the next waypoint
		velocity = nextWaypointNewCoords.sub(coords);

		isAtNextWaypoint(velocity);

		checkSpeed();
	}

	// Adding a new waypoint to the head of the arraylist

	public void insertWaypoint(Waypoint newWaypoint) {
		waypoints.add(0, newWaypoint);
	}

	// Turns right by 5 degrees if the user presses the right key for more than
	// 2000ms

	public void turnRight() {
		Vector3 zAxis = new Vector3();
		zAxis.set(0, 0, 1);
		if (delay())
			;
		velocity.rotate(zAxis, 5);
	}

	// Turns left by 5 degrees if the user presses the right key for more than
	// 2000ms

	public void turnLeft() {
		Vector3 zAxis = new Vector3();
		zAxis.set(0, 0, 1);
		if (delay())
			;
		velocity.rotate(zAxis, -5);
	}

	// Calculates the time for which the buttons have been pressed.

	public boolean delay() {
		Calendar cal = Calendar.getInstance();
		long currentTime = cal.getTimeInMillis();
		long previousTime = currentTime;
		if (currentTime - previousTime >= 2000)
			;
		return true;
	}

	// increases rate of altitude change

	public void increaseAltitude() {
		this.velocity.add(0, 0, 5);
		if (this.velocity.z > maxClimbRate) {
			this.velocity.z = maxClimbRate;
		}
	}

	// decreasing rate of altitude change

	public void decreaseAltitude() {
		this.velocity.add(0, 0, -5);
		if (this.velocity.z > -maxClimbRate) {
			this.velocity.z = -maxClimbRate;
		}

	}

	// updates the coords in every frame

	public void updateCoords() {
		coords.add(velocity);
	}

	// checks whether the aircraft is within 10 pixels of the next waypoint

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

	/**
	 * Regular getter for maxspeed
	 * 
	 * @return boolean isActive
	 */

	public boolean getIsActive() {
		return isActive;
	}

	// check if its only got the exit point left to go to.

	public boolean isActive() {
		if (waypoints.size() == 1) {
			this.isActive = false;
		}
		return isActive;
	}

	// limits speed to max speed

	public void checkSpeed() {
		this.velocity.clamp(0, this.maxSpeed);
	}

	/**
	 * Will be used to draw the aircraft
	 */
	@Override
	public TextureRegion getRegion() {
		return texture;
	}

	@Override
	public float getX() {
		return coords.x;
	}

	@Override
	public float getY() {
		return coords.y;
	}

	@Override
	public float getWidth() {
		return size.x;
	}

	@Override
	public float getHeight() {
		return size.y;
	}
}

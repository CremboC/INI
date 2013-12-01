package uk.ac.york.ini.atc.controllers;

import com.badlogic.gdx.math.Vector3;

public class AircraftType {
	private Vector3 coords;
	private Vector3 velocity;
	private int radius;
	private int separationRadius;
	private String texture;
	private int maxTurningSpeed;
	private int maxClimbRate;
	private int maxSpeed;
	private boolean isActive;

	public Vector3 getCoords() {
		return coords;
	}

	public AircraftType setCoords(Vector3 coords) {
		this.coords = coords;
		return this;
	}

	public Vector3 getVelocity() {
		return velocity;
	}

	public AircraftType setVelocity(Vector3 velocity) {
		this.velocity = velocity;
		return this;
	}

	public int getRadius() {
		return radius;
	}

	public AircraftType setRadius(int radius) {
		this.radius = radius;
		return this;
	}

	public int getSeparationRadius() {
		return separationRadius;
	}

	public AircraftType setSeparationRadius(int separationRadius) {
		this.separationRadius = separationRadius;
		return this;
	}

	public String getTexture() {
		return texture;
	}

	public AircraftType setTexture(String texture) {
		this.texture = texture;
		return this;
	}

	public int getMaxTurningSpeed() {
		return maxTurningSpeed;
	}

	public AircraftType setMaxTurningSpeed(int maxTurningSpeed) {
		this.maxTurningSpeed = maxTurningSpeed;
		return this;
	}

	public int getMaxClimbRate() {
		return maxClimbRate;
	}

	public AircraftType setMaxClimbRate(int maxClimbRate) {
		this.maxClimbRate = maxClimbRate;
		return this;
	}

	public int getMaxSpeed() {
		return maxSpeed;
	}

	public AircraftType setMaxSpeed(int maxSpeed) {
		this.maxSpeed = maxSpeed;
		return this;
	}

	public boolean isActive() {
		return isActive;
	}

	public AircraftType setActive(boolean isActive) {
		this.isActive = isActive;
		return this;
	}
}

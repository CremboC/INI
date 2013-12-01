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

	public void setCoords(Vector3 coords) {
		this.coords = coords;
	}

	public Vector3 getVelocity() {
		return velocity;
	}

	public void setVelocity(Vector3 velocity) {
		this.velocity = velocity;
	}

	public int getRadius() {
		return radius;
	}

	public void setRadius(int radius) {
		this.radius = radius;
	}

	public int getSeparationRadius() {
		return separationRadius;
	}

	public void setSeparationRadius(int separationRadius) {
		this.separationRadius = separationRadius;
	}

	public String getTexture() {
		return texture;
	}

	public void setTexture(String texture) {
		this.texture = texture;
	}

	public int getMaxTurningSpeed() {
		return maxTurningSpeed;
	}

	public void setMaxTurningSpeed(int maxTurningSpeed) {
		this.maxTurningSpeed = maxTurningSpeed;
	}

	public int getMaxClimbRate() {
		return maxClimbRate;
	}

	public void setMaxClimbRate(int maxClimbRate) {
		this.maxClimbRate = maxClimbRate;
	}

	public int getMaxSpeed() {
		return maxSpeed;
	}

	public void setMaxSpeed(int maxSpeed) {
		this.maxSpeed = maxSpeed;
	}

	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}
}

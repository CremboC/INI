package uk.ac.york.ini.atc.models;

import com.badlogic.gdx.math.Vector2;

public class Exitpoint extends Waypoint {

	protected int radius;

	public Exitpoint(Vector2 position) {
		super(position, false);
	}
}

package seprini.models;

import com.badlogic.gdx.math.Vector2;

public final class Exitpoint extends Waypoint {

	protected int radius;

	public Exitpoint(Vector2 position) {
		super(position, false);
	}
}

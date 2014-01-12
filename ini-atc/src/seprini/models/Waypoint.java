package seprini.models;

import seprini.data.Art;
import seprini.data.Config;

import com.badlogic.gdx.math.Vector2;

public class Waypoint extends Entity {

	private final boolean deletable;

	public Waypoint(Vector2 position, boolean deletable) {
		coords = position;
		this.deletable = deletable;
		init();
	}

	public Waypoint(float x, float y, boolean deletable) {
		coords = new Vector2(x, y);
		this.deletable = deletable;
		init();
	}

	private void init() {
		this.debugShape = true;
		this.texture = Art.getTextureRegion("waypoint");
		this.size = Config.WAYPOINT_SIZE;

		this.setOrigin(getWidth() / 2, getHeight() / 2);
		this.setBounds(getX(), getY(), getWidth(), getHeight());
	}

	public boolean isDeletable() {
		return deletable;
	}

	public String toString() {
		return "Waypoint - x: " + getX() + " y: " + getY();
	}

}

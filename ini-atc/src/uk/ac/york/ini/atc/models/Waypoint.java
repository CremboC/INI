package uk.ac.york.ini.atc.models;

import uk.ac.york.ini.atc.data.Art;
import uk.ac.york.ini.atc.data.Config;

import com.badlogic.gdx.math.Vector2;

public class Waypoint extends Entity {

	private boolean deletable;

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

}

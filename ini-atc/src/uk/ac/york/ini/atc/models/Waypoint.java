package uk.ac.york.ini.atc.models;

import uk.ac.york.ini.atc.data.Config;
import uk.ac.york.ini.atc.handlers.Art;

import com.badlogic.gdx.math.Vector2;

public class Waypoint extends Entity {

	public Waypoint(Vector2 position) {
		coords = position;
		init();
	}

	public Waypoint(float x, float y) {
		coords = new Vector2(x, y);
		init();
	}

	private void init() {
		this.texture = Art.getTextureRegion("waypoint");
		this.size = Config.WAYPOINT_SIZE;

		this.setOrigin(getWidth() / 2, getHeight() / 2);
		this.setBounds(getX(), getY(), getWidth(), getHeight());
	}

	@Override
	public void act() {
	}

}

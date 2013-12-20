package uk.ac.york.ini.atc.models;

import uk.ac.york.ini.atc.data.Config;
import uk.ac.york.ini.atc.handlers.Art;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

public class Waypoint extends Entity {

	public static Vector2 size = Config.WAYPOINT_SIZE;

	protected Vector2 coords = new Vector2();
	protected TextureRegion texture = Art.getTextureRegion("waypoint");

	public Waypoint(Vector2 position) {
		coords.x = position.x - size.x / 2;
		coords.y = position.y - size.y / 2;
		init();
	}

	public Waypoint(float x, float y) {
		coords = new Vector2(x - size.x / 2, y - size.y / 2);
		init();
	}

	private void init() {
		this.setOrigin(getWidth() / 2, getHeight() / 2);
		this.setBounds(getX(), getY(), getWidth(), getHeight());
	}

	@Override
	public void act() {
	}

	public Vector2 getCoords() {
		return coords;
	}

	public void setXCoord(float x) {
		this.coords.x = x;
	}

	public void setYCoord(float y) {
		this.coords.y = y;
	}

	public void setCoords(Vector2 newCoords) {
		this.coords.x = newCoords.x;
		this.coords.y = newCoords.y;
	}

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

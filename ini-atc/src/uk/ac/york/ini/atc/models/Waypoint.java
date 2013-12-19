package uk.ac.york.ini.atc.models;

import uk.ac.york.ini.atc.data.Config;
import uk.ac.york.ini.atc.handlers.Art;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

public class Waypoint extends Entity {

	protected Vector2 coords;
	protected Vector2 size = Config.WAYPOINT_SIZE;
	protected TextureRegion texture = Art.getTextureRegion("waypoint");

	public Waypoint(Vector2 position) {
		coords = position;

		this.setOrigin(getWidth() / 2, getHeight() / 2);
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

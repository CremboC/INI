package uk.ac.york.ini.atc.models;

import uk.ac.york.ini.atc.handlers.Art;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

public class Waypoint extends Entity {

	protected Vector2 coords;
	protected Vector2 size = new Vector2(32, 32);
	protected TextureRegion texture = Art.getTextureRegion("waypoint");

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
	public void act() {
		// TODO Auto-generated method stub

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

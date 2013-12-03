package uk.ac.york.ini.atc.models;

import uk.ac.york.ini.atc.handlers.Art;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

public class Airspace extends Entity {

	private final TextureRegion texture = Art.getTextureRegion("airspace");
	private final Vector2 pos = new Vector2(0, 0);
	private final Vector2 size = new Vector2(1280, 720);

	@Override
	public void act() {

	}

	@Override
	public TextureRegion getRegion() {
		return texture;
	}

	@Override
	public float getX() {
		return pos.x;
	}

	@Override
	public float getY() {
		return pos.y;
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

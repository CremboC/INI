package uk.ac.york.ini.atc.models;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;

public abstract class Entity extends Actor {

	protected Vector2 coords;
	protected TextureRegion texture;
	protected Vector2 size;

	public abstract void act();

	@Override
	public void draw(SpriteBatch batch, float parentAlpha) {
		Color color = getColor();

		batch.setColor(color.r, color.g, color.b, color.a * parentAlpha);

		batch.draw(getRegion(), getX(), getY(), getOriginX(), getOriginY(),
				getWidth(), getHeight(), getScaleX(), getScaleX(),
				getRotation());
	}

	public TextureRegion getRegion() {
		return texture;
	}

	public Vector2 getCoords() {
		return coords;
	}

	public float getX() {
		return coords.x - getOriginX();
	}

	public float getY() {
		return coords.y - getOriginY();
	}

	public float getWidth() {
		return size.x;
	}

	public float getHeight() {
		return size.y;
	}
}

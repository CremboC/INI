package uk.ac.york.ini.atc.models;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;

public abstract class Entity extends Actor {

	public abstract void act();

	@Override
	public void draw(SpriteBatch batch, float parentAlpha) {
		Color color = getColor();
		batch.setColor(color.r, color.g, color.b, color.a * parentAlpha);
		batch.draw(getRegion(), getX(), getY(), getWidth(), getHeight());
	}

	public abstract TextureRegion getRegion();

	@Override
	public abstract float getX();

	@Override
	public abstract float getY();

	@Override
	public abstract float getWidth();

	@Override
	public abstract float getHeight();

}

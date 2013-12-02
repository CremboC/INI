package uk.ac.york.ini.atc.models;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;

public abstract class Entity extends Actor {

	public Entity() {
		// TODO Auto-generated constructor stub
	}

	public abstract void act();

	public void draw(SpriteBatch batch, float parentAlpha) {
		Color color = getColor();
		batch.setColor(color.r, color.g, color.b, color.a * parentAlpha);
		batch.draw(getRegion(), getX(), getY(), getWidth(), getHeight());
	}

	public abstract TextureRegion getRegion();

}

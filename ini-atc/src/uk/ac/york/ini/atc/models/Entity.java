package uk.ac.york.ini.atc.models;

import uk.ac.york.ini.atc.data.Config;
import uk.ac.york.ini.atc.screens.Screen;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;

public abstract class Entity extends Actor {

	protected Vector2 coords;
	protected TextureRegion texture;
	protected Vector2 size;

	protected boolean debugShape = false;

	@Override
	public void draw(SpriteBatch batch, float parentAlpha) {
		Color color = getColor();

		batch.setColor(color.r, color.g, color.b, color.a * parentAlpha);

		batch.draw(getRegion(), getX() - getWidth() / 2, getY() - getHeight()
				/ 2, getOriginX(), getOriginY(), getWidth(), getHeight(),
				getScaleX(), getScaleX(), getRotation());

		if (Config.DEBUG)
			drawDebug(batch);
	}

	/**
	 * Used to draw debugging shapes
	 * 
	 * @param batch
	 */
	private void drawDebug(SpriteBatch batch) {
		if (!debugShape)
			return;

		batch.end();

		Screen.shapeDebugger.begin(ShapeType.Line);
		Screen.shapeDebugger.setColor(1, 0, 0, 0);
		Screen.shapeDebugger.circle(getX(), getY(), getWidth() / 2);
		Screen.shapeDebugger.end();

		batch.begin();
	}

	public TextureRegion getRegion() {
		return texture;
	}

	public Vector2 getCoords() {
		return coords;
	}

	public float getX() {
		return coords.x;
	}

	public float getY() {
		return coords.y;
	}

	public float getWidth() {
		return size.x;
	}

	public float getHeight() {
		return size.y;
	}
}

package seprini.models;

import seprini.data.Config;
import seprini.screens.Screen;

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

		additionalDraw(batch);

		if (Config.DEBUG_UI) {
			drawDebug(batch);
		}
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

		Screen.shapeRenderer.begin(ShapeType.Line);
		Screen.shapeRenderer.setColor(1, 0, 0, 0);
		Screen.shapeRenderer.box(getX() - getWidth() / 2, getY() - getHeight()
				/ 2, 0, getWidth(), getHeight(), 0);
		Screen.shapeRenderer.end();

		batch.begin();
	}

	protected void additionalDraw(SpriteBatch batch) {

	}

	public TextureRegion getRegion() {
		return texture;
	}

	public Vector2 getCoords() {
		return coords;
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

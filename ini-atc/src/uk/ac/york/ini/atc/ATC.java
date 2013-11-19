package uk.ac.york.ini.atc;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

public class ATC implements ApplicationListener {

	private OrthographicCamera camera;
	private SpriteBatch batch;
	private Texture texture;
	private Sprite map;
	private BitmapFont font;

	private final Vector2 topLeft = new Vector2(-640, 360);
	private final Vector2 topRight = new Vector2(640, 360);
	private final Vector2 bottomLeft = new Vector2(-640, -360);
	private final Vector2 bottomRight = new Vector2(640, -360);

	@Override
	public void create() {
		float w = Gdx.graphics.getWidth();
		float h = Gdx.graphics.getHeight();

		camera = new OrthographicCamera(w, h);
		batch = new SpriteBatch();
		font = new BitmapFont();

		texture = new Texture(Gdx.files.internal("data/nisairspace.png"));
		texture.setFilter(TextureFilter.Linear, TextureFilter.Linear);

		TextureRegion region = new TextureRegion(texture, 0, 0, 1280, 720);

		map = new Sprite(region);
		map.setPosition(bottomLeft.x, bottomLeft.y);
	}

	@Override
	public void dispose() {
		batch.dispose();
		texture.dispose();
	}

	@Override
	public void render() {

		// Should be in update method
		// System.out.println(Gdx.input.getX());
		// System.out.println(Gdx.input.getY());

		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);

		batch.setProjectionMatrix(camera.combined);
		batch.begin();

		font.setColor(Color.BLACK);
		font.draw(batch, "fps: " + Gdx.graphics.getFramesPerSecond(),
				topLeft.x + 10, topLeft.y - 10);

		map.draw(batch);

		batch.end();
	}

	@Override
	public void resize(int width, int height) {
	}

	@Override
	public void pause() {
	}

	@Override
	public void resume() {
	}

}

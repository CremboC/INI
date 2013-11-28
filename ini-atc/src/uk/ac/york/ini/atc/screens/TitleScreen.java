package uk.ac.york.ini.atc.screens;

import uk.ac.york.ini.atc.handlers.Input;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class TitleScreen extends Screen {

	private Texture texture;
	private TextureRegion region;

	private int time = 0;

	// Temporary variable to make sure stuff is not loaded twice
	private boolean loaded = false;

	/**
	 * A method to load all the required textures for this screen. Should be
	 * moved to a separate class which preloads everything and puts it into a
	 * pool, from which everything should be taken.
	 */
	public void load() {
		if (loaded)
			return;

		loaded = true;

		// loads the texture, whose size must be in power's of two
		texture = new Texture(Gdx.files.internal("data/nisairspace.png"));

		// select a specific area of the loaded texture, this will be passed
		// onto a wrapper and drawn
		region = new TextureRegion(texture, 0, 0, 1280, 720);
	}

	/**
	 * The main loop, do all the rendering here
	 */
	@Override
	public void render() {
		load();
		spriteBatch.begin();

		draw(region, 0, 0);

		drawString("fps: " + Gdx.graphics.getFramesPerSecond(), 10, 20,
				Color.BLACK);

		spriteBatch.end();
	}

	/**
	 * Called every tick of the main loop. Do all the movement and collision
	 * detection here
	 * 
	 * @param input
	 */
	@Override
	public void update(Input input) {
		time++;
	}

}

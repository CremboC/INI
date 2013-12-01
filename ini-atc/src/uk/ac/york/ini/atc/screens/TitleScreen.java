package uk.ac.york.ini.atc.screens;

import uk.ac.york.ini.atc.handlers.Art;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class TitleScreen extends Screen {

	private final TextureRegion airspace = Art.getTextureRegion("airspace");
	private final TextureRegion aircraft = Art.getTextureRegion("aircraft");
	private int time = 0;

	/**
	 * The main loop, do all the rendering here
	 */
	@Override
	public void render() {
		spriteBatch.begin();

		draw(airspace, 0, 0);

		draw(aircraft, Screen.width / 2, Screen.height / 2);

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
	public void update() {
		time++;
	}

}

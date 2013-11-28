package uk.ac.york.ini.atc;

import uk.ac.york.ini.atc.handlers.Input;
import uk.ac.york.ini.atc.screens.Screen;
import uk.ac.york.ini.atc.screens.TitleScreen;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL10;

/**
 * Main class, calls all subsequent classes. Initialises Input, Art classes,
 * first and last class to be called
 * 
 * @author Crembo
 * 
 */
public class ATC implements ApplicationListener {

	/**
	 * Handles input
	 */
	public Input input = new Input();

	/**
	 * The current screen
	 */
	public Screen screen;

	@Override
	public void create() {
		Gdx.input.setInputProcessor(input);

		setScreen(new TitleScreen());
	}

	/**
	 * Changes the current screen
	 * 
	 * @param newScreen
	 */
	public void setScreen(Screen newScreen) {
		if (screen != null)
			screen.removed();
		screen = newScreen;
		if (screen != null)
			screen.init(this);
	}

	@Override
	public void render() {

		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);

		screen.update(input);
		screen.render();
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

	@Override
	public void dispose() {
		screen.removed();
	}

}

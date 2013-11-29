package uk.ac.york.ini.atc.screens;

import uk.ac.york.ini.atc.handlers.Input;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Stage;

/**
 * Menu Screen, displays the menu
 * 
 * @author Crembo
 * 
 */
public class MenuScreen extends Screen {

	private Stage stage;

	public MenuScreen() {
		/**
		 * Initialise screen on startup -Start and exit buttons
		 * 
		 */
		stage = new Stage();
		Gdx.input.setInputProcessor(stage);
	}

	@Override
	public void render() {
		// TODO Auto-generated method stub
		stage.act(Gdx.graphics.getDeltaTime());
		stage.draw();
	}

	@Override
	public void update(Input input) {
		// TODO Auto-generated method stub

	}

	/**
	 * Called on change of screen, disposes of unneeded resources (ie. prevents
	 * a memory leak)
	 */
	public void removed() {
		stage.dispose();
	}

}

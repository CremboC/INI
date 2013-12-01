package uk.ac.york.ini.atc.screens;

import uk.ac.york.ini.atc.handlers.Art;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;

/**
 * Menu Screen, displays the menu
 * 
 * @author Paulius, Miguel
 * 
 */
public class MenuScreen extends Screen {

	/**
	 * The stage for this screen
	 */
	private final Stage stage;

	/**
	 * Buttons in this screen, so the button handler can access them
	 */
	private final TextButton startButton, exitButton;

	public MenuScreen() {
		InputHandler handler = new InputHandler();

		// create a stage and set it as the input processor
		stage = new Stage();
		Gdx.input.setInputProcessor(stage);

		// create a table layout
		Table table = new Table();

		// make it fill the whole screen
		table.setFillParent(true);

		// add it to the screen
		stage.addActor(table);

		// Create a start button and add a listener to the button.
		// ChangeListener is fired when the button
		// is clicked
		startButton = new TextButton("Start game", Art.getSkin());
		startButton.addListener(handler);
		table.add(startButton).width(100);

		// create a new row
		table.row();

		// create the Exit button
		exitButton = new TextButton("Exit", Art.getSkin());
		exitButton.addListener(handler);
		table.add(exitButton).width(100);

		table.toFront();
	}

	@Override
	public void render() {
		stage.draw();
	}

	@Override
	public void update() {
		stage.act(Gdx.graphics.getDeltaTime());
	}

	/**
	 * Called on change of screen, disposes of unneeded resources (ie. prevents
	 * a memory leak)
	 */
	@Override
	public void removed() {
		stage.dispose();
	}

	/**
	 * Handles the input for this screen
	 * 
	 * @author Paulius, Miguel
	 * 
	 */
	private class InputHandler extends ChangeListener {

		/**
		 * Handles all the buttons for this screen. For every new button a new
		 * if should be added followed by what should be done after it's clicked
		 * 
		 */
		@Override
		public void changed(ChangeEvent event, Actor actor) {

			if (actor.equals(startButton))
				setScreen(new TitleScreen());

			if (actor.equals(exitButton))
				Gdx.app.exit();
		}

	}

}

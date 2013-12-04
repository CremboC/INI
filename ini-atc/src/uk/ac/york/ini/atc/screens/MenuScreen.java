package uk.ac.york.ini.atc.screens;

import uk.ac.york.ini.atc.data.GameDifficulty;
import uk.ac.york.ini.atc.handlers.Art;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;

/**
 * Menu Screen, displays the menu with buttons to start and exit the game
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
	private final TextButton startButtonEasy, startButtonMedium,
			startButtonHard, exitButton;

	/**
	 * Initialises the input handler, stage and create the layout with buttons
	 * for the menu screen
	 */
	public MenuScreen() {

		InputHandler handler = new InputHandler();

		// create a stage and set it as the input processor
		stage = new Stage();
		Gdx.input.setInputProcessor(stage);

		// create a table layout
		Table table = new Table();

		// make it fill the whole screen
		table.setFillParent(true);

		stage.addActor(table);

		// Create a start button and add a listener to the button.
		// ChangeListener is fired when the button
		// is clicked

		// Create a label for difficulty and add it
		Label difficultylabel = new Label("Difficulty:", Art.getSkin());
		table.add(difficultylabel).width(100).center();

		// create a button to start the game in easy mode
		startButtonEasy = new TextButton("Easy", Art.getSkin());
		startButtonEasy.addListener(handler);
		table.add(startButtonEasy).width(100);

		// create a button to start the game in medium mode
		startButtonMedium = new TextButton("Medium", Art.getSkin());
		startButtonMedium.addListener(handler);
		table.add(startButtonMedium).width(100);

		// create a button to start the game in hard mode
		startButtonHard = new TextButton("Hard", Art.getSkin());
		startButtonHard.addListener(handler);
		table.add(startButtonHard).width(100);

		// create a new row
		table.row();

		// create the Exit button
		exitButton = new TextButton("Exit", Art.getSkin());
		exitButton.addListener(handler);
		table.add(exitButton).width(200).colspan(4);
		table.toFront();
	}

	@Override
	public void render() {
		stage.draw();
	}

	@Override
	public void update() {
		stage.act();
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

			if (actor.equals(exitButton))
				Gdx.app.exit();

			if (actor.equals(startButtonEasy))
				setScreen(new GameScreen(GameDifficulty.EASY));

			if (actor.equals(startButtonMedium))
				setScreen(new GameScreen(GameDifficulty.MEDIUM));

			if (actor.equals(startButtonHard))
				setScreen(new GameScreen(GameDifficulty.HARD));
		}
	}

}

package uk.ac.york.ini.atc.screens;

import uk.ac.york.ini.atc.controllers.AircraftController;
import uk.ac.york.ini.atc.data.GameDifficulty;
import uk.ac.york.ini.atc.handlers.Art;
import uk.ac.york.ini.atc.models.Airspace;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;

public class GameScreen extends Screen {

	private final Stage stage;
	private final AircraftController controller;

	private final Airspace airspace;

	private final TextButton button;

	public GameScreen(GameDifficulty diff) {

		InputHandler handler = new InputHandler();
		stage = new Stage();
		controller = new AircraftController(diff, stage);

		Gdx.input.setInputProcessor(stage);

		// create a table layout
		Table table = new Table();

		// make it fill the whole screen
		table.setFillParent(true);

		stage.addActor(table);

		airspace = new Airspace();
		// stage.addActor(airspace);

		table.add(airspace).width(1080);

		button = new TextButton("button", Art.getSkin());
		button.addListener(handler);
		table.add(button).width(200).top();
	}

	@Override
	public void render() {
		stage.draw();
	}

	@Override
	public void update() {
		controller.update();
		stage.act(Gdx.graphics.getDeltaTime());
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

			if (actor.equals(button))
				return;

		}
	}

}

package uk.ac.york.ini.atc.screens;

import uk.ac.york.ini.atc.controllers.AircraftController;
import uk.ac.york.ini.atc.data.GameDifficulty;
import uk.ac.york.ini.atc.handlers.Art;
import uk.ac.york.ini.atc.models.Airspace;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

public class GameScreen extends Screen {

	private final Stage stage;
	private final AircraftController controller;

	private final Airspace airspace;

	private final TextButton button;

	private final InputHandler handler = new InputHandler();

	public GameScreen(GameDifficulty diff) {

		stage = new Stage();
		controller = new AircraftController(diff, stage, handler);

		Gdx.input.setInputProcessor(stage);

		// create a table layout
		Table table = new Table();

		// make it fill the whole screen
		table.setFillParent(true);

		stage.addActor(table);

		airspace = new Airspace();
		airspace.addListener(handler);
		stage.addActor(airspace);

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
	public class InputHandler extends ClickListener {

		public Vector2 mousePosition = new Vector2(0, 0);

		@Override
		public void clicked(InputEvent event, float x, float y) {
			mousePosition.x = x;
			mousePosition.y = y;
		}

		public Vector2 getMousePosition() {
			return mousePosition;
		}
	}

}

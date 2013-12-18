package uk.ac.york.ini.atc.screens;

import uk.ac.york.ini.atc.controllers.AircraftController;
import uk.ac.york.ini.atc.data.GameDifficulty;
import uk.ac.york.ini.atc.handlers.Art;
import uk.ac.york.ini.atc.models.Airspace;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;

/**
 * The game screen - all game logic starts here
 * 
 * @author Paulius, Miguel
 * 
 */
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

		table.add(airspace).width(1080).left();

		button = new TextButton("button", Art.getSkin());
		button.addListener(handler);
		table.add(button).width(200).top();
	}

	@Override
	public void render() {
		stage.draw();
		drawString("fps: " + Gdx.graphics.getFramesPerSecond(), 10, 20,
				Color.BLACK, stage.getSpriteBatch());
	}

	@Override
	public void update() {
		controller.update();
		stage.act();
	}

	/**
	 * Handles the input for this screen
	 * 
	 * @author Paulius, Miguel
	 * 
	 */
	public class InputHandler extends InputListener {

		/**
		 * Last mouse position after click
		 */
		private final Vector2 mousePosition = new Vector2(0, 0);

		/**
		 * Which mouse button was clicked
		 */
		private Integer buttonPressed = -1;

		@Override
		public boolean touchDown(InputEvent event, float x, float y,
				int pointer, int button) {
			buttonPressed = button;
			mousePosition.x = x;
			mousePosition.y = y;

			return true;
		}

		@Override
		public void touchUp(InputEvent event, float x, float y, int pointer,
				int button) {
			buttonPressed = null;
		}

		/**
		 * Coordinates where the mouse was last clicked
		 * 
		 * @return
		 */
		public Vector2 getMousePosition() {
			return mousePosition;
		}

		/**
		 * Get which mouse button was clicked. <br>
		 * Compare with Button.LEFT and Button.RIGHT to check which one was
		 * clicked.
		 * 
		 * @return
		 */
		public Integer getButtonPressed() {
			return buttonPressed;
		}
	}

}

package uk.ac.york.ini.atc.screens;

import uk.ac.york.ini.atc.controllers.AircraftController;
import uk.ac.york.ini.atc.data.Config;
import uk.ac.york.ini.atc.data.GameDifficulty;
import uk.ac.york.ini.atc.handlers.Art;
import uk.ac.york.ini.atc.models.Airspace;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;

/**
 * The game screen - all game logic starts here
 * 
 * @author Paulius, Miguel
 * 
 */
public class GameScreen extends Screen {

	private final Stage root;
	private final AircraftController controller;

	private Airspace airspace;

	private final TextButton button;

	public GameScreen(GameDifficulty diff) {

		// create a table layout, main ui
		Table ui = new Table();

		// create a separate layout for sidebar with all the buttons and
		// required info
		Table sidebar = new Table();

		// create and add the Airspace group, contains aircraft and waypoints
		Airspace airspace = new Airspace();

		controller = new AircraftController(diff, airspace, sidebar);
		root = new Stage();

		Gdx.input.setInputProcessor(root);

		// make it fill the whole screen
		ui.setFillParent(true);
		root.addActor(ui);

		airspace.addListener(controller);
		ui.add(airspace).width(Config.AIRSPACE_SIZE.x)
				.height(Config.AIRSPACE_SIZE.y);

		ui.add(sidebar).width(Config.SIDEBAR_SIZE.x)
				.height(Config.SIDEBAR_SIZE.y).right();

		button = new TextButton("button", Art.getSkin());
		button.addListener(controller);
		sidebar.add(button).width(200);

		sidebar.row();

		Label label = new Label("Label", Art.getSkin(), "textStyle");
		sidebar.add(label).center();

		controller.init();
	}

	@Override
	public void render() {
		root.draw();
		drawString("fps: " + Gdx.graphics.getFramesPerSecond(), 10, 20,
				Color.BLACK, root.getSpriteBatch());
	}

	@Override
	public void update() {
		controller.update();
		root.act();
	}

}

package uk.ac.york.ini.atc.screens;

import uk.ac.york.ini.atc.controllers.AircraftController;
import uk.ac.york.ini.atc.data.GameDifficulty;
import uk.ac.york.ini.atc.models.Airspace;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Stage;

public class GameScreen extends Screen {

	private final Stage stage;
	private final AircraftController controller;

	private final Airspace airspace;

	public GameScreen(GameDifficulty diff) {

		stage = new Stage();
		controller = new AircraftController(diff, stage);

		Gdx.input.setInputProcessor(stage);

		airspace = new Airspace();
		stage.addActor(airspace);
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

}

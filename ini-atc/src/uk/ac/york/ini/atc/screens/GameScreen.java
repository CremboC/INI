package uk.ac.york.ini.atc.screens;

import uk.ac.york.ini.atc.controllers.AircraftController;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Stage;

public class GameScreen extends Screen {

	private final Stage stage;
	private final AircraftController controller;

	public GameScreen() {
		// TODO Auto-generated constructor stub
		stage = new Stage();
		controller = new AircraftController();

		Gdx.input.setInputProcessor(stage);
	}

	@Override
	public void render() {
		stage.draw();
	}

	@Override
	public void update() {
		controller.update(stage);
		stage.act(Gdx.graphics.getDeltaTime());
	}

}

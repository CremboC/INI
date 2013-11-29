package uk.ac.york.ini.atc.screens;

import uk.ac.york.ini.atc.controllers.AircraftController;
import uk.ac.york.ini.atc.handlers.Input;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Stage;

public class GameScreen extends Screen {

	private Stage stage;
	private AircraftController controller;

	public GameScreen() {
		// TODO Auto-generated constructor stub
		stage = new Stage();
		controller = new AircraftController();

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
		controller.update(input);
	}

}

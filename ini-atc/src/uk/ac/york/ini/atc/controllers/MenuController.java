package uk.ac.york.ini.atc.controllers;

import java.util.HashMap;

import uk.ac.york.ini.atc.data.Art;
import uk.ac.york.ini.atc.data.GameDifficulty;
import uk.ac.york.ini.atc.models.MenuAircraft;
import uk.ac.york.ini.atc.screens.GameScreen;
import uk.ac.york.ini.atc.screens.MenuScreen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.EventListener;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.esotericsoftware.tablelayout.Cell;

/**
 * Menu Controller
 * 
 * @author Crembo
 * 
 */
public class MenuController implements Controller {

	private final Table ui;
	private final MenuScreen screen;

	private HashMap<String, TextButton> buttons;

	public MenuController(MenuScreen screen, Table ui) {
		this.ui = ui;
		this.screen = screen;

		buttons = new HashMap<String, TextButton>();
		addButtons();
	}

	/**
	 * Adds all the buttons on the screen with their own listener
	 */
	private void addButtons() {
		// add the sideways aircraft with the title
		ui.addActor(new MenuAircraft());

		// Create a label for difficulty and add it
		Label difficultylabel = new Label("Difficulty:", Art.getSkin());
		ui.add(difficultylabel).width(100).center();

		// create a button to start the game in easy mode
		addButton("startButtonEasy", "Easy", new ChangeListener() {
			@Override
			public void changed(ChangeEvent event, Actor actor) {
				screen.setScreen(new GameScreen(GameDifficulty.EASY));
			}
		}).width(100);

		// create a button to start the game in medium mode
		addButton("startButtonMedium", "Medium", new ChangeListener() {
			@Override
			public void changed(ChangeEvent event, Actor actor) {
				screen.setScreen(new GameScreen(GameDifficulty.MEDIUM));
			}
		}).width(100);

		// create a button to start the game in hard mode
		addButton("startButtonHard", "Hard", new ChangeListener() {
			@Override
			public void changed(ChangeEvent event, Actor actor) {
				screen.setScreen(new GameScreen(GameDifficulty.HARD));
			}
		}).width(100);

		// create a new row
		ui.row();

		// create the Exit button
		addButton("exitButton", "Exit", new ChangeListener() {
			@Override
			public void changed(ChangeEvent event, Actor actor) {
				Gdx.app.exit();
			}
		}).width(200).colspan(4);

		ui.toFront();
	}

	/**
	 * Convinience method to add a button to the UI
	 * 
	 * @param name
	 * @param text
	 * @return
	 */
	private Cell<?> addButton(String name, String text, EventListener listener) {
		TextButton button = new TextButton(text, Art.getSkin());
		buttons.put(name, button);
		button.addListener(listener);

		return ui.add(button);
	}
}

package seprini.controllers;

import java.util.HashMap;

import seprini.data.Art;
import seprini.data.GameDifficulty;
import seprini.models.MenuAircraft;
import seprini.screens.GameScreen;
import seprini.screens.MenuScreen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Actor;
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
public final class MenuController extends ChangeListener implements Controller {

	private final Table ui;
	private final MenuScreen screen;

	private HashMap<String, TextButton> buttons;

	/**
	 * Initiate variables, set the screen and ui, add all of the buttons to the
	 * screen
	 * 
	 * @param screen
	 *            required so the controller could change the screen after
	 *            clicking the appropriate button
	 * @param ui
	 *            required for actually adding the buttons
	 */
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
		addButton("startEasy", "Easy", this).width(100);

		// create a button to start the game in medium mode
		addButton("startMedium", "Medium", this).width(100);

		// create a button to start the game in hard mode
		addButton("startHard", "Hard", this).width(100);

		// create a new row
		ui.row();

		// create the Exit button
		addButton("exit", "Exit", this).width(200).colspan(4);

		ui.toFront();
	}

	/**
	 * Convinience method to add a button to the UI
	 * 
	 * @param name
	 * @param text
	 * @return
	 */
	private Cell<?> addButton(String name, String text, ChangeListener listener) {
		TextButton button = new TextButton(text, Art.getSkin());
		buttons.put(name, button);
		button.addListener(listener);

		return ui.add(button);
	}

	/**
	 * Handles what happens once one of the buttons have been clicked
	 */
	@Override
	public void changed(ChangeEvent event, Actor actor) {

		// Pass difficulty to the newly created GameScreen so the game can
		// change variables depending on it
		if (actor.equals(buttons.get("startEasy")))
			screen.setScreen(new GameScreen(GameDifficulty.EASY));

		if (actor.equals(buttons.get("startMedium")))
			screen.setScreen(new GameScreen(GameDifficulty.MEDIUM));

		if (actor.equals(buttons.get("startHard")))
			screen.setScreen(new GameScreen(GameDifficulty.HARD));

		if (actor.equals(buttons.get("exit")))
			Gdx.app.exit();

	}
}

package seprini.screens;

import seprini.controllers.MenuController;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Table;

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
	private final Stage root;
	private final Table ui;

	private final MenuController controller;

	/**
	 * Initialises the input handler, stage and create the layout with buttons
	 * for the menu screen
	 */
	public MenuScreen() {
		// create a stage and set it as the input processor
		root = new Stage();
		Gdx.input.setInputProcessor(root);

		// create a table layout
		ui = new Table();

		controller = new MenuController(this, ui);

		// make it fill the whole screen
		ui.setFillParent(true);

		// add the table UI
		root.addActor(ui);
	}

	@Override
	public void render() {
		root.draw();
	}

	@Override
	public void update() {
		root.act();
	}

	/**
	 * Called on change of screen, disposes of unneeded resources (ie. prevents
	 * a memory leak)
	 */
	@Override
	public void removed() {
		root.dispose();
	}

}

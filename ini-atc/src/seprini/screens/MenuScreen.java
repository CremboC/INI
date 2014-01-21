package seprini.screens;

import seprini.controllers.MenuController;
import seprini.data.Art;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
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

		// creater the controller for this screen, this will handle basically
		// everything for this screen, including input and creation of buttons
		new MenuController(this, ui);

		// make it fill the whole screen
		ui.setFillParent(true);

		// add the table UI
		root.addActor(ui);

		Art.getSound("national").play(0.5f);
	}

	@Override
	public void render() {
		root.draw();

		

		drawString(
				"Copyright Disclaimer Under Section 107 of the Copyright Act 1976, allowance is made "
						+ "for 'fair use' for purposes such as criticism, comment, news reporting, teaching, "
						+ "scholarship, and research. Fair use is a use permitted by copyright statute that "
						+ "might otherwise be infringing. Non-profit, educational or personal use tips the "
						+ "balance in favor of fair use.", 10, 10, Color.BLACK,
				root.getSpriteBatch(), false, 0.5f);

		draw(Art.getTextureRegion("menuAircraft"), 300, 390,
				root.getSpriteBatch());

		draw(Art.getTextureRegion("libgdx"), 1228, 0,
				root.getSpriteBatch());
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

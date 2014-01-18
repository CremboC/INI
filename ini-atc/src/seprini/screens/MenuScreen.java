package seprini.screens;

import seprini.controllers.MenuController;
import seprini.data.Art;

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

	/**
	 * Initialises the input handler, stage and create the layout with buttons
	 * for the menu screen
	 */
	public MenuScreen() {
		// set font size for this screen
		Art.getSkin().getFont("default").setScale(1f);

		// create a stage and set it as the input processor
		root = new Stage();
		Gdx.input.setInputProcessor(root);

		// create a table layout
		ui = new Table();

		// creater the controller for this screen, this will handle basically
		// everything for this screen, including input and creation of buttons
		MenuController controller = new MenuController(this, ui);

		// make it fill the whole screen
		ui.setFillParent(true);

		// add the table UI
		root.addActor(ui);

		Art.getSound("national").play(0.5f);
	}

	@Override
	public void render() {
		root.draw();

		this.draw(Art.getTextureRegion("menuAircraft"), 300, 390,
				root.getSpriteBatch());

		this.draw(Art.getTextureRegion("libgdx"), 1228, 0,
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

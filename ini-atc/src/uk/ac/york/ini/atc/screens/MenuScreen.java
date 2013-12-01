package uk.ac.york.ini.atc.screens;

import uk.ac.york.ini.atc.handlers.Art;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;

/**
 * Menu Screen, displays the menu
 * 
 * @author Paulius, Miguel
 * 
 */
public class MenuScreen extends Screen {
	private final Stage stage;

	public MenuScreen() {

		// TODO Auto-generated constructor stub
		stage = new Stage();
		Gdx.input.setInputProcessor(stage);

		Table table = new Table();
		table.setFillParent(true);
		stage.addActor(table);

		// Create a button with the "default" TextButtonStyle. A 3rd parameter
		// can be used to specify a name other than "default".
		final TextButton startButton = new TextButton("Start game!",
				Art.getSkin());
		table.add(startButton);

		// Add a listener to the button. ChangeListener is fired when the
		// button's checked state changes, eg when clicked,
		// Button#setChecked() is called, via a key press, etc. If the
		// event.cancel() is called, the checked state will be reverted.
		// ClickListener could have been used, but would only fire when clicked.
		// Also, canceling a ClickListener event won't
		// revert the checked state.
		startButton.addListener(new ChangeListener() {
			@Override
			public void changed(ChangeEvent event, Actor actor) {
				setScreen(new TitleScreen());
			}
		});

		table.row();

		final TextButton exitButton = new TextButton("Exit", Art.getSkin());
		table.add(exitButton).width(100);

		exitButton.addListener(new ChangeListener() {
			@Override
			public void changed(ChangeEvent event, Actor actor) {
				Gdx.app.exit();
			}
		});

		table.toFront();
	}

	@Override
	public void render() {
		// TODO Auto-generated method stub
		stage.draw();
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		stage.act(Gdx.graphics.getDeltaTime());
	}

	/**
	 * Called on change of screen, disposes of unneeded resources (ie. prevents
	 * a memory leak)
	 */
	@Override
	public void removed() {
		stage.dispose();
	}

}

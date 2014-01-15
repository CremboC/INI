package seprini.screens;

import seprini.data.Art;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;

public class EndScreen extends Screen {

	private final Stage root;

	public EndScreen() {
		root = new Stage();
		Gdx.input.setInputProcessor(root);

		Table ui = new Table();

		ui.setFillParent(true);
		root.addActor(ui);

		ui.addListener(new InputListener() {
			@Override
			public boolean keyDown(InputEvent event, int keycode) {
				if (keycode == Keys.ESCAPE)
					setScreen(new MenuScreen());

				return false;
			}
		});

		root.setKeyboardFocus(ui);

		Label text = new Label(
				"PLANES HAVE COLLIDED OMG\nPRESS ESC TO RETURN TO MAIN MENU",
				Art.getSkin(), "textStyle");

		ui.add(text).center();

		ui.row();

		Art.getSkin().getFont("default").setScale(1f);

		TextButton button = new TextButton("Main Menu", Art.getSkin());

		button.addListener(new ChangeListener() {
			@Override
			public void changed(ChangeEvent event, Actor actor) {
				// TODO Auto-generated method stub
				setScreen(new MenuScreen());
			}
		});

		ui.add(button).width(150);
	}

	@Override
	public void render() {
		root.draw();
	}

	@Override
	public void update() {
		root.act();
	}

}

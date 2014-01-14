package seprini.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Table;

public class EndScreen extends Screen {

	private final Stage root;

	public EndScreen() {
		root = new Stage();
		Gdx.input.setInputProcessor(root);

		Table ui = new Table();

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
	}

	@Override
	public void render() {
		root.draw();
		drawString(
				"PLANES HAVE COLLIDED OMG\nPRESS ESC TO RETURN TO MAIN MENU",
				Screen.WIDTH / 2,
				Screen.HEIGHT / 2, Color.BLACK, root.getSpriteBatch());
	}

	@Override
	public void update() {
		root.act();
	}

}

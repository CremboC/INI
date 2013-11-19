package uk.ac.york.ini.atc;

import uk.ac.york.ini.atc.handlers.Input;
import uk.ac.york.ini.atc.screens.Screen;
import uk.ac.york.ini.atc.screens.TitleScreen;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class ATC implements ApplicationListener {

    private OrthographicCamera camera;
    private SpriteBatch batch;
    private Texture texture;
    private Sprite map;
    private BitmapFont font;

    public Input input = new Input();
    public Screen screen;

    private final Vector2 topLeft = new Vector2(-640, 360);
    private final Vector2 topRight = new Vector2(640, 360);
    private final Vector2 bottomLeft = new Vector2(-640, -360);
    private final Vector2 bottomRight = new Vector2(640, -360);

    @Override
    public void create() {
	Gdx.input.setInputProcessor(input);

	setScreen(new TitleScreen());
    }

    public void setScreen(Screen newScreen) {
	if (screen != null)
	    screen.removed();
	screen = newScreen;
	if (screen != null)
	    screen.init(this);
    }

    @Override
    public void dispose() {
	batch.dispose();
	texture.dispose();
    }

    @Override
    public void render() {

	// Should be in update method
	// System.out.println(Gdx.input.getX());
	// System.out.println(Gdx.input.getY());

	Gdx.gl.glClearColor(1, 1, 1, 1);
	Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);

	screen.render();
    }

    @Override
    public void resize(int width, int height) {
    }

    @Override
    public void pause() {
    }

    @Override
    public void resume() {
    }

}

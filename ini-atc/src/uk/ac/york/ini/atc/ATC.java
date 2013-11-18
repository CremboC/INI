package uk.ac.york.ini.atc;

import uk.ac.york.ini.atc.screens.Screen;
import uk.ac.york.ini.atc.screens.TitleScreen;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL10;

public class ATC implements ApplicationListener {

    private boolean running = false;
    private Screen screen;

    private float accum = 0;

    @Override
    public void create() {
	Screen.width = Gdx.graphics.getWidth();
	Screen.height = Gdx.graphics.getHeight();

	running = true;

	setScreen(new TitleScreen());
    }

    @Override
    public void dispose() {

    }

    public void setScreen(Screen newScreen) {
	if (screen != null)
	    screen.removed();
	screen = newScreen;
	if (screen != null)
	    screen.init(this);
    }

    @Override
    public void render() {
	Gdx.gl.glClearColor(1, 1, 1, 1);
	Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);

	accum += Gdx.graphics.getDeltaTime();

	while (accum > 1.0f / 60.0f) {
	    screen.tick();
	    accum -= 1.0f / 60.0f;
	}

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

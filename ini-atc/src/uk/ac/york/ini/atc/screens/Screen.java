package uk.ac.york.ini.atc.screens;

import uk.ac.york.ini.atc.ATC;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

public abstract class Screen {

    public static final Vector2 topLeft = new Vector2(-640, 360);
    public static final Vector2 topRight = new Vector2(640, 360);
    public static final Vector2 bottomLeft = new Vector2(-640, -360);
    public static final Vector2 bottomRight = new Vector2(640, -360);

    public static int width;
    public static int height;

    private ATC atc;
    public SpriteBatch spriteBatch;
    protected OrthographicCamera camera;

    public void removed() {
	spriteBatch.dispose();
    }

    public final void init(ATC atc) {
	this.atc = atc;

	spriteBatch = new SpriteBatch();

	// camera = new OrthographicCamera(width, height);
    }

    protected void setScreen(Screen screen) {
	atc.setScreen(screen);
    }

    public void draw(TextureRegion region, int x, int y) {
	int width = region.getRegionWidth();

	if (width < 0)
	    width = -width;

	spriteBatch.draw(region, x, y, width, region.getRegionHeight());
    }

    public void drawSprite(Sprite sprite) {
	sprite.draw(spriteBatch);
    }

    public void drawString(CharSequence str, float x, float y, Color color) {
	final BitmapFont font = new BitmapFont();
	font.setColor(color);
	font.draw(spriteBatch, str, x, y);
    }

    public abstract void render();

    public void tick() {

    }

}

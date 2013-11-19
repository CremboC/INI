package uk.ac.york.ini.atc.screens;

import uk.ac.york.ini.atc.ATC;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public abstract class Screen {

    public static int width;
    public static int height;

    private ATC atc;
    protected SpriteBatch spriteBatch;
    protected OrthographicCamera camera;

    /**
     * Dispose of unused resources
     */
    public void removed() {
	spriteBatch.dispose();
    }

    /**
     * Initialise all the required default parameters
     * 
     * @param atc
     */
    public final void init(ATC atc) {
	this.atc = atc;

	// can be used for a custom coordinate system
	// Matrix4 projection = new Matrix4();
	// projection.setToOrtho(0, 1280, 720, 0, 0, 0);

	spriteBatch = new SpriteBatch();
	// use in conjunction with the above commented code
	// spriteBatch.setProjectionMatrix(projection);
    }

    /**
     * Convenience method for the main setScreen method
     * 
     * @param screen
     */
    protected void setScreen(Screen screen) {
	atc.setScreen(screen);
    }

    /**
     * Draw a texture from a texture region
     * 
     * @param region
     * @param x
     * @param y
     */
    public void draw(TextureRegion region, int x, int y) {
	int width = region.getRegionWidth();

	if (width < 0)
	    width = -width;

	spriteBatch.draw(region, x, y, width, region.getRegionHeight());
    }

    /**
     * Draw a texture from a sprite
     * 
     * @param sprite
     */
    public void drawSprite(Sprite sprite) {
	sprite.draw(spriteBatch);
    }

    /**
     * Draws a string using the default 15pt Arial font
     * 
     * @param str
     * @param x
     * @param y
     * @param color
     */
    public void drawString(CharSequence str, float x, float y, Color color) {
	final BitmapFont font = new BitmapFont();
	font.setColor(color);
	font.draw(spriteBatch, str, x, y);
    }

    public abstract void render();

    public void update() {

    }

}

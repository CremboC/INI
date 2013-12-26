package uk.ac.york.ini.atc.data;

import java.util.Hashtable;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Pixmap.Format;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;

/**
 * Loads all the requires art (textures), essentially pre-loading all the
 * required texture for later use
 * 
 * @author Crembo
 * 
 */
public class Art {

	/**
	 * A hashtable which stores all of the textures
	 */
	private final static Hashtable<String, TextureRegion> textures = new Hashtable<String, TextureRegion>();

	/**
	 * A skin can be loaded via JSON or defined programmatically, either is
	 * fine. Using a skin is optional but strongly recommended solely for the
	 * convenience of getting a texture, region, etc as a drawable, tinted
	 * drawable, etc.
	 */
	private final static Skin skin = new Skin();

	/**
	 * Initialises loading of texture, should be called once
	 * 
	 */
	public static void load() {
		// loads the whole sprite which consists most of the game's textures
		Texture combined = loadTexture("data/combinedgraphics.png");
		combined.setFilter(TextureFilter.MipMapLinearNearest,
				TextureFilter.Linear);

		// splits up the sprite into parts and loads them into the table
		textures.put("airspace", split(combined, 0, 0, 1080, 720));
		textures.put("aircraft", split(combined, 1080, 0, 75, 63));
		textures.put("waypoint", split(combined, 1155, 0, 20, 20));
		textures.put("menuAircraft", split(combined, 1080, 64, 727, 249));

		// load the default skin
		loadSkin();
	}

	/**
	 * Loads the default skin
	 */
	private static void loadSkin() {
		// Generate a 1x1 white texture and store it in the skin named "white".
		Pixmap pixmap = new Pixmap(1, 1, Format.RGBA8888);
		pixmap.setColor(Color.WHITE);
		pixmap.fill();
		skin.add("white", new Texture(pixmap));
		
		// Add the font to the skin; TODO: use a different one?
		BitmapFont font = new BitmapFont();
		skin.add("default", font);

		// Configure a TextButtonStyle and name it "default". Skin resources are
		// stored by type, so this doesn't overwrite the font.
		TextButtonStyle textButtonStyle = new TextButtonStyle();
		textButtonStyle.up = skin.newDrawable("white", 0.07f, 0.1f, 0.22f, 1);
		textButtonStyle.down = skin.newDrawable("white", Color.DARK_GRAY);
		textButtonStyle.checked = skin.newDrawable("white", Color.BLUE);
		textButtonStyle.over = skin.newDrawable("white", Color.LIGHT_GRAY);
		textButtonStyle.font = skin.getFont("default");
		skin.add("default", textButtonStyle);

		// labelStyle with background
		LabelStyle labelStyle = new LabelStyle();
		labelStyle.font = skin.getFont("default");
		labelStyle.background = skin.newDrawable("white", Color.DARK_GRAY);
		skin.add("default", labelStyle);

		// labelStyle without a background and black text
		LabelStyle textStyle = new LabelStyle();
		textStyle.font = skin.getFont("default");
		textStyle.fontColor = Color.BLACK;
		skin.add("textStyle", textStyle);

	}

	/**
	 * Splits a texture from given position with size and width
	 * 
	 * @param texture
	 * @param x
	 * @param y
	 * @param size
	 * @param width
	 * @return The requested TextureRegion
	 */
	private static TextureRegion split(Texture texture, int x, int y, int size,
			int width) {
		return new TextureRegion(texture, x, y, size, width);
	}

	/**
	 * Wrapper for loading a texture
	 * 
	 * @param textureName
	 * @return Texture
	 */
	private static Texture loadTexture(String textureName) {
		Texture texture = new Texture(Gdx.files.internal(textureName), true);
		return texture;
	}

	/**
	 * Loads a texture and returns a TextureRegion from taken from 0, 0
	 * 
	 * @param name
	 * @param width
	 * @param height
	 * @return
	 */
	private static TextureRegion load(String name, int width, int height) {
		Texture texture = new Texture(Gdx.files.internal(name));
		TextureRegion region = new TextureRegion(texture, 0, 0, width, height);
		return region;
	}

	/**
	 * Returns a texture region, should be used for all drawing all models
	 * 
	 * @param key
	 * @return the required texture region or null if doesn't exist
	 */
	public static TextureRegion getTextureRegion(String key) {
		if (!textures.containsKey(key))
			return null;

		return textures.get(key);
	}

	public static Skin getSkin() {
		return skin;
	}
}

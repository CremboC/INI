package uk.ac.york.ini.atc.handlers;

import java.util.Hashtable;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

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
	 * Initialises loading of texture, should be called once
	 */
	public static void load() {
		Texture combined = loadTexture("data/combinedgraphics.png");

		textures.put("airspace", split(combined, 0, 0, 1280, 720));
		textures.put("aircraft", split(combined, 1281, 0, 76, 63));
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
		Texture texture = new Texture(Gdx.files.internal(textureName));
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
	 * @return
	 */
	public static TextureRegion getTextureRegion(String key) {
		if (!textures.containsKey(key))
			return null;

		return textures.get(key);
	}

}

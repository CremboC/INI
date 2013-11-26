package uk.ac.york.ini.atc.handlers;

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

    public static TextureRegion gdx;

    public static void load() {
	gdx = load("data/libgdx.png", 512, 270);
    }

    public static TextureRegion load(String name, int width, int height) {
	Texture texture = new Texture(Gdx.files.internal(name));
	TextureRegion region = new TextureRegion(texture, 0, 0, width, height);
	return region;
    }

}

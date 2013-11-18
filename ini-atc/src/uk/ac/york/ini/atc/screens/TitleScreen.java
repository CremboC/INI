package uk.ac.york.ini.atc.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class TitleScreen extends Screen {

    private Texture texture;
    private Sprite sprite;
    private BitmapFont font;

    private int time = 0;

    @Override
    public void render() {

	spriteBatch.setProjectionMatrix(camera.combined);
	spriteBatch.begin();

	texture = new Texture(Gdx.files.internal("data/nisairspace.png"));
	texture.setFilter(TextureFilter.Linear, TextureFilter.Linear);

	TextureRegion region = new TextureRegion(texture, 0, 0, 1280, 720);

	sprite = new Sprite(region);
	sprite.setPosition(bottomLeft.x, bottomLeft.y);

	drawSprite(sprite);

	drawString("fps: " + Gdx.graphics.getFramesPerSecond(), topLeft.x + 10,
		topLeft.y - 10, Color.BLACK);

	spriteBatch.end();
    }

    @Override
    public void tick() {
	time++;
    }

}

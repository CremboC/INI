package uk.ac.york.ini.atc.models;

import uk.ac.york.ini.atc.data.Config;
import uk.ac.york.ini.atc.handlers.Art;

import com.badlogic.gdx.math.Vector2;

public class Map extends Entity {

	public Map() {
		this.coords = new Vector2(0, 0);
		this.size = Config.AIRSPACE_SIZE;
		this.texture = Art.getTextureRegion("airspace");
	}

	@Override
	public void act() {

	}
}
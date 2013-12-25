package uk.ac.york.ini.atc.models;

import uk.ac.york.ini.atc.data.Art;
import uk.ac.york.ini.atc.data.Config;

import com.badlogic.gdx.math.Vector2;

public class Map extends Entity {

	public Map() {
		this.coords = new Vector2(Config.AIRSPACE_SIZE.x / 2,
				Config.AIRSPACE_SIZE.y / 2);
		this.size = Config.AIRSPACE_SIZE;
		this.texture = Art.getTextureRegion("airspace");
	}

}

package seprini.models;

import seprini.data.Art;
import seprini.data.Config;

import com.badlogic.gdx.math.Vector2;

public final class Map extends Entity {
	public Map() {
		this.coords = new Vector2(Config.AIRSPACE_SIZE.x / 2,
				Config.AIRSPACE_SIZE.y / 2);
		this.size = Config.AIRSPACE_SIZE;
		this.texture = Art.getTextureRegion("airspace");
	}
}

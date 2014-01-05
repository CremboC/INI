package seprini.models;

import seprini.data.Art;
import seprini.screens.Screen;

import com.badlogic.gdx.math.Vector2;

public class MenuAircraft extends Entity {

	public MenuAircraft() {
		this.coords = new Vector2((int) (Screen.width / 2), 520);
		this.size = new Vector2(727, 249);
		this.texture = Art.getTextureRegion("menuAircraft");
	}

}

package uk.ac.york.ini.atc.models;

import uk.ac.york.ini.atc.handlers.Art;
import uk.ac.york.ini.atc.screens.Screen;

import com.badlogic.gdx.math.Vector2;

public class MenuAircraft extends Entity {

	public MenuAircraft() {
		this.coords = new Vector2((int) (Screen.width / 2), 520);
		this.size = new Vector2(727, 249);
		this.texture = Art.getTextureRegion("menuAircraft");
	}

	@Override
	public void act() {

	}

}

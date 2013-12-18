package uk.ac.york.ini.atc.data;

import com.badlogic.gdx.math.Vector2;

public final class Config {

	// General, graphics related settings
	public final static String GAME_TITLE = "Controller Concern";
	public final static int SCREEN_WIDTH = 1280;
	public final static int SCREEN_HEIGHT = 720;
	public final static boolean VSYNC = true;
	// DANGER, changing it may lead to unexpected results
	public final static boolean RESIZABLE = false;

	// Art related config
	public final static Vector2 AIRSPACE_SIZE = new Vector2(1080, 720);
	public final static Vector2 WAYPOINT_SIZE = new Vector2(20, 20);

}

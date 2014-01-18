package seprini.data;

import com.badlogic.gdx.math.Vector2;

public final class Config {

	// Used for debugging all around
	public final static boolean DEBUG_UI = false;
	public final static boolean DEBUG_TEXT = true;

	// General, graphics related settings
	public final static String GAME_TITLE = "Controller Concern";
	public final static int SCREEN_WIDTH = 1280;
	public final static int SCREEN_HEIGHT = 720;
	public final static boolean VSYNC = true;
	// DANGER, changing it may lead to unexpected results
	public final static boolean RESIZABLE = false;

	// Art related config
	public final static Vector2 AIRSPACE_SIZE = new Vector2(1080, SCREEN_HEIGHT);
	public final static Vector2 WAYPOINT_SIZE = new Vector2(20, 20);
	public final static Vector2 EXIT_WAYPOINT_SIZE = new Vector2(50, 50);

	// UI related
	public final static Vector2 SIDEBAR_SIZE = new Vector2(200, SCREEN_HEIGHT);

	// Game related
	public final static int USER_WAYPOINT_LIMIT = 10;
	public final static float AIRCRAFT_SPEED_MULTIPLIER = 800f;
	public final static int MIN_ALTITUDE_DIFFERENCE = 1000;
	public static final int[] ALTITUDES = { 5000, 10000, 15000 };

}

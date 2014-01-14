package seprini.data;

import com.badlogic.gdx.Gdx;

public class State {

	public static boolean paused = false;
	private static float time = 0;

	/**
	 * Tick time; time += delta
	 */
	public static void tick() {
		time += Gdx.graphics.getDeltaTime();
	}

	/**
	 * Get time
	 * 
	 * @return delta time added every frame
	 */
	public static float time() {
		return time;
	}

	/**
	 * Reset timer
	 */
	public static void reset() {
		time = 0;
	}

}

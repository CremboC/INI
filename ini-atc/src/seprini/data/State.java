package seprini.data;

import com.badlogic.gdx.Gdx;

public class State {

	public static boolean paused = false;
	public static float time;

	public static void tick() {
		time += Gdx.graphics.getDeltaTime();
	}

}

package uk.ac.york.ini.atc;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

public class Main {
	public static void main(String[] args) {
		LwjglApplicationConfiguration cfg = new LwjglApplicationConfiguration();
		cfg.title = "Controller Concern";
		cfg.useGL20 = false;
		cfg.width = 1280;
		cfg.height = 720;

		cfg.resizable = false;

		cfg.vSyncEnabled = true;

		new LwjglApplication(new ATC(), cfg);
	}
}

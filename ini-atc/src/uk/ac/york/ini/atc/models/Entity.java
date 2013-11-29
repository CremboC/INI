package uk.ac.york.ini.atc.models;

import uk.ac.york.ini.atc.handlers.Input;

import com.badlogic.gdx.scenes.scene2d.Actor;

public abstract class Entity extends Actor {

	public Entity() {
		// TODO Auto-generated constructor stub
	}

	public abstract void update(Input input);

	public abstract void draw();

}

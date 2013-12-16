package uk.ac.york.ini.atc.tests;

import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;

import uk.ac.york.ini.atc.screens.MenuScreen;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Array;

public class MenuScreenTest {

	MenuScreen screen;

	@Before
	public void setUp() throws Exception {
		screen = new MenuScreen();
	}

	@Test
	public void testMenuScreen() {
		Array<Actor> stageActors = screen.getStage().getActors();

		if (stageActors.size != 1)
			fail("There should only be one actor on the stage - the Table");

		for (Actor actor : stageActors) {
			if (actor instanceof Table) {
				//
			}
		}

		screen.getStartButtonEasy();

		// fail("Not yet implemented");
	}
}

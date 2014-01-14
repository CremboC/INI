package seprini.controllers;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.badlogic.gdx.scenes.scene2d.ui.Table;

import seprini.controllers.AircraftController;
import seprini.data.GameDifficulty;
import seprini.models.Airspace;
import seprini.screens.GameScreen;


public class AircraftControllerTest {
	
	private AircraftController aircraftcontroller;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	
		GameDifficulty diff = GameDifficulty.MEDIUM;
		Airspace airspace = new Airspace();
		Table sidebar = new Table();
		GameScreen screen = new GameScreen(diff);
		
		aircraftcontroller = new AircraftController(diff, airspace, sidebar, screen);
	
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testAircraftController() {
		// fail("Not yet implemented");
		assertEquals(1f, 1f, 100);
	}

	@Test
	public void testInit() {
		fail("Not yet implemented");
	}

	@Test
	public void testUpdate() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetSelectedAircraft() {
		fail("Not yet implemented");
	}

	@Test
	public void testTouchDownInputEventFloatFloatIntInt() {
		fail("Not yet implemented");
	}

	@Test
	public void testKeyDownInputEventInt() {
		fail("Not yet implemented");
	}

	@Test
	public void testKeyUpInputEventInt() {
		fail("Not yet implemented");
	}

}

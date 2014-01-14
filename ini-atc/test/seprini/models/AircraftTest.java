package seprini.models;

import static org.junit.Assert.*;
import static org.junit.Assert.fail;

import java.util.ArrayList;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.badlogic.gdx.math.Vector2;

import seprini.data.Art;
import seprini.models.types.AircraftType;

public class AircraftTest {
	
	private Aircraft aircraft;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		AircraftType defaultAircraft = new AircraftType(); 


		defaultAircraft.setCoords(new Vector2(0, 0)).setActive(true)
		.setMaxClimbRate(0).setMaxSpeed(0.8f).setMaxTurningSpeed(0.4f)
		.setRadius(15).setSeparationRadius(100)
		.setTexture(Art.getTextureRegion("aircraft"))
		.setVelocity(new Vector2(0.8f, 0.8f));


		ArrayList<Waypoint> plan = new ArrayList<Waypoint>();
		plan.add(new Waypoint(3 , 5, true));
		plan.add(new Waypoint(4 , 7, true));


		aircraft = new Aircraft(defaultAircraft, plan, 0);

	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testAdditionalDraw() {
		fail("Not yet implemented");
	}

	@Test
	public void testAircraft() {
		fail("Not yet implemented");
	}

	@Test
	public void testAct() {
		fail("Not yet implemented");
	}

	@Test
	public void testInsertWaypoint() {
		fail("Not yet implemented");
	}

	@Test
	public void testDelay() {
		fail("Not yet implemented");
	}

	@Test
	public void testIncreaseAltitude() {
		fail("Not yet implemented");
	}

	@Test
	public void testDecreaseAltitude() {
		fail("Not yet implemented");
	}

	@Test
	public void testTurnRight() {
		fail("Not yet implemented");
	}

	@Test
	public void testTurnLeft() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetRadius() {
		
	assertEquals(15f, aircraft.getRadius(), 100);
	}

	@Test
	public void testGetCentreCoords() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetSeparationRadius() {
		fail("Not yet implemented");
	}

	@Test
	public void testIsBreaching() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetAltitude() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetSpeed() {
		fail("Not yet implemented");
	}

	@Test
	public void testIsActive() {
		fail("Not yet implemented");
	}

	@Test
	public void testSelected() {
		fail("Not yet implemented");
	}

	@Test
	public void testToString() {
		fail("Not yet implemented");
	}

}

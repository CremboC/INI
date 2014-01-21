package seprini.models;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import seprini.data.Art;
import seprini.models.types.AircraftType;

import com.badlogic.gdx.math.Vector2;

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
				.setMaxClimbRate(10).setMaxSpeed(0.8f).setMaxTurningSpeed(0.4f)
				.setRadius(15).setSeparationRadius(100)
				.setTexture(Art.getTextureRegion("aircraft"))
				.setVelocity(new Vector2(0.8f, 0.8f));

		ArrayList<Waypoint> plan = new ArrayList<Waypoint>();
		plan.add(new Waypoint(3, 5, true));
		plan.add(new Waypoint(4, 7, true));

		aircraft = new Aircraft(defaultAircraft, plan, 0);

	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testAdditionalDraw() {
		// fail("Not yet implemented");
	}

	@Test
	public void testAircraft() {
		// fail("Not yet implemented");
	}

	@Test
	public void testAct() {
		// fail("Not yet implemented");

	}

	@Test
	public void testInsertWaypoint() {
		Waypoint newWaypoint = new Waypoint(7, 8, true);
		aircraft.insertWaypoint(newWaypoint);
		assertEquals(aircraft.getFlightPlan().get(0), newWaypoint);

	}

	@Test
	public void testIncreaseSpeed() {
		aircraft.increaseSpeed();
		assertEquals(1.1f, aircraft.getSpeed(), 100);

	}

	@Test
	public void testDecreaseSpeed() {
		aircraft.decreaseSpeed();
		assertEquals(0.9f, aircraft.getSpeed(), 0);
	}

	@Test
	public void testIncreaseAltitude() {

	}

	@Test
	public void testDecreaseAltitude() {

	}

	@Test
	public void testTurnRight() {
	}

	@Test
	public void testTurnLeft() {
	}

	@Test
	public void testGetRadius() {

		assertEquals(15f, aircraft.getRadius(), 0);
	}

	@Test
	public void testGetSeparationRadius() {
		float result = aircraft.getSeparationRadius();
		assertEquals(result, 100, 0);
	}

	@Test
	public void testIsBreaching() {
	}

	@Test
	public void testGetAltitude() {
		float result = aircraft.getAltitude();
		if (result == 5000) {
			assertEquals(5000, result, 0);
		} else if (result == 10000) {
			assertEquals(10000, result, 0);
		} else if (result == 15000) {
			assertEquals(15000, result, 0);
		}
	}

	@Test
	public void testGetSpeed() {
		float result = aircraft.getSpeed();
		assertEquals(1f, result, 0);
	}

	@Test
	public void testIsActive() {
		assertTrue(aircraft.isActive());
	}

	@Test
	public void testSelected() {
	}

	@Test
	public void testToString() {
		ArrayList<Waypoint> plan = new ArrayList<Waypoint>();
		plan.add(new Waypoint(3, 5, true));

		assertTrue(("Aircraft - x: 3.0 y: 5.0\n\r flight plan: [Waypoint - x: 4.0 y: 7.0]")
				.equals(aircraft.toString()));
	}
}

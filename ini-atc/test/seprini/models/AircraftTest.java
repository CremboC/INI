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
		fail("Not yet implemented");
		/*
		Waypoint newWaypoint = new Waypoint(7 , 8, true);
		aircraft.insertWaypoint(newWaypoint);
		assertEquals(aircraft.waypoints(1), newWaypoint);
		*/
	}

	@Test
	public void testIncreaseSpeed() {
		// fail("Not yet implemented");
		aircraft.increaseSpeed();
		assertEquals(1.1f*700f, aircraft.getSpeed(), 100);
	}
	
	@Test
	public void testDecreaseSpeed() {
		aircraft.decreaseSpeed();
		assertEquals(0.9f*700f, aircraft.getSpeed(), 0);
	}

	@Test
	public void testIncreaseAltitude() {
		// fail("Not yet implemented");
		aircraft.increaseAltitude();
		float result = aircraft.getAltitude();
		assertEquals(5f, result, 0);
	}

	@Test
	public void testDecreaseAltitude() {
		//fail("Not yet implemented");
		aircraft.increaseAltitude();
		aircraft.decreaseAltitude();
		float result = aircraft.getAltitude();
		assertEquals(0f, result, 0);
	}

	@Test
	public void testTurnRight() {
		//fail("Not yet implemented");
	}

	@Test
	public void testTurnLeft() {
		//fail("Not yet implemented");
	}

	@Test
	public void testGetRadius() {
		
	assertEquals(15f, aircraft.getRadius(), 0);
	}

	@Test
	public void testGetCentreCoords() {
		//fail("Not yet implemented");
		Vector2 result = aircraft.getCentreCoords();
		Vector2 testAnswer = new Vector2(41f, 36.5f);
		assertEquals(testAnswer.x, result.x, 0);
		assertEquals(testAnswer.y, result.y, 0);
	}

	@Test
	public void testGetSeparationRadius() {
		// fail("Not yet implemented");
		float result = aircraft.getSeparationRadius();
		assertEquals(result, 100, 0);
	}

	@Test
	public void testIsBreaching() {
		// fail("Not yet implemented");
	}

	@Test
	public void testGetAltitude() {
		// fail("Not yet implemented");
		float result = aircraft.getAltitude();
		assertEquals(0f, result, 0);
	}

	@Test
	public void testGetSpeed() {
		// fail("Not yet implemented");
		float result = aircraft.getSpeed();
		assertEquals(700f, result, 0);	
	}

	@Test
	public void testIsActive() { // Not done yet, code not fixed
		fail("Not yet implemented");
	}

	@Test
	public void testSelected() {
		// fail("Not yet implemented");
	}

	@Test
	public void testToString() { // NOT WORKING YET, Strings don't match
		// fail("Not yet implemented");
		ArrayList<Waypoint> plan = new ArrayList<Waypoint>();
		plan.add(new Waypoint(3 , 5, true));
		assertEquals("Aircraft - x: " + 3 + " y: " + 5 + "\n\r flight plan: " + plan.toString(), aircraft.toString(), 100);
	}

}

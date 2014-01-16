package seprini.models;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.badlogic.gdx.math.Vector2;

public class ExitpointTest {

	private Exitpoint exitpoint;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {

		Exitpoint testexitpoint = new Exitpoint(new Vector2(3f, 3f));
		testexitpoint.radius = 5;
		exitpoint = testexitpoint;

	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testToString() {
		assertTrue(("Exitpoint - x: 3.0 y: 3.0").equals(exitpoint.toString()));
	}

	@Test
	public void testExitpoint() {
		Vector2 testcoordinates = new Vector2(3f, 3f);
		Vector2 result = exitpoint.coords;
		assertEquals(testcoordinates.x, result.x, 0);
		assertEquals(testcoordinates.y, result.y, 0);
	}

}

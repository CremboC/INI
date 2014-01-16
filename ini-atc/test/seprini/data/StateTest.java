package seprini.data;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class StateTest {

	private State testState;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		testState = new State();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testTick() {
		// fail("Not yet implemented");
	}

	@Test
	public void testTime() {
		// fail("Not yet implemented");
		assertEquals(testState.time(), 0f, 0);
	}

	@Test
	public void testReset() {
		// fail("Not yet implemented");
		testState.reset();
		assertEquals(testState.time(), 0f, 0);

	}
}

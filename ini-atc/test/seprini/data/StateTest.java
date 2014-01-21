package seprini.data;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class StateTest {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testTick() {
	}

	@Test
	public void testTime() {
		assertEquals(State.time(), 0f, 0);
	}

	@Test
	public void testReset() {
		State.reset();
		assertEquals(State.time(), 0f, 0);

	}
}

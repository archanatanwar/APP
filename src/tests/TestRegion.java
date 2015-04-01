package tests;

import static org.junit.Assert.*;

import java.util.Arrays;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import Game.Player;
import Game.Region;

public class TestRegion {
	private Region obj;
	private int minion;
	private int minionLater;

	@Before
	public void setUp() throws Exception {
		obj = new Region("Dolly Sisters", 1, 12, Arrays.asList(2, 3, 12));
	}

	@After
	public void tearDown() throws Exception {
		this.obj = null;
	}

	/**
	 * test constructor
	 */
	@Test
	public void testConstructor() {
		try {
			String rName = obj.getRegionName();
			minion = obj.getMinion();
			assertTrue(rName == "Dolly Sisters");
		} catch (Exception e) {
			fail("Should not throw exception. Message: " + e.getMessage());
		}
	}

	/**
	 * test placeDefaultMinion function
	 */
	@Test
	public void testPlaceDeafaultMinion() {
		try {
			obj.placeDefaultMinion("red");
			minionLater = obj.getMinion();
			assertNotSame(this.minion, this.minionLater);
		} catch (Exception e) {
			fail("Should not throw exception. Message: " + e.getMessage());
		}
	}

	/**
	 * test placeDemon method
	 */
	@Test
	public void testplaceDemon() {
		try {
			int result = obj.placeDemon();
			assertTrue(result == 1);
		} catch (Exception e) {
			fail("Should not throw exception. Message: " + e.getMessage());
		}
	}

	/**
	 * test removeDemon method
	 */
	@Test
	public void testremoveDemon() {
		try {
			obj.placeDemon();
			int result = obj.removeDemon();
			assertTrue(result == 1);
		} catch (Exception e) {
			fail("Should not throw exception. Message: " + e.getMessage());
		}
	}

	/**
	 * test placeTroll method
	 */
	@Test
	public void testplaceTroll() {
		try {
			int result = obj.placeTroll();
			assertTrue(result == 1);
		} catch (Exception e) {
			fail("Should not throw exception. Message: " + e.getMessage());
		}
	}

	/**
	 * test removeTrollmethod
	 */
	@Test
	public void testremoveTroll() {
		try {
			obj.placeTroll();
			int result = obj.removeTroll();
			assertTrue(result == 1);
		} catch (Exception e) {
			fail("Should not throw exception. Message: " + e.getMessage());
		}
	}

	/**
	 * test placeTroubleMarker method
	 */
	@Test
	public void testplaceTroubleMarker() {
		try {
			int result = obj.placeTroubleMarker();
			assertTrue(result == 1);
		} catch (Exception e) {
			fail("Should not throw exception. Message: " + e.getMessage());
		}
	}

	/**
	 * test removeTroubleMarker method
	 */
	@Test
	public void testremoveTroubleMarker() {
		try {
			obj.placeTroubleMarker();
			obj.removeTroubleMarker();
			assertTrue(obj.getTMarker() == 0);
		} catch (Exception e) {
			fail("Should not throw exception. Message: " + e.getMessage());
		}
	}
}

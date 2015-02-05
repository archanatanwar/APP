package tests;

import static org.junit.Assert.*;

/**
 * Test class Region
 */

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import Game.Region;
public class TestRegion {
	private Region obj;
	private int minion;
	private int minionLater;
	@Before
	public void setUp() throws Exception {
		obj = new Region("Dolly Sisters", 1, 12);
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
		try{
			String rName = obj.getRegionName();
			minion = obj.getMinion();
			assertTrue(rName == "Dolly Sisters");
		}
		catch(Exception e){
			fail("Should not throw exception. Message: " + e.getMessage());
		}
	}
	
	/**
	 * test placeDefaultMinion function
	 */
	@Test 
	public void testPlaceDeafaultMinion() {
		try{
			obj.placeDefaultMinion("red");
			minionLater = obj.getMinion();
			assertNotSame(this.minion, this.minionLater);
		}
		catch(Exception e){
			fail("Should not throw exception. Message: " + e.getMessage());
		}
	}

}

package tests;

import static org.junit.Assert.*;
import Game.Player;
import Game.GameEngine;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import Game.PlayerCards;
/**
 * Test Player class
 * @author nav_k
 *
 */
public class TestPlayer {
	
	private Player obj;
	private PlayerCards obj2;
	private int minionHold;
	private int minionHoldLater;
	private int regionNum = 1;
	private int buildingHold;
	private int buildingHoldLater;
	private int result;
	@Before
	public void setUp() throws Exception {
		//obj = new Player("red","Lord_Rust");
	}

	@After
	public void tearDown() throws Exception {
		this.obj = null;
	}
	
	/**
	 * check the constructor
	 */
	@Test
	public void testPlayer() {
		try{
			obj = new Player("red","Lord_Rust");
			String pColor = obj.getColor();
			assertTrue(pColor == "red" );
		}
		catch(Exception e){
			fail("Should not throw exception. Message: " + e.getMessage());
		}
	}
	
	/**
	 * test initializePlayer method
	 */
	@Test
	public void testInitializePlayer() {
		try{
			obj = new Player("red","Lord_Rust");
			obj2.createPlayerCardsDeck();
			obj.initialisePlayer();
			minionHold = obj.getMinion();
			assertNotNull(obj.getMinion());
		}
		catch(Exception e){
			fail("Should not throw exception. Message: " + e.getMessage());
		}
	}
	
	/**
	 * test placeMinion method
	 */
	@Test
	public void testplaceMinion() {
		try{
			obj = new Player("red","Lord_Rust");
			obj.initialisePlayer();
			minionHold = obj.getMinion();
			obj.placeMinion(regionNum);
			minionHoldLater = obj.getMinion();
			
			assertTrue(minionHold != minionHoldLater);
		}
		catch(Exception e){
			fail("Should not throw exception. Message: " + e.getMessage());
		}
	}
	
	/**
	 * test removeMinion method
	 */
	@Test
	public void testremoveMinion() {
		try{
			obj = new Player("red","Lord_Rust");
			obj.initialisePlayer();
			obj.placeMinion(regionNum);
			minionHold = obj.getMinion();
			obj.removeMinion(regionNum);
			minionHoldLater = obj.getMinion();
			assertFalse(minionHold == minionHoldLater);
		}
		catch(Exception e){
			fail("Should not throw exception. Message: " + e.getMessage());
		}
	}
	
	/**
	 * test initialPlayerStatus method
	 */
	@Test
	public void testinitialPlayerStatus() {
		try{
			obj = new Player("red","Lord_Rust");
			//obj.initialisePlayer();
			minionHold = obj.getMinion();
			obj.initialPlayerStatus();
			minionHoldLater = obj.getMinion();
			assertTrue((minionHold-3) == minionHoldLater);
		}
		catch(Exception e){
			fail("Should not throw exception. Message: " + e.getMessage());
		}
	}
	
	/**
	 * test placeBuilding method
	 */
	@Test
	public void testplaceBuildingMinion() {
		try{
			obj = new Player("red","Lord_Rust");
			obj.initialisePlayer();
			obj.placeMinion(regionNum);
			buildingHold = obj.getBuilding();
			obj.placeBuilding(regionNum);
			buildingHoldLater = obj.getBuilding();
			assertTrue(buildingHold != buildingHoldLater);
		}
		catch(Exception e){
			fail("Should not throw exception. Message: " + e.getMessage());
		}
	}
	
	/**
	 * test removeBuilding method
	 */
	@Test
	public void testremoveBuildingMinion() {
		try{
			obj = new Player("red","Lord_Rust");
			obj.initialisePlayer();
			obj.placeMinion(regionNum);
			obj.placeBuilding(regionNum);
			buildingHold = obj.getBuilding();
			obj.removeBuilding(regionNum);
			buildingHoldLater = obj.getBuilding();
			assertTrue(buildingHold != buildingHoldLater);
		}
		catch(Exception e){
			fail("Should not throw exception. Message: " + e.getMessage());
		}
	}
	
	/**
	 * test checkMinionMove method
	 */
	@Test
	public void testcheckMinionMove() {
		try{
			obj = new Player("red","Lord_Rust");
			obj.initialisePlayer();
			result = obj.checkMinionMove(regionNum);
			assertTrue(result == 0);
		}
		catch(Exception e){
			fail("Should not throw exception. Message: " + e.getMessage());
		}
	}
	
	/**
	 * test checkMinionMove method
	 */
	@Test
	public void testcheckMinionMove1() {
		try{
			obj = new Player("red","Lord_Rust");
			obj.initialisePlayer();
			obj.placeMinion(regionNum);
			result = obj.checkMinionMove(regionNum);
			assertTrue(result == 1);
		}
		catch(Exception e){
			fail("Should not throw exception. Message: " + e.getMessage());
		}
	}
	
}

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
	
	@Before
	public void setUp() throws Exception {
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
			minionHold = obj.getMinion();
			obj.placeMinion(regionNum);
			minionHoldLater = obj.getMinion();
			assertNotNull(obj.getMinion());
		}
		catch(Exception e){
			fail("Should not throw exception. Message: " + e.getMessage());
		}
	}
}

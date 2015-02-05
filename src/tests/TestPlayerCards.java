package tests;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;

import Game.PlayerCards;
/**
 * Test class for Player Cards
 * @author nav_k
 *
 */
public class TestPlayerCards {
	
	private PlayerCards obj;
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		obj = new PlayerCards();
	}

	@After
	public void tearDown() throws Exception {
		this.obj = null;
	}
	
	/**
	 * test createPlayerCardsDeck method
	 * should not throw exceptions
	 */
	@Test
	public void testCreatePlayerCardsDeck() {
		try{
			obj.createPlayerCardsDeck();
			assertTrue(obj.playerCards.get("Green").contains(23));
			assertFalse(obj.playerCards.get("Green").contains(50));
		}
		catch(Exception e){
			fail("Should not throw exception. Message: " + e.getMessage());
		}
	}
	
	/**
	 * test getPlayerCard method
	 * should not throw exceptions
	 */
	@Test
	public void testGetPlayerCard() {
		try{
			Game.Pair pair = obj.getPlayerCard();
			int num = pair.getCard();
			assertFalse(obj.playerCards.get("Green").contains(num));
		}
		catch(Exception e){
			fail("Should not throw exception. Message: " + e.getMessage());
		}
	}
	
}

package tests;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import Game.Player;
import Game.PlayerCards;

/**
 * Test class for Player Cards
 * 
 * @author nav_k
 *
 */
public class TestPlayerCards {
	private int beforeCall;
	private int afterCall;
	private static PlayerCards obj;
	private static Game.Player obj2;
	private static Game.Player obj1;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		obj = new PlayerCards();
		obj2 = new Player("yellow", "LORD_SELACHII");
		obj1 = new Player("red", "Lord_Rust");
		obj1.initialisePlayer();
		obj2.initialisePlayer();
		Game.GameUtility.playerObjList.add(obj1);
		Game.GameUtility.playerObjList.add(obj2);
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {

	}

	/*
	 * @Before public void setUp() throws Exception {
	 * System.out.println("setUp--------"); obj = new PlayerCards(); obj2 = new
	 * Player("yellow","LORD_SELACHII"); obj1 = new Player("red","Lord_Rust");
	 * obj1.initialisePlayer(); obj2.initialisePlayer();
	 * 
	 * }
	 * 
	 * @After public void tearDown() throws Exception {
	 * System.out.println("tearDown--------"); }
	 */

	/**
	 * test createPlayerCardsDeck method should not throw exceptions
	 */
	@Test
	public void testCreatePlayerCardsDeck() {
		try {

			obj.createPlayerCardsDeck();
			assertTrue(obj.playerCards.get("Green").contains(23));
			assertFalse(obj.playerCards.get("Green").contains(50));
		} catch (Exception e) {
			fail("Should not throw exception. Message: " + e.getMessage());
		}
	}

	/**
	 * test getPlayerCard method should not throw exceptions
	 */
	@Test
	public void testGetPlayerCard() {
		try {
			Game.Pair pair = obj.getPlayerCard();
			String num = pair.getCard();
			assertFalse(obj.playerCards.get("Green").contains(num));
		} catch (Exception e) {
			fail("Should not throw exception. Message: " + e.getMessage());
		}
	}

	/**
	 * test payToBank method
	 */
	@Test
	public void testpayToBank() {
		try {
			obj1.setTurn(1);
			obj2.setTurn(0);
			beforeCall = Game.GameEngine.BankHold;
			obj.payToBank(2);
			afterCall = Game.GameEngine.BankHold;
			assertTrue((beforeCall + 2) == afterCall);
		} catch (Exception e) {
			fail("Should not throw exception. Message: " + e.getMessage());
		}
	}

	/**
	 * test takeMoneyFromOtherPlayers method
	 */
	@Test
	public void testtakeMoneyFromOtherPlayers() {
		try {
			obj1.setTurn(1);
			obj1.setPNumber(2);
			obj2.setTurn(0);
			obj2.setPNumber(1);
			beforeCall = obj2.getCash();
			obj.takeMoneyFromOtherPlayers(3, 1);
			afterCall = obj2.getCash();
			// System.out.println("Before--------"+beforeCall+"-----After------"+afterCall);
			assertTrue(beforeCall == (afterCall + 3));

		} catch (Exception e) {
			fail("Should not throw exception. Message: " + e.getMessage());
		}
	}

	/**
	 * test takeMoneyFromEveryPlayer method
	 */
	@Test
	public void testtakeMoneyFromEveryPlayer() {
		try {
			obj1.setTurn(1);
			obj1.setPNumber(2);
			obj2.setTurn(0);
			obj2.setPNumber(1);
			beforeCall = obj1.getCash();
			obj.takeMoneyFromEveryPlayer(4);
			afterCall = obj1.getCash();
			assertTrue(beforeCall == (afterCall - 4));

		} catch (Exception e) {
			fail("Should not throw exception. Message: " + e.getMessage());
		}
	}

}

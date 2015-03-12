package tests;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import Game.CityAreaCards;
import Game.Player;
import Game.PlayerCards;

public class TestCityAreaCards {
	private int beforeCall;
	private int afterCall;
	private static CityAreaCards obj;
	private static Game.Player obj2 ;
	private static Game.Player obj1 ;
	
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		obj = new CityAreaCards();
		obj2 = new Player("yellow","LORD_SELACHII");
		obj1 = new Player("red","Lord_Rust");
		Game.GameEngine.playerObjList.add(obj1);
		Game.GameEngine.playerObjList.add(obj2);
	}
	
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		
	}
	/**
	 * test takeMoneyFromBank method
	 */
	@Test
	public void testtakeMoneyFromBank() {
		try{			
			obj1.setTurn(1);
			obj2.setTurn(0);
			beforeCall = Game.GameEngine.BankHold;
			obj.takeMoneyFromBank(2);
			afterCall = Game.GameEngine.BankHold;
			assertTrue((beforeCall-2) == afterCall);
		}
		catch(Exception e){
			fail("Should not throw exception. Message: " + e.getMessage());
		}
	}
	
	/**
	 * test giveMoneyToBank method
	 */
	@Test
	public void testgiveMoneyToBank() {
		try{			
			obj1.setTurn(1);
			obj2.setTurn(0);
			beforeCall = Game.GameEngine.BankHold;
			obj.giveMoneyToBank(2);
			afterCall = Game.GameEngine.BankHold;
			assertTrue((beforeCall+2) == afterCall);
		}
		catch(Exception e){
			fail("Should not throw exception. Message: " + e.getMessage());
		}
	}

}

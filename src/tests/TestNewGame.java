package tests;

import static org.junit.Assert.*;

import javax.swing.ImageIcon;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import Game.NewGame;

public class TestNewGame {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test 
	/**
	 * test getPlayerCardImage method
	 */
	public void testgetPlayerCardImage() {
		try{
			 NewGame obj = new NewGame();
			 ImageIcon result =  obj.getPlayerCardImage("MR_BOGGIS");
			 assertEquals(result.toString(),"PlayerCardImages/MrBoggis.png");
		}
		catch(Exception e){
			fail("Should not throw exception. Message: " + e.getMessage());
		}
	}

}

package Game;

import static org.junit.Assert.*;

import org.junit.Test;

public class TestPlayer {
	
	String personality_temp = PersonalityCards.shufflePersonalityCards();
	Player pRed = new Player("red", personality_temp);
	
	String personality_temp_2 = PersonalityCards.shufflePersonalityCards();
	Player pYellow = new Player("yellow", personality_temp_2);
	
	@Test
	/**
	 * Comparing the two players whether they are same or not
	 */
	public void testPlyer() {
		assertNotSame(pRed,pYellow);
	}
	
	@Test
	public void testPlayerConstructor() {
		try{
			
			String personality_temp_3 = PersonalityCards.shufflePersonalityCards();
			Player pGreen = new Player("green", personality_temp_3);
			assertTrue(pGreen.color.equals("green") && pGreen.personality.equals(personality_temp_3));
			
		}
		catch(Exception e)
		{
			e.printStackTrace(); 
			fail("should not throw exception");
		}
	}

}

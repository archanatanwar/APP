package Game;

import static org.junit.Assert.*;

import org.junit.Test;

public class TestPersonalityCards {
	@Test
	
	public void testShufflePersonalityCards() {
		assertEquals(PersonalityCards.shufflePersonalityCards().getClass().toString(),"class java.lang.String");	
	}

}

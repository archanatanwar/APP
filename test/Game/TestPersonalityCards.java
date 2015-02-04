package Game;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import Game.PersonalityCards.getPersonalityCard;

public class TestPersonalityCards {
	
	PersonalityCards personality = new PersonalityCards();
	
	List<getPersonalityCard> shuffleListBeforeShuffle = personality.shuffleList;
	String personalityRemovedAfterShuffle = personality.shufflePersonalityCards();
	List<getPersonalityCard> shuffleListAfterShuffle = personality.shuffleList;
	
	@Test
	public void testPersonalityCardShuffling() {
		assertNotSame(shuffleListBeforeShuffle,shuffleListAfterShuffle);
	}
	
	@Test
	public void testPersonalityCardShufflingLength() {
		assertNotSame(shuffleListBeforeShuffle.size(),shuffleListAfterShuffle.size());
	}

}

package Game;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import Game.CityAreaCards.getCityAreaCard;
import Game.PersonalityCards.getPersonalityCard;

public class TestCityAreaCards {

	CityAreaCards cityArea = new CityAreaCards();
	
	List<getCityAreaCard> shuffleListBeforeShuffle = cityArea.shuffleList; 
	String cityRemoved = cityArea.shuffleCityAreaCards();
	List<getCityAreaCard> shuffleListAfterShuffle = cityArea.shuffleList; 
	
	@Test
	public void testPersonalityCardShuffling() {
		assertNotSame(shuffleListBeforeShuffle,shuffleListAfterShuffle);
	}
	
	@Test
	public void testPersonalityCardShufflingLength() {
		assertNotSame(shuffleListBeforeShuffle.size(),shuffleListAfterShuffle.size());
	}

}

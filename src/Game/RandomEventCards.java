package Game;

import java.util.*;

/**
 *
 * @author nav_k <h1>Represenation of Random Event Cards</h1>
 *         <p>
 *         Contains Random Cards in enum data structure and a method that <br>
 *         returns a Random Event Card whenever it is called i.e. for <br>
 *         the purpose of distributing cards
 *         </p>
 */
public abstract class RandomEventCards {
	/**
	 * <h1>Random Event cards enum</h1>
	 * <p>
	 * Contains Random Event card names to which details will be added
	 * </p>
	 * 
	 * @author nav_k
	 *
	 */
	public enum getRandomEventCard {
		THE_DRAGON, FLOOD, FIRE, FOG, RIOTS, EXPLOSION, MYSTERIOUS_MURDERS, DEMONS_FROM_THE_DUNGEONS_DIMENSIONS, SUBSIDENCE, BLOODY_STUPID_JOHNSON, TROLLS, EARTHQUAKE
	}

	// Contains all the values of enum
	public static List<getRandomEventCard> shuffleList = Arrays
			.asList(getRandomEventCard.values());
	public static List<getRandomEventCard> getRandomCardsName = Arrays
			.asList(getRandomEventCard.values());
	public static List<Integer> regionList = Arrays.asList(1, 2, 3, 4, 5, 6, 7,
			8, 9, 10, 11, 12);
	public static HashMap<String, List<String>> fogCards;
	static List<String> list = new ArrayList<>();

	/**
	 * Non parameterized function that returns name of random event card after
	 * shuffling the deck whenever a player requests for a particular random
	 * event Card
	 * 
	 * @return String returns name of Random Event Card
	 */
	public static String getRandomEventCard() {
		Collections.shuffle(shuffleList);
		getRandomEventCard valueToReturn = shuffleList.get(0);
		String result = valueToReturn.toString();
		// shuffleList.remove(0);
		shuffleList = new ArrayList<getRandomEventCard>(shuffleList);
		shuffleList.remove(0);
		return valueToReturn.toString();
	}

	public static int getRollDiceNumber() {
		int result = 0;
		Collections.shuffle(regionList);
		result = regionList.get(0);
		return result;
	}

	public abstract void executeRandomEvent();
}

package Game;

import java.util.*;
/**
 * <h1>Representation of Personality Cards</h1>
 *<p>Contains Personality Cards in enum data structure and a method that <br>
 * returns a Personality Card whenever it is called i.e. for<br>
 * the purpose of distributing cards 
 * </p>
 * @author nav_k
 *
 */
public class PersonalityCards 
{
	
	// Contains Personality card names to which details will be added
	public enum getPersonalityCard{
		LORD_VETINARI, LORD_SELACHII, LORD_RUST, LORD_DE_WORDE, DRAGON_KING_OF_ARMS, CHRYSOPRASE, COMMANDER_VIMES
	}
	
	// Contains all the values of enum
	public static List<getPersonalityCard> PersonalityList = Arrays.asList(getPersonalityCard.values());
	
	public static List<getPersonalityCard> getPersonalityNames = Arrays.asList(getPersonalityCard.values());

	/**
	 * Non parameterized function that returns
	 * name of personality card after shuffling the deck
	 * whenever a player requests for a particular Personality Card
	 * 
	 * @return String returns name of Personality Card
	 */
	public static String getPersonalityCard()
	{
		// shuffle values in list
		Collections.shuffle(PersonalityList);
		getPersonalityCard valueToReturn = PersonalityList.get(0);
		String result = valueToReturn.toString();
		//shuffleList.remove(0);
		PersonalityList = new ArrayList<getPersonalityCard>(PersonalityList);
		// remove personality card that gets
		// distributed to any player 
		PersonalityList.remove(0);
		return valueToReturn.toString();
	}
	
//	public static void main(String[] args)
//	{
//		System.out.println(shufflePersonalityCards());
//	}
}

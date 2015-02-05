package Game;

import java.util.*;
/**
 * <h1>Representation of Player Cards</h1>
 * <p>Contains Player Cards in HashMap data structure , method that<br>
 * creates the deck of cards and a method that <br>
 * returns a Player Card whenever it is called i.e. for
 * the purpose of distributing cards </p>
 * @author nav_k
 *
 */
public class PlayerCards
{
	
	// Contains Player card color as key and list of card numbers as value
	public static HashMap<String, List<Integer>> playerCards;
	
	/**
	 * method creates deck of brown and green cards as HashMap
	 * and two lists that shuffle and then gets added to HashMap
	 */
	public static void createPlayerCardsDeck()
	{
		playerCards = new HashMap<String, List<Integer>>();
		List<Integer> listForGreen = new ArrayList<>();
		List<Integer> listForBrown = new ArrayList<>();
		String Green = "Green";
		String Brown = "Brown";
		
		// add initial 48 cards for Green color list
		for(int i=1; i<=48; i++)
		{
			listForGreen.add(i);
		}
		
		// 53 cards for Brown color list
		for(int i=1; i<=53; i++)
		{
			listForBrown.add(i);
		}
		// shuffle values of lists
		Collections.shuffle(listForBrown);
		Collections.shuffle(listForGreen);
		
		// add values to map
		playerCards.put(Green, listForGreen);
		playerCards.put(Brown, listForBrown);
		
	}
	
	/**
	 * method that returns the playerCard values i.e. color and number
	 * @return Pair returns an object to a utility class Pair which in turn has <br>
	 *              data members card number and color
	 * 
	 */
	public static Pair getPlayerCard()
	{
		if(playerCards.isEmpty())
		{
			return new Pair(0,"empty");
		}
		// get key by color green first
		List<Integer> tempList = playerCards.get("Green");
		// green cards are finished
		if(tempList == null)
		{
			 tempList = playerCards.get("Brown");
			 int result = tempList.get(0);
			 tempList.remove(0);
			 playerCards.remove("Brown");
			 // make sure list is not empty
			 if(tempList.size() >= 1)
			 {
				// add again to the hashmap
				 playerCards.put("Brown", tempList);
			 }
			 return new Pair(result,"Brown");
		}
		int result = tempList.get(0);
		tempList.remove(0);
		playerCards.remove("Green");
		// make sure list is not empty
		if(tempList.size() >= 1)
		{
			// add again to the hashmap
			playerCards.put("Green", tempList);
		}
		
		return new Pair(result,"Green");
	}
}





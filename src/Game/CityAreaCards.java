package Game;
import java.util.*;


/**
*
* @author nav_k
* <h1>Representation of City Area Cards</h1>
* <p>
* Contains City Area Cards in enum data structure and a method that <br>
* returns a City Area Card whenever it is called i.e. for <br>
* the purpose of distributing cards
* </p>
*  
*/

public class CityAreaCards
{
	// Contains City Area Card names to which details will be added
	public enum getCityAreaCard
	{
		DOLLY_SISTERS, UNREAL_ESTATE, DRAGONS_LANDING, SMALL_GODS, THE_SCOURS, 
		THE_HIPPO, THE_SHADES, DIMWELL, LONGWALL, ISLE_OF_GODS, SEVEN_SLEEPERS, NAP_HILL
	}
	

	// Contains all the values of enum
	public static List<getCityAreaCard> cityAreaList = Arrays.asList(getCityAreaCard.values());
	public static List<getCityAreaCard> getCityCardsName = Arrays.asList(getCityAreaCard.values());
	
	// Contains key as City Number and Value as Name of City Area
	public static HashMap<Integer, String> mapCityArea = new HashMap<Integer, String>();
	

	/**
	 * One parameterized function that takes integer value as 
	 * City Are Number
	 * and whenever a player requests for a particular City Area Card,
	 * method returns the details(name as of now) of the same.
	 * @param cityAreaToMatch Matches given number with city area name
	 * @return String returns name of city area
	 */
	public static String getCityAreaCard(int cityAreaToMatch)
	{
		String valueToAdd = "";
		int counter = 1;
//		for(getCityAreaCard a: tempList)
//		{
//			System.out.println(a.toString());
//		}
		
		for(getCityAreaCard a: cityAreaList)
		{
			 valueToAdd = a.toString();
			 mapCityArea.put(counter, valueToAdd);
			 counter++;
		}
		// Map that removes the Name of city area corresponding to the number given
		String valueToReturn = mapCityArea.get(cityAreaToMatch);
		mapCityArea.remove(cityAreaToMatch);
		return valueToReturn.toString();
	}
	
//	public static void main(String[] args)
//	{
//		System.out.println(getCityAreaCard(3));
//	}
}

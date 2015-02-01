package Game;
import java.util.*;
public class CityAreaCards {
	
	public enum getCityAreaCard{
		THE_SHADES, DOLLY_SISTERS, THE_SCOURS, DIMWELL, NAP_HILL, LONGWALL, THE_HIPPO, DRAGONS_LANDING, ISLE_OF_GODS, SMALL_GODS, SEVEN_SLEEPERS, UNREAL_ESTATE
	}
	public static List<getCityAreaCard> shuffleList = Arrays.asList(getCityAreaCard.values());
	
	public static String shuffleCityAreaCards()
	{
		Collections.shuffle(shuffleList);
		getCityAreaCard valueToReturn = shuffleList.get(0);
		String result = valueToReturn.toString();
		//shuffleList.remove(0);
		shuffleList = new ArrayList<getCityAreaCard>(shuffleList);
		shuffleList.remove(0);
		return valueToReturn.toString();
	}
	
	public static void main(String[] args)
	{
		System.out.println(shuffleCityAreaCards());
	}
}

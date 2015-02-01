import java.util.*;

public class RandomEventCards {
	public enum getRandomEventCard{
		THE_DRAGON, FLOOD, FIRE, FOG, RIOTS, EXPLOSION, MYSTERIOUS_MURDERS, DEMONS_FROM_THE_DUNGEONS_DIMENSIONS, SUBSIDENCE, BLOODY_STUPID_JOHNSON, TROLLS, EARTHQUAKE
	}
	public static List<getRandomEventCard> shuffleList = Arrays.asList(getRandomEventCard.values());
	public static String shuffleRandomEventCards()
	{
		Collections.shuffle(shuffleList);
		getRandomEventCard valueToReturn = shuffleList.get(0);
		String result = valueToReturn.toString();
		//shuffleList.remove(0);
		shuffleList = new ArrayList<getRandomEventCard>(shuffleList);
		shuffleList.remove(0);
		return valueToReturn.toString();
	}
	
	public static void main(String[] args)
	{
		System.out.println(shuffleRandomEventCards());
	}
}

import java.util.*;
public class PersonalityCards {
	
	public enum getPersonalityCard{
		LORD_VETINARI, LORD_SELACHII, LORD_RUST, LORD_DE_WORDE, DRAGON_KING_OF_ARMS, CHRYSOPRASE, COMMANDER_VIMES
	}
	public static List<getPersonalityCard> shuffleList = Arrays.asList(getPersonalityCard.values());
	
	public static String shufflePersonalityCards()
	{
		Collections.shuffle(shuffleList);
		getPersonalityCard valueToReturn = shuffleList.get(0);
		String result = valueToReturn.toString();
		//shuffleList.remove(0);
		shuffleList = new ArrayList<getPersonalityCard>(shuffleList);
		shuffleList.remove(0);
		return valueToReturn.toString();
	}
	
	public static void main(String[] args)
	{
		System.out.println(shufflePersonalityCards());
	}
}

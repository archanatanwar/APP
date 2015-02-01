package Game;

//import java.awt.List;
import java.util.*;

public class Player {
	String color;
	String personality;
	int minionHold;
	int buildingHold;
	int cashHold;
	private String[] defaultRegions = { "Dolly Sisters", "The Scours",
			"The Shades" };

	Hashtable<String, RegionStatus> H_Region;
	RegionStatus SP;
	HashMap<String, List<Integer>> pCards;
	List<Integer> list = new ArrayList<>();

	public Player(String colorTemp, String personalityTemp) {
		color = colorTemp;
		personality = personalityTemp;
		minionHold = 12;
		buildingHold = 6;
		cashHold = 10;
		H_Region = new Hashtable<String, RegionStatus>();
		pCards = new HashMap<String, List<Integer>>();

		for (int i = 1; i <= 5; i++) {
			Pair pair = PlayerCards.returnPlayerCard();
			String color_temp = pair.getCardColor();
			int cardNo_temp = pair.getCard();
			if (pCards.containsKey(color_temp)) {
				list = pCards.get(color_temp);
				list.add(cardNo_temp);
			} else {
				list.add(cardNo_temp);
			}
			pCards.put(color_temp, list);
		}
	}

	public void initialPlayerStatus() {
		minionHold = minionHold - 3;
		for (int i = 0; i < defaultRegions.length; i++) {
			String temp = defaultRegions[i];
			if (H_Region.containsKey(temp)) {
				H_Region.get(defaultRegions[i]).placedMinion = 3;
			} else {
				SP = new RegionStatus();
				SP.placedbuilding = 0;
				SP.placedMinion = 3;
				H_Region.put(defaultRegions[i], SP);
			}
		}
	}
}

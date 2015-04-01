package Game;

import java.util.HashMap;
import java.util.List;

/**
 * <h1>Fog</h1>
 * <p>
 * Draw and discard the top five cards from the deck
 * </p>
 * 
 * @author mary
 *
 */

public class FogEventCard extends RandomEventCards {
	/**
	 * executes Random Event Fog functionality
	 */
	public void executeRandomEvent() {
		fogCards = new HashMap<String, List<String>>();
		for (int i = 1; i <= 5; i++) {
			Pair pair = PlayerCards.getPlayerCard();
			String color_temp = pair.getCardColor();
			String cardName_temp = pair.getCard();
			if (fogCards.containsKey(color_temp)) {
				list = fogCards.get(color_temp);
				list.add(cardName_temp);
			} else {
				list.add(cardName_temp);
			}
			fogCards.put(color_temp, list);
		}
		String names = fogCards.toString();
		NewGame.showErrorDialog("Fog Event Occured, Five Cards Discarded : "
				+ names);
	}
}

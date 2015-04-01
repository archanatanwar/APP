package Game;

/**
 * <h1>Utility class to help function getPlayerCard() to return two values</h1>
 * <p>
 * utility class the helps getPlayerCard function to return two values <br>
 * i.e. color and number of player card
 * </p>
 * 
 * @author nav_k
 *
 */
public final class Pair {
	private final String playerCardName;
	private final String colorPlayerCard;

	public Pair(String name, String color) {
		playerCardName = name;
		colorPlayerCard = color;
	}

	// accessor for Card number
	public String getCard() {
		return playerCardName;
	}

	// accessor for Card color
	public String getCardColor() {
		return colorPlayerCard;
	}
}

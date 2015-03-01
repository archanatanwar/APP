package Game;

/**
 * <h1>Utility class to help function getPlayerCard() to return two values</h1>
 * <p>utility class the helps getPlayerCard function to return two values <br>
 * i.e. color and number of player card</p>
 * @author nav_k
 *
 */
public final class Pair
{
	private final int playerCardNo;
	private final String colorPlayerCard; 
	public Pair(int cardNo, String color)
	{
		playerCardNo = cardNo;
		colorPlayerCard = color;
	}
	
	// accessor for Card number
	public int getCard()
	{
		return playerCardNo;
	}
	
	// accessor for Card color
	public String getCardColor()
	{
		return colorPlayerCard;
	}
}

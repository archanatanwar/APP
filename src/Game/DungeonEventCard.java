package Game;

/**
 * <h1> Demons from the Dungeon Dimensions </h1>
 * <p>
 * Roll the die four times to select four area<br>
 * Place a Demon in each area
 * </p>
 * 
 * @author mary
 *
 */

public class DungeonEventCard extends RandomEventCards{
	
	public void executeRandomEvent()
	{
		int result;
		String value = "";
		for(int i=0; i<4; i++)
		{
			result = getRollDiceNumber();
			GameEngine.regionObjList.get(result-1).placeDemon();
			value = value + result + " ";
		}
		NewGame.showErrorDialog("Demons from the Dungeon Dimensions Event Occured in Region : " + value);
	}
}

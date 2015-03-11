package Game;

/**
 * <h1> Trolls </h1>
 * <p>
 * Roll the die three times to select three areas<br>
 * Place one Troll minion piece in each area
 * </p>
 * 
 * @author mary
 *
 */

public class TrollsEventCard extends RandomEventCards{
	
	public void executeRandomEvent()
	{
		int result;
		String sValue = "Trolls placed in region: ";
		for(int i=0; i<3; i++)
		{
			result = getRollDiceNumber();
			GameEngine.regionObjList.get(result-1).placeTroll();
			sValue = sValue + result + "  ";
		}
		NewGame.showErrorDialog(sValue);
	}
}

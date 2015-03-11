package Game;

/**
 * <h1> The Dragon </h1>
 * <p>
 * Roll the die to select an area <br>
 * remove all pieces from that area
 * </p>
 * 
 * @author mary
 *
 */

public class DragonEventCard extends RandomEventCards{
	
	public void executeRandomEvent()
	{
		int result = 0;
		result = getRollDiceNumber();
		GameEngine.regionObjList.get(result-1).executeDragonEvent();
		int size = GameEngine.playerObjList.size();
		for(int i=0; i<size; i++){
			GameEngine.playerObjList.get(i).executeDragonEvent(result);
		}
		NewGame.showErrorDialog("Dragon Event Occured in Region : " + result);
	}
}

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
	/**
	 * executes Random Event The Dragon functionality
	 */
	public void executeRandomEvent()
	{
		int result = 0;
		// roll die
		result = getRollDiceNumber();
		// get region number according to result
		GameEngine.regionObjList.get(result-1).executeDragonEvent();
		int size = GameEngine.playerObjList.size();
		// remove all pieces from area by calling method
		for(int i=0; i<size; i++){
			GameEngine.playerObjList.get(i).executeDragonEvent(result);
		}
		NewGame.showErrorDialog("Dragon Event Occured in Region : " + result);
	}
}

package Game;

/**
 * <h1>The Dragon</h1>
 * <p>
 * Roll the die to select an area <br>
 * remove all pieces from that area
 * </p>
 * 
 * @author mary
 *
 */

public class DragonEventCard extends RandomEventCards {
	/**
	 * executes Random Event The Dragon functionality
	 */
	public void executeRandomEvent() {
		int result = 0;
		// roll die
		int isSmallGods = 0;
		result = getRollDiceNumber();
		// get region number according to result
		
		int size = GameUtility.playerObjList.size();
		// remove all pieces from area by calling method
		for (int i = 0; i < size; i++) {
			if(GameUtility.playerObjList.get(i).H_Region.get(4-1).placedbuilding == 1)
			{
				
			}
			GameUtility.playerObjList.get(i).executeDragonEvent(result);
		}
		GameUtility.regionObjList.get(result - 1).executeDragonEvent();		
		NewGame.showErrorDialog("Dragon Event Occured in Region : " + result);
	}
}

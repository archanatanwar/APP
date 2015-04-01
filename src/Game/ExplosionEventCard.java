package Game;

/**
 * <h1>Explosion</h1>
 * <p>
 * Roll the die to select an area<br>
 * Remove any building in that area
 * </p>
 * 
 * @author mary
 *
 */

public class ExplosionEventCard extends RandomEventCards {
	/**
	 * executes Random Event Explosion functionality
	 */
	public void executeRandomEvent() {
		int result;
		// roll die
		result = getRollDiceNumber();
		GameUtility.regionObjList.get(result - 1).removeAllBuilding();
		for (int i = 0; i < GameUtility.playerObjList.size(); i++) {
			// remove building from the area
			GameUtility.playerObjList.get(i).removeBuilding(result);
		}
		NewGame.showErrorDialog("Event occured in region: " + result);
	}
}

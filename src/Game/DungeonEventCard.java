package Game;

/**
 * <h1>Demons from the Dungeon Dimensions</h1>
 * <p>
 * Roll the die four times to select four area<br>
 * Place a Demon in each area
 * </p>
 * 
 * @author mary
 *
 */

public class DungeonEventCard extends RandomEventCards {
	/**
	 * executes Random Event Dungeon Event functionality
	 */
	public void executeRandomEvent() {
		int result;
		String value = "";
		for (int i = 0; i < 4; i++) {
			// roll die four times
			result = getRollDiceNumber();
			// place demon in each area rolled
			GameUtility.regionObjList.get(result - 1).placeDemon();
			value = value + result + " ";
		}
		NewGame.showErrorDialog("Demons from the Dungeon Dimensions Event Occured in Region : "
				+ value);
	}
}

package Game;

/**
 * <h1>Bloody Stupid Johnson</h1>
 * <p>
 * Roll the die.<br>
 * If city area card of that region is in play, then move it to side.<br>
 * The owner of card removes one of his minions from the same area
 * </p>
 * 
 * @author nav_k
 *
 */
public class bsJohnsonEventCard extends RandomEventCards {
	/**
	 * executes Random Event Bloody Stupid Johnson functionality
	 */
	public void executeRandomEvent() {
		int result = 0;
		// roll the die
		result = getRollDiceNumber();
		// stop taking benefit of city area card
		GameUtility.regionObjList.get(result - 1).stopBenefit = 1;
		NewGame.showErrorDialog("Bloody Stupid Johnson Event Occured for Region : "
				+ result);
	}
}

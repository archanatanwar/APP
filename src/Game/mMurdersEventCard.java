package Game;

/**<h1>Mysterious Murders</h1>
 * <p>Each player rolls the die and move one minion from the area rolled</p>
 * @author nav_k
 *
 */
public class mMurdersEventCard extends RandomEventCards{
	/**
	 * executes Random Event Mysterious murders  functionality
	 */
	public void executeRandomEvent()
	{
		int result = 0;
		String playerColor;
		int newReg;
		int size = GameEngine.playerObjList.size();
		// for every player
		for(int i=0; i<size; i++){
			// roll the die
			result = getRollDiceNumber();
			playerColor = NewGame
					.displayBox("Select player color whose minion should be removed from region: "+result);
			newReg = Integer.parseInt(playerColor);
			// remove minion
			removePlayerMinion(playerColor, result);
			NewGame.showErrorDialog("Mysterious Murders Event Occured in Region : " + result + " for Player: "+playerColor);
		}		
	}
	
	public void removePlayerMinion(String playerColor, int region)
	{
		for (Player playerObj : GameEngine.playerObjList) {
			if (playerObj.color.equals(playerColor)) {
				playerObj.removeMinion(region);
				GameEngine.regionObjList.get(region-1).removeMinion(playerColor);
			}
		}
	}
}

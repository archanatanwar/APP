package Game;

import java.util.ArrayList;
import java.util.List;

/**
 * <h1>Mysterious Murders</h1>
 * <p>
 * Each player rolls the die and move one minion from the area rolled
 * </p>
 * 
 * @author nav_k
 *
 */
public class mMurdersEventCard extends RandomEventCards {
	/**
	 * executes Random Event Mysterious murders functionality
	 */
	public void executeRandomEvent() {
		int result = 0;
		String playerColor;
		int newReg;
		int size = GameUtility.playerObjList.size();
		List<String> playerList = new ArrayList<String>();
		// for every player
		for (Player playerObj : GameUtility.playerObjList) {
			if(playerObj.pTurn == 1)
			{
			// roll the die
			result = getRollDiceNumber();
			playerList.clear();
			for (String key : GameUtility.regionObjList.get(result-1).H_Player.keySet()) {
				if(GameUtility.regionObjList.get(result-1).H_Player.get(key).pMinionRegionwise > 0){
					playerList.add(GameUtility.regionObjList.get(result-1).H_Player.get(key).color);
				}
			}
			playerList.add("Exit");
			
			playerColor = NewGame
					.displayComboBox(playerObj.color+ ": Player's turn, Select player color whose minion should be removed from region: "+result,
							playerList);
			if(playerColor.equals("Exit"))
			{
				NewGame.showErrorDialog("Mysterious Murders Event Not Occured in Region : "
						+ result);
			}
			else
			{
				// remove minion
				removePlayerMinion(playerColor, result);
				NewGame.showErrorDialog("Mysterious Murders Event Occured in Region : "
						+ result + " for Player: " + playerColor);
			}
			}
		}
		
		for (Player playerObj : GameUtility.playerObjList) {
			if(playerObj.pTurn == 0)
			{
			// roll the die
			result = getRollDiceNumber();
			playerList.clear();
			for (String key : GameUtility.regionObjList.get(result-1).H_Player.keySet()) {
				if(GameUtility.regionObjList.get(result-1).H_Player.get(key).pMinionRegionwise > 0){
					playerList.add(GameUtility.regionObjList.get(result-1).H_Player.get(key).color);
				}
			}
			playerList.add("Exit");
			
			playerColor = NewGame
					.displayComboBox(playerObj.color+ ": Player's turn, Select player color whose minion should be removed from region: "+result,
							playerList);
			if(playerColor.equals("Exit"))
			{
				NewGame.showErrorDialog("Mysterious Murders Event Not Occured in Region : "
						+ result);
			}
			else
			{
				// remove minion
				removePlayerMinion(playerColor, result);
				NewGame.showErrorDialog("Mysterious Murders Event Occured in Region : "
						+ result + " for Player: " + playerColor);
			}
			}
		}
		
	}

	public void removePlayerMinion(String playerColor, int region) {
		for (Player playerObj : GameUtility.playerObjList) {
			if (playerObj.color.equals(playerColor)) {
				playerObj.removeMinion(region);
				GameUtility.regionObjList.get(region - 1).removeMinion(
						playerColor);
			}
		}
	}
}

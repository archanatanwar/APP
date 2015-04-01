package Game;

import java.util.ArrayList;

/**
 * <h1>Flood</h1>
 * <p>
 * Roll the die twice to see which areas are affected.<br>
 * If area rolled is adjacent to river, the players must move their minions to
 * adjacent areas.<br>
 * Trolls and demons remain in the area.<br>
 * </p>
 * 
 * @author nav_k
 *
 */
public class FloodEventCard extends RandomEventCards {
	/**
	 * executes Random Event Flood functionality
	 */
	public void executeRandomEvent() {
		ArrayList<Integer> floodAreas = new ArrayList<Integer>();
		ArrayList<String> tempArray = new ArrayList<String>();
		Boolean floodFlag;
		String comboChoice;
		int area1, area2, count, moveRegion = -1;
		// roll the die twice
		area1 = getRollDiceNumber();
		area2 = getRollDiceNumber();
		floodFlag = false;
		for(int temp : floodAreas)
		{
			if(temp == area1)
			{
				floodFlag = true;
			}
		}
		if(floodFlag)
		{
			tempArray.clear();		
			NewGame.showErrorDialog("Flood Event Occured for First Selected Region : "+ area1);
			count = GameUtility.regionObjList.get(area1 - 1).listForNeighbours
					.size();
			for (int i = 0; i < count; i++) {
				if (area2 != GameUtility.regionObjList.get(area1 - 1).listForNeighbours
						.get(i)) {
					// get list of neighbors
					tempArray.add(String.valueOf(GameUtility.regionObjList.get(area1 - 1).listForNeighbours.get(i)));
				}
			}
			// move all the minions
			for (Player playerObj : GameUtility.playerObjList) {
				if(playerObj.H_Region.containsKey(area1) && playerObj.H_Region.get(area1-1).placedMinion > 0)
				{
					comboChoice = NewGame.displayComboBox("Player: "+playerObj.color+" Select region in which minion should be moved", tempArray);
					moveRegion = Integer.parseInt(comboChoice);
					playerObj.moveAllMinion(area1, moveRegion);
				}
			}
		}
		else
		{
			NewGame.showErrorDialog("Flood Event Can't Occur for First Selected Region : "+ area1);
		}
		
		
		floodFlag = false;
		for(int temp : floodAreas)
		{
			if(temp == area2)
			{
				floodFlag = true;
			}
		}
		if(floodFlag)
		{
			tempArray.clear();
			NewGame.showErrorDialog("Flood Event Occured for Second Selected Region : " + area2);
			count = GameUtility.regionObjList.get(area2 - 1).listForNeighbours
					.size();
			for (int i = 0; i < count; i++) {
				if (area1 != GameUtility.regionObjList.get(area2 - 1).listForNeighbours
						.get(i)) {
					// get list of neighbors
					tempArray.add(String.valueOf(GameUtility.regionObjList.get(area2 - 1).listForNeighbours.get(i)));
				}
			}
			// move all the minions
			for (Player playerObj : GameUtility.playerObjList) {
				if(playerObj.H_Region.containsKey(area2) && playerObj.H_Region.get(area2-1).placedMinion > 0)
				{
					comboChoice = NewGame.displayComboBox("Player: "+playerObj.color+" Select region in which minion should be moved", tempArray);
					moveRegion = Integer.parseInt(comboChoice);
					playerObj.moveAllMinion(area2, moveRegion);
				}
			}
		}
		else
		{
			NewGame.showErrorDialog("Flood Event Can't Occur for Second Selected Region : "+ area2);
		}
		NewGame.showErrorDialog("Flood Event Occurence Completed!");
	}
}

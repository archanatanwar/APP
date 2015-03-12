package Game;
/**
 * <h1>Flood</h1>
 *  <p>Roll the die twice to see which areas are affected.<br>
 *  If area rolled is adjacent to river, the players must move their minions to adjacent areas.<br>
 *  Trolls and demons remain in the area.<br></p>
 * @author nav_k
 *
 */
public class FloodEventCard extends RandomEventCards{
	/**
	 * executes Random Event Flood functionality
	 */
	public void executeRandomEvent()
	{
		int area1, area2, count, moveRegion = -1;
		// roll the die twice
		area1 = getRollDiceNumber();
		area2 = getRollDiceNumber();
		count = GameEngine.regionObjList.get(area1-1).listForNeighbours.size();
		for(int i=0; i<count; i++)
		{
			if(area2 != GameEngine.regionObjList.get(area1-1).listForNeighbours.get(i))
			{
				// get list of neighbors
				moveRegion = GameEngine.regionObjList.get(area1-1).listForNeighbours.get(i);
				break;
			}
		}
		if(moveRegion != -1)
		{
			// move all the minions
			for (Player playerObj : GameEngine.playerObjList) {
				playerObj.moveAllMinion(area1, moveRegion);
			}
			NewGame.showErrorDialog("Flood Event Occured for Region : " + moveRegion);
		}		
		moveRegion = -1;
		count = GameEngine.regionObjList.get(area2-1).listForNeighbours.size();
		for(int i=0; i<count; i++)
		{
			if(area1 != GameEngine.regionObjList.get(area2-1).listForNeighbours.get(i))
			{
				moveRegion = GameEngine.regionObjList.get(area2-1).listForNeighbours.get(i);
				break;
			}
		}
		if(moveRegion != -1)
		{
			for (Player playerObj : GameEngine.playerObjList) {
				playerObj.moveAllMinion(area2, moveRegion);
			}
			NewGame.showErrorDialog("Flood Event Occured for Region : " + moveRegion);
		}
		NewGame.showErrorDialog("Flood Event Occurence Completed!");
	}
}

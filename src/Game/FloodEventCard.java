package Game;

public class FloodEventCard extends RandomEventCards{
	
	public void executeRandomEvent()
	{
		int area1, area2, count, moveRegion = -1;
		area1 = getRollDiceNumber();
		area2 = getRollDiceNumber();
		count = GameEngine.regionObjList.get(area1-1).listForNeighbours.size();
		for(int i=0; i<count; i++)
		{
			if(area2 != GameEngine.regionObjList.get(area1-1).listForNeighbours.get(i))
			{
				moveRegion = GameEngine.regionObjList.get(area1-1).listForNeighbours.get(i);
				break;
			}
		}
		if(moveRegion != -1)
		{
			for (Player playerObj : GameEngine.playerObjList) {
				playerObj.moveAllMinion(area1, moveRegion);
			}
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
		}
	}
}

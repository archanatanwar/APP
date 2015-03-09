package Game;

public class RiotEventCard extends RandomEventCards{
	
	public void executeRandomEvent()
	{
		int minion, mCost, bCost, cash, size, rNum, totalPoints; 
		String color;
		String winnerColor = "";
		int winner = 0;
		GameEngine.TMarkerHold = 4;
		if(GameEngine.TMarkerHold <= 4)
		{
			size = GameEngine.playerObjList.size();
			for(int i=0; i<size; i++)
			{
				bCost = 0;
				totalPoints = 0;
				color = GameEngine.playerObjList.get(i).color;
				minion = GameEngine.playerObjList.get(i).minionHold;				
				mCost = (12-minion)*5;
				cash = GameEngine.playerObjList.get(i).cashHold;
				for (int key : GameEngine.playerObjList.get(i).H_Region.keySet()) {
					if(GameEngine.playerObjList.get(i).H_Region.get(key).placedbuilding == 1)
					{
						rNum = GameEngine.playerObjList.get(i).H_Region.get(key).regionNumber;
						bCost = bCost + GameEngine.regionObjList.get(rNum-1).rBuildingCost;
					}
				}
				totalPoints = mCost + cash + bCost;
				if(winner < totalPoints)
				{
					winnerColor = color;
					winner = totalPoints;
				}
			}
		}
		NewGame.showErrorDialog("Player: "+ winnerColor +" wins having total points: "+ winner);
	}

}

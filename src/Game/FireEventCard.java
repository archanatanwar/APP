package Game;

public class FireEventCard extends RandomEventCards{
	public void executeRandomEvent()
	{
		int result = 0;
		int resultTemp = 0;
		int isBuildingExist = 0;
		int numNeighbour;
		result = getRollDiceNumber();
		isBuildingExist = GameEngine.regionObjList.get(result-1).executeFireEvent();
		NewGame.showErrorDialog("Fire Event Occured in region : " + result);
		while(isBuildingExist == 1)
		{	
			int size = GameEngine.playerObjList.size();
			for(int i=0; i<size; i++){
				GameEngine.playerObjList.get(i).executeFireEvent(result);
			}			
			resultTemp = getRollDiceNumber();
			NewGame.showErrorDialog("Next region selected : " + resultTemp);
			numNeighbour = GameEngine.regionObjList.get(result-1).listForNeighbours.size();
			for(int i=0; i<numNeighbour; i++)
			{
				if (resultTemp == GameEngine.regionObjList.get(result-1).listForNeighbours.get(i))
				{
					result = resultTemp;
					break;
				}				
			}			
			if(result != resultTemp)
			{
				break;
			}
			isBuildingExist = GameEngine.regionObjList.get(result-1).executeFireEvent();
			NewGame.showErrorDialog("Fire Event Occured in region : " + result);
		};		
	}
}

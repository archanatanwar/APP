package Game;

public class DragonEventCard extends RandomEventCards{
	
	public void executeRandomEvent()
	{
		int result = 0;
		result = getRollDiceNumber();
		GameEngine.regionObjList.get(result-1).executeDragonEvent();
		int size = GameEngine.playerObjList.size();
		for(int i=0; i<size; i++){
			GameEngine.playerObjList.get(i).executeDragonEvent(result);
		}
		NewGame.showErrorDialog("Dragon Event Occured in Region : " + result);
	}
}

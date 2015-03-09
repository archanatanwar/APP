package Game;

public class ExplosionEventCard extends RandomEventCards{
	public void executeRandomEvent()
	{
		int result;
		result = getRollDiceNumber();
		GameEngine.regionObjList.get(result-1).removeAllBuilding();
		for(int i=0; i<GameEngine.playerObjList.size(); i++)
		{
			GameEngine.playerObjList.get(i).removeBuilding(result);
		}
		NewGame.showErrorDialog("ExplosionEvent occured in region: "+ result);
	}
}

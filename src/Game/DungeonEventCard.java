package Game;

public class DungeonEventCard extends RandomEventCards{
	
	public void executeRandomEvent()
	{
		int result;
		for(int i=0; i<4; i++)
		{
			result = getRollDiceNumber();
			GameEngine.regionObjList.get(result-1).placeDemon();
			//TODO
		}
	}
}

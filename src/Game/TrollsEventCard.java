package Game;

public class TrollsEventCard extends RandomEventCards{
	
	public void executeRandomEvent()
	{
		int result;
		for(int i=0; i<3; i++)
		{
			result = getRollDiceNumber();
			GameEngine.regionObjList.get(result-1).placeTroll();
		}
	}
}

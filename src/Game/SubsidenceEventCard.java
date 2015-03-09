package Game;

public class SubsidenceEventCard extends RandomEventCards{
	
	public void executeRandomEvent()
	{
		for(int i=0; i<GameEngine.playerObjList.size(); i++)
		{
			GameEngine.playerObjList.get(i).handleSubsidenceEvent();
		}		
	}
}

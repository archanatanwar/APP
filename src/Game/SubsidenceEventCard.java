package Game;

public class SubsidenceEventCard extends RandomEventCards{
	
	public void executeRandomEvent()
	{
		for(int i=0; i<GameEngine.playerObjList.size(); i++)
		{
			if(GameEngine.playerObjList.get(i).buildingHold < 6)
			{			
				GameEngine.playerObjList.get(i).handleSubsidenceEvent();
			}
		}		
	}
}

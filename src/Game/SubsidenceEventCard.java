package Game;

/**
 * <h1> Subsidence </h1>
 * <p>
 * All players must pay 2$ for each building they have on the board or remove it instead
 * </p>
 * 
 * @author mary
 *
 */

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

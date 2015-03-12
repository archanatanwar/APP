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
	
	/**
	 * executes Random Event Subsidence functionality
	 */
	public void executeRandomEvent()
	{
		for(int i=0; i<GameEngine.playerObjList.size(); i++)
		{
			// if player has his building placed on board
			if(GameEngine.playerObjList.get(i).buildingHold < 6)
			{			
				// call the method to perform action
				GameEngine.playerObjList.get(i).handleSubsidenceEvent();
			}
		}
		//Dialog shown in player class
	}
}

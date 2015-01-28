package Game;

public class Player {
	String color;
	String personality;
	int minionHold;
	int buildingHold;
	int cash;
	
	public Player(String colorTemp, String personalityTemp) {
		color = colorTemp;
		personality = personalityTemp;
		minionHold = 0;
		buildingHold = 0;
		cash = 0;
		
	}
	public void initialPlayerStatus()
	{
		minionHold = minionHold - 3;
	}
	
}

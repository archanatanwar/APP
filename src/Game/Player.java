package Game;

public class Player {
	String color;
	String personality;
	int minionHold;
	int buildingHold;
	int cashing;
	
	
	public Player(String colorTemp, String personalityTemp) {
		color = colorTemp;
		personality = personalityTemp;
		minionHold = 12;
		buildingHold = 6;
		cashing = 10;
		
	}
	
	public void initialPlayerStatus()
	{
		minionHold = minionHold - 3;
	}
}

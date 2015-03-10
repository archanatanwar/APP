package Game;

public class TrollsEventCard extends RandomEventCards{
	
	public void executeRandomEvent()
	{
		int result;
		String sValue = "Trolls placed in region: ";
		for(int i=0; i<3; i++)
		{
			result = getRollDiceNumber();
			GameEngine.regionObjList.get(result-1).placeTroll();
			sValue = sValue + result + "  ";
		}
		NewGame.showErrorDialog(sValue);
	}
}

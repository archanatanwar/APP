package Game;

public class bsJohnsonEventCard extends RandomEventCards{
	
	public void executeRandomEvent()
	{
		int result = 0;
		result = getRollDiceNumber();
		GameEngine.regionObjList.get(result-1).stopBenefit = 1;
		NewGame.showErrorDialog("Bloody Stupid Johnson Event Occured for Region : " + result);
	}
}

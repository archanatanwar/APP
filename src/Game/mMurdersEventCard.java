package Game;

public class mMurdersEventCard extends RandomEventCards{
	public void executeRandomEvent()
	{
		int result = 0;
		String playerColor;
		int newReg;
		int size = GameEngine.playerObjList.size();
		for(int i=0; i<size; i++){
			result = getRollDiceNumber();
			playerColor = NewGame
					.displayBox("Select player color whose minion should be removed from region: "+result);
			newReg = Integer.parseInt(playerColor);
			removePlayerMinion(playerColor, result);
		}
		NewGame.showErrorDialog("Dragon Event Occured in Region : " + result);
	}
	
	public void removePlayerMinion(String playerColor, int region)
	{
		for (Player playerObj : GameEngine.playerObjList) {
			if (playerObj.color.equals(playerColor)) {
				playerObj.removeMinion(region);
				GameEngine.regionObjList.get(region-1).removeMinion(playerColor);
			}
		}
	}
}

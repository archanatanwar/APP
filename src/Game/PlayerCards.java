package Game;

import java.util.*;

public class PlayerCards {
	public static HashMap<String, List<Integer>> playerCards;
	public static void createPlayerCardsDeck()
	{
		playerCards = new HashMap<String, List<Integer>>();
		List<Integer> listForGreen = new ArrayList<>();
		List<Integer> listForBrown = new ArrayList<>();
		String Green = "Green";
		String Brown = "Brown";
	
		for(int i=1; i<=48; i++)
		{
			listForGreen.add(i);
		}
		
		for(int i=1; i<=53; i++)
		{
			listForBrown.add(i);
		}
		// shuffle values of list
		Collections.shuffle(listForBrown);
		Collections.shuffle(listForGreen);
		
		// add values to map
		playerCards.put(Green, listForGreen);
		playerCards.put(Brown, listForBrown);
		
	}
	
	public static Pair returnPlayerCard()
	{
		if(playerCards.isEmpty())
		{
			return new Pair(0,"empty");
		}
		// get key by color green first
		List<Integer> tempList = playerCards.get("Green");
		// green cards are finished
		if(tempList == null)
		{
			 tempList = playerCards.get("Brown");
			 int result = tempList.get(0);
			 tempList.remove(0);
			 playerCards.remove("Brown");
			 // make sure list is not empty
			 if(tempList.size() >= 1)
			 {
				// add again to the hashmap
				 playerCards.put("Brown", tempList);
			 }
			 return new Pair(result,"Brown");
		}
		int result = tempList.get(0);
		tempList.remove(0);
		playerCards.remove("Green");
		// make sure list is not empty
		if(tempList.size() >= 1)
		{
			// add again to the hashmap
			playerCards.put("Green", tempList);
		}
		
		return new Pair(result,"Green");
	}
	
//	public static void main(String[] args) 
//	{
//		
//		System.out.println("Before calling");
//		PlayerCards.createPlayerCardsDeck();
//		Pair pair = returnPlayerCard();
//		System.out.println("1st element: "+ pair.getCard() + "color:  " + pair.getCardColor());
//		Pair pair2 = returnPlayerCard();
//		System.out.println("2nd element: "+ pair2.getCard() + "color:  " + pair2.getCardColor());
//		Pair pair3= returnPlayerCard();
//		System.out.println("3rd element: "+ pair3.getCard() + "color:  " + pair3.getCardColor());
//		Pair pair4 = returnPlayerCard();
//		System.out.println("4th element: "+ pair4.getCard() + "color:  " + pair4.getCardColor());
//		Pair pair5 = returnPlayerCard();
//		System.out.println("5th element: "+ pair5.getCard() + "color:  " + pair5.getCardColor());
//		Pair pair6 = returnPlayerCard();
//		System.out.println("6th element: "+ pair6.getCard() + "color:  " + pair6.getCardColor());
//	}
}

final class Pair{
	private final int playerCardNo;
	private final String colorPlayerCard; 
	public Pair(int cardNo, String color)
	{
		playerCardNo = cardNo;
		colorPlayerCard = color;
	}
	public int getCard()
	{
		return playerCardNo;
	}
	public String getCardColor()
	{
		return colorPlayerCard;
	}
}



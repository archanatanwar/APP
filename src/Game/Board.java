package Game;

import java.util.Scanner;

/**
 * 
 */

/**
 * @author Archana
 *
 */
public class Board {
	
	public static void main(String[] args) {
		RegionStatus DSisters = new RegionStatus("Dolly Sisters", 1, 6);
		RegionStatus UEstate = new RegionStatus("Unreal Estate", 2, 18);
		RegionStatus DLanding = new RegionStatus("Dragon's Landing", 3, 12);
		RegionStatus SGods = new RegionStatus("Small Gods", 4, 18);
		RegionStatus TScours = new RegionStatus("The Scours", 5, 6);
		RegionStatus THippo = new RegionStatus("The Hippo", 6, 12);
		RegionStatus TShades = new RegionStatus("The Shades", 7, 6);
		RegionStatus Dimwell = new RegionStatus("Dimwell", 8, 6);
		RegionStatus Longwall = new RegionStatus("Longwall", 9, 12);
		RegionStatus IGods = new RegionStatus("Isle of Gods", 10, 12);
		RegionStatus SSleepers = new RegionStatus("Seven Sleepers", 11, 18);
		RegionStatus NHill = new RegionStatus("Nap Hill", 12, 12);

		Scanner s1 = new Scanner(System.in);
		System.out.println("Enter Number of Players");
		int numPlayers = s1.nextInt();
		// check user >1 <=4
		for (int loop = 1; loop <= numPlayers; loop++) {
			switch (loop) {
			case 1:
				Player pRed = new Player("red", "A");
				TScours.placeDefaultMinion("red");
				TShades.placeDefaultMinion("red");
				DSisters.placeDefaultMinion("red");
				pRed.initialPlayerStatus();
				break;
			case 2:
				Player pGreen = new Player("green", "B");
				TScours.placeDefaultMinion("green");
				TShades.placeDefaultMinion("green");
				DSisters.placeDefaultMinion("green");
				break;
			case 3:
				Player pYellow = new Player("yellow", "C");
				TScours.placeDefaultMinion("yellow");
				TShades.placeDefaultMinion("yellow");
				DSisters.placeDefaultMinion("yellow");
				break;
			case 4:
				Player pBlue = new Player("blue", "D");
				TScours.placeDefaultMinion("blue");
				TShades.placeDefaultMinion("blue");
				DSisters.placeDefaultMinion("blue");
				break;
			}
		}
	}
}

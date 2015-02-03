package Game;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

/**
 * 
 */

public class Board {
	public static Region objDSisters;
	public static Region objUEstate;
	public static Region objDLanding;
	public static Region objSGods;
	public static Region objTScours;
	public static Region objTHippo;
	public static Region objTShades;
	public static Region objDimwell;
	public static Region objLongwall;
	public static Region objIGods;
	public static Region objSSleepers;
	public static Region objNHill;
	
	public static Player pRed;
	public static Player pYellow;
	public static Player pGreen;
	public static Player pBlue;

	static int numPlayers;
	static List<Player> players = new ArrayList<Player>();
	static List<Region> regions = new ArrayList<Region>();
	static List<Integer> greenList = new ArrayList<>();
	static List<Integer> brownList = new ArrayList<>();

	public static void showMenu() {
		int userInput;
		System.out.println("\n **** Welcome to Ankh-Morpork ****\n");
		System.out.println("Please Select From The Following Option ");
		System.out.println("1: Start Game");
		System.out.println("2: Load Game");
		System.out.println("3: View Game Status");
		System.out.println("4: Exit");

		System.out.println("Please Enter Your Choice");
		Scanner s2 = new Scanner(System.in);
		userInput = s2.nextInt();
		boolean choiceFlag = false;

		do {
			if (choiceFlag) {
				System.out.println("Do You Want To Continue? (y/n)");
				String choice = s2.next();
				if (choice.equalsIgnoreCase("y")) {
					System.out.println("Please Enter Your Choice");
					userInput = s2.nextInt();
				} else {
					userInput = 4;
				}
			}

			switch (userInput) {

			// Calling function createPlayerAccount()
			case 1:

				break;

			// Calling function playerSignIn()
			case 2:

				break;

			// Calling function playerSignOut()
			case 3:
				System.out.println("There are " + numPlayers + " players");
				for (int i = 0; i < players.size(); i++) {
					System.out.println("Player " + i + " ("
							+ players.get(i).color + ") is playing as "
							+ players.get(i).personality);

				}
				System.out.println("Current state of the game board:");
				System.out.format("%20s%20s%15s%15s%15s%15s", "area",
						"minions", "trouble?", "building?", "rDemons", "rTrolls");
				System.out.println("\n");

				for (int i = 0; i < regions.size(); i++) {
					String showMinion = "";
					String showBuilding = "";
					Set<String> keys = regions.get(i).H_Player.keySet();
			        for(String key: keys){
			        	showMinion = showMinion + " " + regions.get(i).H_Player.get(key).pMinionRegionwise+key;
			        	int numBuilding = regions.get(i).H_Player.get(key).pbuildingRegionwise;
			        	if(numBuilding == 1)
			        	{
			        		showBuilding = regions.get(i).H_Player.get(key).color;
			        	}
			        }					
					System.out.println("");
					System.out.format("%20s%20s%15s%15s%15s%15s",
							regions.get(i).rName, showMinion,
							regions.get(i).rTroubleMarker,
							showBuilding, regions.get(i).rDemon,
							regions.get(i).rTroll);
				}
				System.out.println("");
				for (int i = 0; i < players.size(); i++) {
					greenList = players.get(i).pCards.get("Green");
					brownList = players.get(i).pCards.get("Brown");
					String greenCards = "";
					String brownCards = "";	
					if(greenList != null)
						greenCards = greenList.toString();
					if(brownList != null)
						brownCards = brownList.toString();
					
					System.out.println("Player " + i
							+ "'s current inventory:");
					System.out.println("\t- "
							+ players.get(i).minionHold + " minions, "
							+ players.get(i).buildingHold + " buildings, "
							+ players.get(i).cashHold + " Ankh-Morpork dollars");
					System.out.println("\t- City Area cards:"
							+ players.get(i).minionHold + " minions, "
							+ players.get(i).buildingHold + " buildings, "
							+ players.get(i).cashHold + " Ankh-Morpork dollars");
					System.out.println("\t- Player cards:"
							+ "\n\t Green"+ greenCards 
							+ "\n\t Brown"+ brownCards );
				}

				break;

			// Calling function Exit()
			case 4:
				System.out.println("Bye Bye!");
				System.exit(0);
			default:
				System.out.println("Enter Your Choice Correctly!");
				break;

			}
			choiceFlag = true;
		} while (userInput != 4);
	}

	public static void setDefaultRegionStatus(String color) {
		objTScours.placeDefaultMinion(color);
		objTShades.placeDefaultMinion(color);
		objDSisters.placeDefaultMinion(color);
	}
	public static void main(String[] args) {
		objDSisters = new Region("Dolly Sisters", 1, 6);
		regions.add(objDSisters);
		objUEstate = new Region("Unreal Estate", 2, 18);
		regions.add(objUEstate);
		objDLanding = new Region("Dragon's Landing", 3, 12);
		regions.add(objDLanding);
		objSGods = new Region("Small Gods", 4, 18);
		regions.add(objSGods);
		objTScours = new Region("The Scours", 5, 6);
		regions.add(objTScours);
		objTHippo = new Region("The Hippo", 6, 12);
		regions.add(objTHippo);
		objTShades = new Region("The Shades", 7, 6);
		regions.add(objTShades);
		objDimwell = new Region("Dimwell", 8, 6);
		regions.add(objDimwell);
		objLongwall = new Region("Longwall", 9, 12);
		regions.add(objLongwall);
		objIGods = new Region("Isle of Gods", 10, 12);
		regions.add(objIGods);
		objSSleepers = new Region("Seven Sleepers", 11, 18);
		regions.add(objSSleepers);
		objNHill = new Region("Nap Hill", 12, 12);
		regions.add(objNHill);
		PlayerCards.createPlayerCardsDeck();

		// createXML();

		Scanner s1 = new Scanner(System.in);
		System.out.println("Enter Number of Players");
		numPlayers = s1.nextInt();
		String personality_temp;
		// check user >1 <=4
		for (int loop = 1; loop <= numPlayers; loop++) {
			switch (loop) {
			case 1:
				personality_temp = PersonalityCards.shufflePersonalityCards();
				pRed = new Player("red", personality_temp);
				setDefaultRegionStatus(pRed.color);				
				pRed.initialPlayerStatus();
				players.add(pRed);
				break;
			case 2:
				personality_temp = PersonalityCards.shufflePersonalityCards();
				pYellow = new Player("yellow", personality_temp);
				setDefaultRegionStatus(pYellow.color);
				pYellow.initialPlayerStatus();
				players.add(pYellow);
				break;
			case 3:
				personality_temp = PersonalityCards.shufflePersonalityCards();
				pGreen = new Player("green", personality_temp);
				setDefaultRegionStatus(pGreen.color);
				pGreen.initialPlayerStatus();
				players.add(pGreen);
				break;
			case 4:
				personality_temp = PersonalityCards.shufflePersonalityCards();
				pBlue = new Player("blue", personality_temp);
				setDefaultRegionStatus(pBlue.color);
				pBlue.initialPlayerStatus();
				players.add(pBlue);
				break;
			}
		}
		// createXML();
		showMenu();
	}
	private static void createXML() {
		DocumentBuilderFactory icFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder icBuilder;
		try {
			icBuilder = icFactory.newDocumentBuilder();
			Document doc = icBuilder.newDocument();
			Element mainRootElement = doc.createElementNS("CreateXMLDOM",
					"Regions");
			doc.appendChild(mainRootElement);

			// append child elements to root element
			mainRootElement.appendChild(getRegion(doc, objDSisters.rName,
					objDSisters.rNumber, objDSisters.rBuildingCost,
					objDSisters.rMinionNum, objDSisters.rTroubleMarker,
					objDSisters.rDemon, objDSisters.rTroll));
			mainRootElement.appendChild(getRegion(doc, objUEstate.rName,
					objUEstate.rNumber, objUEstate.rBuildingCost,
					objUEstate.rMinionNum, objUEstate.rTroubleMarker,
					objUEstate.rDemon, objUEstate.rTroll));
			mainRootElement.appendChild(getRegion(doc, objDLanding.rName,
					objDLanding.rNumber, objDLanding.rBuildingCost,
					objDLanding.rMinionNum, objDLanding.rTroubleMarker,
					objDLanding.rDemon, objDLanding.rTroll));
			mainRootElement.appendChild(getRegion(doc, objSGods.rName,
					objSGods.rNumber, objSGods.rBuildingCost,
					objSGods.rMinionNum, objSGods.rTroubleMarker,
					objSGods.rDemon, objSGods.rTroll));
			mainRootElement.appendChild(getRegion(doc, objTScours.rName,
					objTScours.rNumber, objTScours.rBuildingCost,
					objTScours.rMinionNum, objTScours.rTroubleMarker,
					objTScours.rDemon, objTScours.rTroll));
			mainRootElement.appendChild(getRegion(doc, objTHippo.rName,
					objTHippo.rNumber, objTHippo.rBuildingCost,
					objTHippo.rMinionNum, objTHippo.rTroubleMarker,
					objTHippo.rDemon, objTHippo.rTroll));
			mainRootElement.appendChild(getRegion(doc, objTShades.rName,
					objTShades.rNumber, objTShades.rBuildingCost,
					objTShades.rMinionNum, objTShades.rTroubleMarker,
					objTShades.rDemon, objTShades.rTroll));
			mainRootElement.appendChild(getRegion(doc, objDimwell.rName,
					objDimwell.rNumber, objDimwell.rBuildingCost,
					objDimwell.rMinionNum, objDimwell.rTroubleMarker,
					objDimwell.rDemon, objDimwell.rTroll));
			mainRootElement.appendChild(getRegion(doc, objLongwall.rName,
					objLongwall.rNumber, objLongwall.rBuildingCost,
					objLongwall.rMinionNum, objLongwall.rTroubleMarker,
					objLongwall.rDemon, objLongwall.rTroll));
			mainRootElement.appendChild(getRegion(doc, objIGods.rName,
					objIGods.rNumber, objIGods.rBuildingCost,
					objIGods.rMinionNum, objIGods.rTroubleMarker,
					objIGods.rDemon, objIGods.rTroll));
			mainRootElement.appendChild(getRegion(doc, objSSleepers.rName,
					objSSleepers.rNumber, objSSleepers.rBuildingCost,
					objSSleepers.rMinionNum, objSSleepers.rTroubleMarker,
					objSSleepers.rDemon, objSSleepers.rTroll));
			mainRootElement.appendChild(getRegion(doc, objNHill.rName,
					objNHill.rNumber, objNHill.rBuildingCost,
					objNHill.rMinionNum, objNHill.rTroubleMarker,
					objNHill.rDemon, objNHill.rTroll));

			// output DOM XML to console
			Transformer transformer = TransformerFactory.newInstance()
					.newTransformer();
			transformer.setOutputProperty(OutputKeys.INDENT, "yes");
			DOMSource source = new DOMSource(doc);
			StreamResult file = new StreamResult(new File(
					"/G:/APPProject/xmlFileFinal2.xml"));
			transformer.transform(source, file);

			System.out.println("\nXML DOM Created Successfully..");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static Node getRegion(Document doc, String name, int rNumber,
			int rBuildingCost, int rMinionNum, int rTroubleMarker, int rDemon,
			int rTroll) {
		Element region = doc.createElement("Region");
		region.setAttribute("Name", name);
		region.appendChild(getRegionElements(doc, region, "RegionNumber",
				rNumber));
		region.appendChild(getRegionElements(doc, region, "rBuildingCost",
				rBuildingCost));
		region.appendChild(getRegionElements(doc, region, "NumberOfMinions",
				rMinionNum));
		region.appendChild(getRegionElements(doc, region,
				"NumberOfrTroubleMarkers", rTroubleMarker));
		region.appendChild(getRegionElements(doc, region, "NumberOfrDemons",
				rDemon));
		region.appendChild(getRegionElements(doc, region, "NumberOfrTrolls",
				rTroll));
		return region;
	}

	// utility method to create text node
	private static Node getRegionElements(Document doc, Element element,
			String name, int value) {
		Element node = doc.createElement(name);
		// node.appendChild(doc.createTextNode(value));
		node.appendChild(doc.createTextNode(Integer.toString(value)));
		return node;
	}

}

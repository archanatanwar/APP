<<<<<<< HEAD
package Game;

import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.*;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;
import org.jdom2.input.SAXBuilder;
import org.w3c.dom.*;

/**
 * <h1>Game Engine</h1>
 * <p>
 * Creates objects for both Player and Board class,<br>
 * controls the execution of entire game<br>
 * and saves and loads the XML file
 * </p>
 * 
 * @author nav_k
 *
 */
public class GameEngine extends JFrame implements Runnable {
	// objects for all regions
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

	// objects for players
	public static Player objPRed;
	public static Player objPYellow;
	public static Player objPGreen;
	public static Player objPBlue;
	static int BankHold = 120;
	static int numPlayers; // number of players
	static List<Player> playerObjList = new ArrayList<Player>(); // list of
																	// player
																	// objects
	static List<Region> regionObjList = new ArrayList<Region>(); // list of
																	// region
																	// objects

	static List<String> randomCards = new ArrayList<>();

	private JPanel panel1;
	private JButton Load_Game;
	private JButton Start_Game;
	public JTextField xmlName;

	public GameEngine() {
		initComponents();
	}

	/**
	 * Function reads from XML file, saves the values to objects of regions and
	 * players and load the corresponding tables using the same
	 * 
	 * @param e
	 *            the action performed
	 */
	private void Load_GameActionPerformed(ActionEvent e) {

		String XMLFileName = xmlName.getText();
		String pattern = "(.*?).xml";
		Pattern pCheck = Pattern.compile(pattern);
		Matcher mCheck = pCheck.matcher(XMLFileName);
		if (!mCheck.matches()) {
			xmlName.setText("Wrong Input!");
		} else {

			SAXBuilder builder = new SAXBuilder();
			File fileName = new File("./src/" + XMLFileName);
			if (fileName.exists()) {
				try {
					DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory
							.newInstance();
					DocumentBuilder docBuilder = docBuilderFactory
							.newDocumentBuilder();
					Document doc = docBuilder.parse(new File(
							"./src/"+XMLFileName));
					doc.getDocumentElement().normalize();

					// List of regions present in XML file
					NodeList listOfRegions = doc.getElementsByTagName("Region");
					int totalRegions = listOfRegions.getLength();
					// List of players present in XML file
					NodeList listOfPlayers = doc.getElementsByTagName("Player");

					// for regions
					for (int s = 0; s < listOfRegions.getLength(); s++) {
						Node firstRegionNode = listOfRegions.item(s);
						if (firstRegionNode.getNodeType() == Node.ELEMENT_NODE) {
							Element firstRegionElement = (Element) firstRegionNode;

							// get region number
							NodeList RegionNumberList = firstRegionElement
									.getElementsByTagName("RegionNumber");
							Element RegionNumberElement = (Element) RegionNumberList
									.item(0);
							NodeList RNList = RegionNumberElement
									.getChildNodes();
							// add region number to list of objects of region
							regionObjList.get(s).rNumber = Integer
									.parseInt(RNList.item(0).getNodeValue());

							// get name of region
							NodeList RegionNameList = (NodeList) firstRegionElement
									.getElementsByTagName("Name");
							Element RegionNameElement = (Element) RegionNameList
									.item(0);
							NodeList NameList = RegionNameElement
									.getChildNodes();
							// add region name to list of objects of region
							regionObjList.get(s).rName = NameList.item(0)
									.getNodeValue();

							// building cost of region
							NodeList CostList = firstRegionElement
									.getElementsByTagName("rBuildingCost");
							Element CostElement = (Element) CostList.item(0);
							NodeList rCostList = CostElement.getChildNodes();
							// add building cost to list of objects of region
							regionObjList.get(s).rBuildingCost = Integer
									.parseInt(rCostList.item(0).getNodeValue());

							// number of minions in region
							NodeList MinionList = firstRegionElement
									.getElementsByTagName("NumberOfMinions");
							Element MinionElement = (Element) MinionList
									.item(0);
							NodeList rMinionList = MinionElement
									.getChildNodes();
							// add minion number to list of objects of region
							regionObjList.get(s).rMinionNum = Integer
									.parseInt(rMinionList.item(0)
											.getNodeValue());

							// number of trouble markers in region
							NodeList TMList = firstRegionElement
									.getElementsByTagName("NumberOfrTroubleMarkers");
							Element TMElement = (Element) TMList.item(0);
							NodeList rTMList = TMElement.getChildNodes();
							// add trouble marker number to list of objects of
							// region
							regionObjList.get(s).rTroubleMarker = Integer
									.parseInt(rTMList.item(0).getNodeValue());

							// number of demons in region
							NodeList DList = firstRegionElement
									.getElementsByTagName("NumberOfrDemons");
							Element DElement = (Element) DList.item(0);
							NodeList DemonList = DElement.getChildNodes();
							// add demon number to list of objects of region
							regionObjList.get(s).rDemon = Integer
									.parseInt(DemonList.item(0).getNodeValue());

							// number of trolls in region
							NodeList TList = firstRegionElement
									.getElementsByTagName("NumberOfrTrolls");
							Element TElement = (Element) TList.item(0);
							NodeList TrollList = DElement.getChildNodes();
							// add troll number to list of objects of region
							regionObjList.get(s).rTroll = Integer
									.parseInt(TrollList.item(0).getNodeValue());
						} // end if
					}// end for

					// for players
					for (int s = 0; s < listOfPlayers.getLength(); s++) {
						Node firstPlayerNode = listOfPlayers.item(s);
						if (firstPlayerNode.getNodeType() == Node.ELEMENT_NODE) {
							Element firstPlayerElement = (Element) firstPlayerNode;

							// color of player's accessories
							NodeList ColorList = firstPlayerElement
									.getElementsByTagName("Color");
							Element ColorElement = (Element) ColorList.item(0);
							NodeList CList = ColorElement.getChildNodes();
							// add color to list of objects to player
							playerObjList.get(s).color = CList.item(0)
									.getNodeValue();

							// number of building with player currently
							NodeList BList = firstPlayerElement
									.getElementsByTagName("Building_Hold");
							Element BElement = (Element) BList.item(0);
							NodeList BuildingList = BElement.getChildNodes();
							// add number of buildings a player holds to list of
							// objects to player
							playerObjList.get(s).buildingHold = Integer
									.parseInt(BuildingList.item(0)
											.getNodeValue());
								// player number
						NodeList PNoList = firstPlayerElement.getElementsByTagName("Player_Number");
						Element PNoElement = (Element)PNoList.item(0);
						NodeList pNumberList = PNoElement.getChildNodes();
						// add player number
						playerObjList.get(s).pNumber = Integer.parseInt(pNumberList.item(0).getNodeValue());
						
						// player turn
						NodeList PTurnList = firstPlayerElement.getElementsByTagName("Player_Turn");
						Element PTurnElement = (Element)PTurnList.item(0);
						NodeList TurnList = PTurnElement.getChildNodes();
						// add player turn
						playerObjList.get(s).pTurn = Integer.parseInt(TurnList.item(0).getNodeValue());
						
							// amount of cash with player currently
							NodeList CashList = firstPlayerElement
									.getElementsByTagName("Cash_Hold");
							Element CashElement = (Element) CashList.item(0);
							NodeList pCashList = CashElement.getChildNodes();
							// add cash a player holds to list of objects to
							// player
							playerObjList.get(s).cashHold = Integer
									.parseInt(pCashList.item(0).getNodeValue());

							// number of minions with player currently
							NodeList MinionList = firstPlayerElement
									.getElementsByTagName("Minion_Hold");
							Element MinionElement = (Element) MinionList
									.item(0);
							NodeList MList = MinionElement.getChildNodes();
							// add minions a player holds to list of objects to
							// player
							playerObjList.get(s).minionHold = Integer
									.parseInt(MList.item(0).getNodeValue());

							// personality card with player currently
							NodeList PersonalityList = firstPlayerElement
									.getElementsByTagName("Personality");
							Element PersonalityElement = (Element) PersonalityList
									.item(0);
							NodeList PList = PersonalityElement.getChildNodes();
							// add personality card to list of objects to player
							playerObjList.get(s).personality = PList.item(0)
									.getNodeValue();

							// player card with player currently
							NodeList PCardList = firstPlayerElement
									.getElementsByTagName("Player_Cards");
							Element PCardElement = (Element) PCardList.item(0);
							NodeList CardList = PCardElement.getChildNodes();
							String value = CardList.item(0).getNodeValue();
							HashMap<String, List<Integer>> player_Cards = new HashMap<String, List<Integer>>();
							value = value.substring(1, value.length() - 1); // remove
																			// curly
																			// brackets
							String[] keyValuePair = value.split("="); // split
																		// the
																		// string
																		// to
																		// create
																		// key-value
																		// pair
							String replace = keyValuePair[1].replace("[", "");
							String replace1 = replace.replace("]", "");
							List<String> tempList = new ArrayList<String>(
									Arrays.asList(replace1.split(",")));

							List<Integer> convertToInt = new ArrayList<Integer>();
							for (String a : tempList) {
								Integer temp = 0;
								temp = Integer.parseInt(a.trim());
								convertToInt.add(temp);
							}

							player_Cards.put(keyValuePair[0], convertToInt);
							playerObjList.get(s).pCards = player_Cards;
						}// end if
					}// end for

					// call constructor of SavedGame to load GUI
					SavedGame Loaded = new SavedGame();

					// load the table that contains info about regions
					for (int s = 0; s < listOfRegions.getLength(); s++) {
						Loaded.Loaded_Region_Info.setValueAt(
								regionObjList.get(s).rName, s, 0);
						Loaded.Loaded_Region_Info.setValueAt(
								regionObjList.get(s).rMinionNum, s, 1);
						Loaded.Loaded_Region_Info.setValueAt(
								regionObjList.get(s).rTroubleMarker, s, 3);
						Loaded.Loaded_Region_Info.setValueAt(
								regionObjList.get(s).rDemon, s, 4);
						Loaded.Loaded_Region_Info.setValueAt(
								regionObjList.get(s).rTroll, s, 5);
					}

					// load the table that contains info about players
					for (int s = 0; s < listOfPlayers.getLength(); s++) {
						Loaded.Loaded_Players_Info.setValueAt(playerObjList.get(s).pNumber, s, 0);
						Loaded.Loaded_Players_Info.setValueAt(
								playerObjList.get(s).color, s, 1);
						Loaded.Loaded_Players_Info.setValueAt(
								playerObjList.get(s).personality, s, 2);
						Loaded.Loaded_Players_Info.setValueAt(
								playerObjList.get(s).minionHold, s, 3);
						Loaded.Loaded_Players_Info.setValueAt(
								playerObjList.get(s).buildingHold, s, 4);
						Loaded.Loaded_Players_Info.setValueAt(
								playerObjList.get(s).cashHold, s, 5);
						Loaded.Loaded_Players_Info.setValueAt(
								playerObjList.get(s).pCards, s, 6);
					}
					Loaded.setVisible(true);
				}// end try
				catch (SAXParseException err) {
					System.out.println("** Parsing error" + ", line "
							+ err.getLineNumber() + ", uri "
							+ err.getSystemId());
					System.out.println(" " + err.getMessage());
				} catch (SAXException ee) {
					Exception x = ee.getException();
					((x == null) ? ee : x).printStackTrace();
				} catch (Throwable t) {
					t.printStackTrace();
				}
			} else
				JOptionPane.showMessageDialog(null,
						"No Saved File Exist To Load !");
		}
	}

	private void Start_GameActionPerformed(ActionEvent e) {
		// TODO add your code here

		if (e.getSource() == Start_Game) {

			NewGame Game = new NewGame(); // create Object from the Player Class
											// the will display the form
			Game.setVisible(true); // make it visible
			// Form.setVisible(true);
			// JOptionPane.showMessageDialog(null, "You've got it !");

		}
	}

	/**
	 * Function creates all the components to be displayed in the panel
	 */
	private void initComponents() {
		// Component initialization - DO NOT MODIFY

		panel1 = new JPanel();
		Load_Game = new JButton();
		Start_Game = new JButton();
		xmlName = new JTextField();

		// ======== this ========
		setTitle("Game Build1");
		Container contentPane = getContentPane();
		contentPane.setLayout(null);
		xmlName.setText("Enter XML Name To Be Created !");
		// ======== panel1 ========
		{
			// ---- Load_Game ----
			Load_Game.setText("Load Saved Game");
			Load_Game.setFont(new Font("Tahoma", Font.PLAIN, 24));
			Load_Game.setForeground(Color.blue);
			Load_Game.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					// call function to load a previously saved game
					Load_GameActionPerformed(e);
				}
			});

			// ---- Start_Game ----
			Start_Game.setText("Start New Game");
			Start_Game.setForeground(Color.blue);
			Start_Game.setFont(new Font("Tahoma", Font.PLAIN, 24));
			Start_Game.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					// start a new game
					Start_GameActionPerformed(e);
				}
			});

			GroupLayout panel1Layout = new GroupLayout(panel1);
			panel1.setLayout(panel1Layout);
			panel1Layout
					.setHorizontalGroup(panel1Layout
							.createParallelGroup()
							.addGroup(
									panel1Layout
											.createSequentialGroup()
											.addContainerGap(47,
													Short.MAX_VALUE)
											.addGroup(
													panel1Layout
															.createParallelGroup()
															.addComponent(
																	Load_Game,
																	GroupLayout.PREFERRED_SIZE,
																	255,
																	GroupLayout.PREFERRED_SIZE)
															.addComponent(
																	xmlName,
																	GroupLayout.PREFERRED_SIZE,
																	255,
																	GroupLayout.PREFERRED_SIZE)
															.addComponent(
																	Start_Game,
																	GroupLayout.PREFERRED_SIZE,
																	255,
																	GroupLayout.PREFERRED_SIZE))
											.addContainerGap(54,
													Short.MAX_VALUE)));
			panel1Layout.setVerticalGroup(panel1Layout.createParallelGroup()
					.addGroup(
							panel1Layout
									.createSequentialGroup()
									.addContainerGap()
									.addComponent(xmlName,
											GroupLayout.PREFERRED_SIZE, 30,
											GroupLayout.PREFERRED_SIZE)
									.addComponent(Load_Game,
											GroupLayout.PREFERRED_SIZE, 40,
											GroupLayout.PREFERRED_SIZE)
									.addGap(20, 20, 20)
									.addComponent(Start_Game,
											GroupLayout.PREFERRED_SIZE, 40,
											GroupLayout.PREFERRED_SIZE)
									.addContainerGap(23, Short.MAX_VALUE)));
		}
		contentPane.add(panel1);
		panel1.setBounds(0, 0, 345, 170);

		{ // compute preferred size
			Dimension preferredSize = new Dimension();
			for (int i = 0; i < contentPane.getComponentCount(); i++) {
				Rectangle bounds = contentPane.getComponent(i).getBounds();
				preferredSize.width = Math.max(bounds.x + bounds.width,
						preferredSize.width);
				preferredSize.height = Math.max(bounds.y + bounds.height,
						preferredSize.height);
			}
			Insets insets = contentPane.getInsets();
			preferredSize.width += insets.right;
			preferredSize.height += insets.bottom;
			contentPane.setMinimumSize(preferredSize);
			contentPane.setPreferredSize(preferredSize);
		}
		pack();
		setLocationRelativeTo(getOwner());
		// - End of component initialization
	}

	public void run() {
		GameEngine Form = new GameEngine(); // create Object from the Player
											// Class that will display the form
		Form.setVisible(true); // make it visible
	}

	/**
	 * Main method initializes objects of regions and players and add them to
	 * respective lists of objects and creates thread for class to start
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		objDSisters = new Region("Dolly Sisters", 1, 6);
		regionObjList.add(objDSisters);
		objUEstate = new Region("Unreal Estate", 2, 18);
		regionObjList.add(objUEstate);
		objDLanding = new Region("Dragon's Landing", 3, 12);
		regionObjList.add(objDLanding);
		objSGods = new Region("Small Gods", 4, 18);
		regionObjList.add(objSGods);
		objTScours = new Region("The Scours", 5, 6);
		regionObjList.add(objTScours);
		objTHippo = new Region("The Hippo", 6, 12);
		regionObjList.add(objTHippo);
		objTShades = new Region("The Shades", 7, 6);
		regionObjList.add(objTShades);
		objDimwell = new Region("Dimwell", 8, 6);
		regionObjList.add(objDimwell);
		objLongwall = new Region("Longwall", 9, 12);
		regionObjList.add(objLongwall);
		objIGods = new Region("Isle of Gods", 10, 12);
		regionObjList.add(objIGods);
		objSSleepers = new Region("Seven Sleepers", 11, 18);
		regionObjList.add(objSSleepers);
		objNHill = new Region("Nap Hill", 12, 12);
		regionObjList.add(objNHill);

		// call function to create deck of cards when game is loaded
		PlayerCards.createPlayerCardsDeck();
		String personality_temp;

		// call function to get personality card for player
		personality_temp = PersonalityCards.getPersonalityCard();
		objPRed = new Player("red", personality_temp);
		playerObjList.add(objPRed);

		personality_temp = PersonalityCards.getPersonalityCard();
		objPYellow = new Player("yellow", personality_temp);
		playerObjList.add(objPYellow);

		personality_temp = PersonalityCards.getPersonalityCard();
		objPGreen = new Player("green", personality_temp);
		playerObjList.add(objPGreen);

		personality_temp = PersonalityCards.getPersonalityCard();
		objPBlue = new Player("blue", personality_temp);
		playerObjList.add(objPBlue);

		(new Thread(new GameEngine())).start();
	}

	/**
	 * Function createXML() creates an XML file and saves the current state of
	 * regions and players in that file in form of tags by using the lists of
	 * objects to retrieve values to be saved
	 * 
	 * @throws throws exception in case of failure to create XML file
	 */
	public static void createXML(String XMLFileName) {
		DocumentBuilderFactory icFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder icBuilder;
		try {
			icBuilder = icFactory.newDocumentBuilder();
			// create Document object dom to write to file
			Document doc = icBuilder.newDocument();
			// create root element i.e. XML
			Element mainRootElement = doc
					.createElementNS("CreateXMLDOM", "XML");
			// add it to doc
			doc.appendChild(mainRootElement);
			// create children of root element as Region tag
			for (Region a : regionObjList) {
				mainRootElement.appendChild(getRegion(doc, a.rName, a.rNumber,
						a.rBuildingCost, a.rMinionNum, a.rTroubleMarker,
						a.rDemon, a.rTroll));
			}
			int count = 1;
			// create children of root element as Player tag
			for (Player a : playerObjList) {
				mainRootElement.appendChild(getPlayer(doc,a.color, a.buildingHold, a.cashHold, a.minionHold, a.personality, a.pCards, count , a.pNumber, a.pTurn));
				count++;
			}
			// output DOM XML to console
			Transformer transformer = TransformerFactory.newInstance()
					.newTransformer();
			transformer.setOutputProperty(OutputKeys.INDENT, "yes");
			DOMSource source = new DOMSource(doc);
			StreamResult file = new StreamResult(new File("./src/"
					+ XMLFileName));
			transformer.transform(source, file);
		}

		catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Function acts as a utility method that creates a child node of region for
	 * root element and in return adds the parameters as its own children.
	 * Basically it creates hierarchy of elements
	 * 
	 * @param doc
	 *            Document
	 * @param name
	 *            String name of region
	 * @param rNumber
	 *            Integer number of region
	 * @param rBuildingCost
	 *            Integer building cost of region
	 * @param rMinionNum
	 *            Integer minion number of region
	 * @param rTroubleMarker
	 *            Integer trouble markers in a region
	 * @param rDemon
	 *            Integer number of demon in region
	 * @param rTroll
	 *            Integer number of trolls in region
	 * @return Node that should be added to root element
	 */
	public static Node getRegion(Document doc, String name, int rNumber,
			int rBuildingCost, int rMinionNum, int rTroubleMarker, int rDemon,
			int rTroll) {
		// Region gets added to root element and all parameters as Region's
		// children
		Element region = doc.createElement("Region");
		Element Region_name = doc.createElement("Name");
		Region_name.appendChild(doc.createTextNode(name));
		region.appendChild(Region_name);
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
	public static Node getRegionElements(Document doc, Element element,
			String name, int value) {
		Element node = doc.createElement(name);
		node.appendChild(doc.createTextNode(Integer.toString(value)));
		return node;
	}

	/**
	 * Function acts as a utility method that creates a child node of player for
	 * root element and in return adds the parameters as its own children.
	 * Basically it creates hierarchy of players
	 * 
	 * @param doc
	 *            Document
	 * @param color
	 *            String color of player
	 * @param buildingHold
	 *            Integer number of buildings with player
	 * @param cashHold
	 *            Integer cash with a player
	 * @param minionHold
	 *            Integer number of minions with player
	 * @param personality
	 *            String personality card with player
	 * @param pCards
	 *            HashMap info of player cards with payer
	 * @param playerNumber
	 *            number of player
	 * @return Node of player that should be added to root element
	 */
	public static Node getPlayer(Document doc, String color, int buildingHold, int cashHold, int minionHold, String personality, 
			HashMap<String, List<Integer>> pCards, int playerNumber, int pNumber, int pTurn)
	{
		// Player gets added to root element and all parameters as Player's children
		Element player = doc.createElement("Player");
		//player.setAttribute("Number", Integer.toString(playerNumber));
		player.appendChild(getPlayerElements(doc, player, "Player_Number",
				pNumber));
		Element Player_color = doc.createElement("Color");
		Player_color.appendChild(doc.createTextNode(color));
		player.appendChild(Player_color);
		player.appendChild(getPlayerElements(doc, player, "Player_Turn",
				pTurn));
		player.appendChild(getPlayerElements(doc, player, "Building_Hold",
				buildingHold));
		player.appendChild(getPlayerElements(doc, player, "Cash_Hold",
				cashHold));
		player.appendChild(getPlayerElements(doc, player, "Minion_Hold",
				minionHold));
		Element Personality = doc.createElement("Personality");
		Personality.appendChild(doc.createTextNode(personality));
		player.appendChild(Personality);
		
		Element Player_Cards = doc.createElement("Player_Cards");
		Player_Cards.appendChild(doc.createTextNode(pCards.toString()));
		player.appendChild(Player_Cards);
		return player;
		
	}

	// utility method to create text node
	public static Node getPlayerElements(Document doc, Element element,
			String name, int value) {
		Element node = doc.createElement(name);
		node.appendChild(doc.createTextNode(Integer.toString(value)));
		return node;
	}
}
=======
package Game;

import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.*;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;
import org.jdom2.input.SAXBuilder;
import org.w3c.dom.*;

/**
 * <h1>Game Engine</h1>
 * <p>
 * Creates objects for both Player and Board class,<br>
 * controls the execution of entire game<br>
 * and saves and loads the XML file
 * </p>
 * 
 * @author nav_k
 *
 */
public class GameEngine extends JFrame implements Runnable {
	// objects for all regions
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

	// objects for players
	public static Player objPRed;
	public static Player objPYellow;
	public static Player objPGreen;
	public static Player objPBlue;
	static int BankHold = 120;
	static int numPlayers; // number of players
	static List<Player> playerObjList = new ArrayList<Player>(); // list of
																	// player
																	// objects
	static List<Region> regionObjList = new ArrayList<Region>(); // list of
																	// region
																	// objects

	static List<String> randomCards = new ArrayList<>();

	private JPanel panel1;
	private JButton Load_Game;
	private JButton Start_Game;
	public JTextField xmlName;

	public GameEngine() {
		initComponents();
	}

	/**
	 * Function reads from XML file, saves the values to objects of regions and
	 * players and load the corresponding tables using the same
	 * 
	 * @param e
	 *            the action performed
	 */
	private void Load_GameActionPerformed(ActionEvent e) {

		String XMLFileName = xmlName.getText();
		String pattern = "(.*?).xml";
		Pattern pCheck = Pattern.compile(pattern);
		Matcher mCheck = pCheck.matcher(XMLFileName);
		if (!mCheck.matches()) {
			xmlName.setText("Wrong Input!");
		} else {

			SAXBuilder builder = new SAXBuilder();
			File fileName = new File("./src/" + XMLFileName);
			if (fileName.exists()) {
				try {
					DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory
							.newInstance();
					DocumentBuilder docBuilder = docBuilderFactory
							.newDocumentBuilder();
					Document doc = docBuilder.parse(new File(
							"./src/"+XMLFileName));
					doc.getDocumentElement().normalize();

					// List of regions present in XML file
					NodeList listOfRegions = doc.getElementsByTagName("Region");
					int totalRegions = listOfRegions.getLength();
					// List of players present in XML file
					NodeList listOfPlayers = doc.getElementsByTagName("Player");

					// for regions
					for (int s = 0; s < listOfRegions.getLength(); s++) {
						Node firstRegionNode = listOfRegions.item(s);
						if (firstRegionNode.getNodeType() == Node.ELEMENT_NODE) {
							Element firstRegionElement = (Element) firstRegionNode;

							// get region number
							NodeList RegionNumberList = firstRegionElement
									.getElementsByTagName("RegionNumber");
							Element RegionNumberElement = (Element) RegionNumberList
									.item(0);
							NodeList RNList = RegionNumberElement
									.getChildNodes();
							// add region number to list of objects of region
							regionObjList.get(s).rNumber = Integer
									.parseInt(RNList.item(0).getNodeValue());

							// get name of region
							NodeList RegionNameList = (NodeList) firstRegionElement
									.getElementsByTagName("Name");
							Element RegionNameElement = (Element) RegionNameList
									.item(0);
							NodeList NameList = RegionNameElement
									.getChildNodes();
							// add region name to list of objects of region
							regionObjList.get(s).rName = NameList.item(0)
									.getNodeValue();

							// building cost of region
							NodeList CostList = firstRegionElement
									.getElementsByTagName("rBuildingCost");
							Element CostElement = (Element) CostList.item(0);
							NodeList rCostList = CostElement.getChildNodes();
							// add building cost to list of objects of region
							regionObjList.get(s).rBuildingCost = Integer
									.parseInt(rCostList.item(0).getNodeValue());

							// number of minions in region
							NodeList MinionList = firstRegionElement
									.getElementsByTagName("NumberOfMinions");
							Element MinionElement = (Element) MinionList
									.item(0);
							NodeList rMinionList = MinionElement
									.getChildNodes();
							// add minion number to list of objects of region
							regionObjList.get(s).rMinionNum = Integer
									.parseInt(rMinionList.item(0)
											.getNodeValue());

							// number of trouble markers in region
							NodeList TMList = firstRegionElement
									.getElementsByTagName("NumberOfrTroubleMarkers");
							Element TMElement = (Element) TMList.item(0);
							NodeList rTMList = TMElement.getChildNodes();
							// add trouble marker number to list of objects of
							// region
							regionObjList.get(s).rTroubleMarker = Integer
									.parseInt(rTMList.item(0).getNodeValue());

							// number of demons in region
							NodeList DList = firstRegionElement
									.getElementsByTagName("NumberOfrDemons");
							Element DElement = (Element) DList.item(0);
							NodeList DemonList = DElement.getChildNodes();
							// add demon number to list of objects of region
							regionObjList.get(s).rDemon = Integer
									.parseInt(DemonList.item(0).getNodeValue());

							// number of trolls in region
							NodeList TList = firstRegionElement
									.getElementsByTagName("NumberOfrTrolls");
							Element TElement = (Element) TList.item(0);
							NodeList TrollList = DElement.getChildNodes();
							// add troll number to list of objects of region
							regionObjList.get(s).rTroll = Integer
									.parseInt(TrollList.item(0).getNodeValue());
						} // end if
					}// end for

					// for players
					for (int s = 0; s < listOfPlayers.getLength(); s++) {
						Node firstPlayerNode = listOfPlayers.item(s);
						if (firstPlayerNode.getNodeType() == Node.ELEMENT_NODE) {
							Element firstPlayerElement = (Element) firstPlayerNode;

							// color of player's accessories
							NodeList ColorList = firstPlayerElement
									.getElementsByTagName("Color");
							Element ColorElement = (Element) ColorList.item(0);
							NodeList CList = ColorElement.getChildNodes();
							// add color to list of objects to player
							playerObjList.get(s).color = CList.item(0)
									.getNodeValue();

							// number of building with player currently
							NodeList BList = firstPlayerElement
									.getElementsByTagName("Building_Hold");
							Element BElement = (Element) BList.item(0);
							NodeList BuildingList = BElement.getChildNodes();
							// add number of buildings a player holds to list of
							// objects to player
							playerObjList.get(s).buildingHold = Integer
									.parseInt(BuildingList.item(0)
											.getNodeValue());

							// amount of cash with player currently
							NodeList CashList = firstPlayerElement
									.getElementsByTagName("Cash_Hold");
							Element CashElement = (Element) CashList.item(0);
							NodeList pCashList = CashElement.getChildNodes();
							// add cash a player holds to list of objects to
							// player
							playerObjList.get(s).cashHold = Integer
									.parseInt(pCashList.item(0).getNodeValue());

							// number of minions with player currently
							NodeList MinionList = firstPlayerElement
									.getElementsByTagName("Minion_Hold");
							Element MinionElement = (Element) MinionList
									.item(0);
							NodeList MList = MinionElement.getChildNodes();
							// add minions a player holds to list of objects to
							// player
							playerObjList.get(s).minionHold = Integer
									.parseInt(MList.item(0).getNodeValue());

							// personality card with player currently
							NodeList PersonalityList = firstPlayerElement
									.getElementsByTagName("Personality");
							Element PersonalityElement = (Element) PersonalityList
									.item(0);
							NodeList PList = PersonalityElement.getChildNodes();
							// add personality card to list of objects to player
							playerObjList.get(s).personality = PList.item(0)
									.getNodeValue();

							// player card with player currently
							NodeList PCardList = firstPlayerElement
									.getElementsByTagName("Player_Cards");
							Element PCardElement = (Element) PCardList.item(0);
							NodeList CardList = PCardElement.getChildNodes();
							String value = CardList.item(0).getNodeValue();
							HashMap<String, List<Integer>> player_Cards = new HashMap<String, List<Integer>>();
							value = value.substring(1, value.length() - 1); // remove
																			// curly
																			// brackets
							String[] keyValuePair = value.split("="); // split
																		// the
																		// string
																		// to
																		// create
																		// key-value
																		// pair
							String replace = keyValuePair[1].replace("[", "");
							String replace1 = replace.replace("]", "");
							List<String> tempList = new ArrayList<String>(
									Arrays.asList(replace1.split(",")));

							List<Integer> convertToInt = new ArrayList<Integer>();
							for (String a : tempList) {
								Integer temp = 0;
								temp = Integer.parseInt(a.trim());
								convertToInt.add(temp);
							}

							player_Cards.put(keyValuePair[0], convertToInt);
							playerObjList.get(s).pCards = player_Cards;
						}// end if
					}// end for

					// call constructor of SavedGame to load GUI
					SavedGame Loaded = new SavedGame();

					// load the table that contains info about regions
					for (int s = 0; s < listOfRegions.getLength(); s++) {
						Loaded.Loaded_Region_Info.setValueAt(
								regionObjList.get(s).rName, s, 0);
						Loaded.Loaded_Region_Info.setValueAt(
								regionObjList.get(s).rMinionNum, s, 1);
						Loaded.Loaded_Region_Info.setValueAt(
								regionObjList.get(s).rTroubleMarker, s, 3);
						Loaded.Loaded_Region_Info.setValueAt(
								regionObjList.get(s).rDemon, s, 4);
						Loaded.Loaded_Region_Info.setValueAt(
								regionObjList.get(s).rTroll, s, 5);
					}

					// load the table that contains info about players
					for (int s = 0; s < listOfPlayers.getLength(); s++) {
						Loaded.Loaded_Players_Info.setValueAt(
								playerObjList.get(s).color, s, 1);
						Loaded.Loaded_Players_Info.setValueAt(
								playerObjList.get(s).personality, s, 2);
						Loaded.Loaded_Players_Info.setValueAt(
								playerObjList.get(s).minionHold, s, 3);
						Loaded.Loaded_Players_Info.setValueAt(
								playerObjList.get(s).buildingHold, s, 4);
						Loaded.Loaded_Players_Info.setValueAt(
								playerObjList.get(s).cashHold, s, 5);
						Loaded.Loaded_Players_Info.setValueAt(
								playerObjList.get(s).pCards, s, 6);
					}
					Loaded.setVisible(true);
				}// end try
				catch (SAXParseException err) {
					System.out.println("** Parsing error" + ", line "
							+ err.getLineNumber() + ", uri "
							+ err.getSystemId());
					System.out.println(" " + err.getMessage());
				} catch (SAXException ee) {
					Exception x = ee.getException();
					((x == null) ? ee : x).printStackTrace();
				} catch (Throwable t) {
					t.printStackTrace();
				}
			} else
				JOptionPane.showMessageDialog(null,
						"No Saved File Exist To Load !");
		}
	}

	private void Start_GameActionPerformed(ActionEvent e) {
		// TODO add your code here

		if (e.getSource() == Start_Game) {

			NewGame Game = new NewGame(); // create Object from the Player Class
											// the will display the form
			Game.setVisible(true); // make it visible
			// Form.setVisible(true);
			// JOptionPane.showMessageDialog(null, "You've got it !");

		}
	}

	/**
	 * Function creates all the components to be displayed in the panel
	 */
	private void initComponents() {
		// Component initialization - DO NOT MODIFY

		panel1 = new JPanel();
		Load_Game = new JButton();
		Start_Game = new JButton();
		xmlName = new JTextField();

		// ======== this ========
		setTitle("Game Build1");
		Container contentPane = getContentPane();
		contentPane.setLayout(null);
		xmlName.setText("Enter XML Name To Be Created !");
		// ======== panel1 ========
		{
			// ---- Load_Game ----
			Load_Game.setText("Load Saved Game");
			Load_Game.setFont(new Font("Tahoma", Font.PLAIN, 24));
			Load_Game.setForeground(Color.blue);
			Load_Game.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					// call function to load a previously saved game
					Load_GameActionPerformed(e);
				}
			});

			// ---- Start_Game ----
			Start_Game.setText("Start New Game");
			Start_Game.setForeground(Color.blue);
			Start_Game.setFont(new Font("Tahoma", Font.PLAIN, 24));
			Start_Game.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					// start a new game
					Start_GameActionPerformed(e);
				}
			});

			GroupLayout panel1Layout = new GroupLayout(panel1);
			panel1.setLayout(panel1Layout);
			panel1Layout
					.setHorizontalGroup(panel1Layout
							.createParallelGroup()
							.addGroup(
									panel1Layout
											.createSequentialGroup()
											.addContainerGap(47,
													Short.MAX_VALUE)
											.addGroup(
													panel1Layout
															.createParallelGroup()
															.addComponent(
																	Load_Game,
																	GroupLayout.PREFERRED_SIZE,
																	255,
																	GroupLayout.PREFERRED_SIZE)
															.addComponent(
																	xmlName,
																	GroupLayout.PREFERRED_SIZE,
																	255,
																	GroupLayout.PREFERRED_SIZE)
															.addComponent(
																	Start_Game,
																	GroupLayout.PREFERRED_SIZE,
																	255,
																	GroupLayout.PREFERRED_SIZE))
											.addContainerGap(54,
													Short.MAX_VALUE)));
			panel1Layout.setVerticalGroup(panel1Layout.createParallelGroup()
					.addGroup(
							panel1Layout
									.createSequentialGroup()
									.addContainerGap()
									.addComponent(xmlName,
											GroupLayout.PREFERRED_SIZE, 30,
											GroupLayout.PREFERRED_SIZE)
									.addComponent(Load_Game,
											GroupLayout.PREFERRED_SIZE, 40,
											GroupLayout.PREFERRED_SIZE)
									.addGap(20, 20, 20)
									.addComponent(Start_Game,
											GroupLayout.PREFERRED_SIZE, 40,
											GroupLayout.PREFERRED_SIZE)
									.addContainerGap(23, Short.MAX_VALUE)));
		}
		contentPane.add(panel1);
		panel1.setBounds(0, 0, 345, 170);

		{ // compute preferred size
			Dimension preferredSize = new Dimension();
			for (int i = 0; i < contentPane.getComponentCount(); i++) {
				Rectangle bounds = contentPane.getComponent(i).getBounds();
				preferredSize.width = Math.max(bounds.x + bounds.width,
						preferredSize.width);
				preferredSize.height = Math.max(bounds.y + bounds.height,
						preferredSize.height);
			}
			Insets insets = contentPane.getInsets();
			preferredSize.width += insets.right;
			preferredSize.height += insets.bottom;
			contentPane.setMinimumSize(preferredSize);
			contentPane.setPreferredSize(preferredSize);
		}
		pack();
		setLocationRelativeTo(getOwner());
		// - End of component initialization
	}

	public void run() {
		GameEngine Form = new GameEngine(); // create Object from the Player
											// Class that will display the form
		Form.setVisible(true); // make it visible
	}

	/**
	 * Main method initializes objects of regions and players and add them to
	 * respective lists of objects and creates thread for class to start
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		objDSisters = new Region("Dolly Sisters", 1, 6);
		regionObjList.add(objDSisters);
		objUEstate = new Region("Unreal Estate", 2, 18);
		regionObjList.add(objUEstate);
		objDLanding = new Region("Dragon's Landing", 3, 12);
		regionObjList.add(objDLanding);
		objSGods = new Region("Small Gods", 4, 18);
		regionObjList.add(objSGods);
		objTScours = new Region("The Scours", 5, 6);
		regionObjList.add(objTScours);
		objTHippo = new Region("The Hippo", 6, 12);
		regionObjList.add(objTHippo);
		objTShades = new Region("The Shades", 7, 6);
		regionObjList.add(objTShades);
		objDimwell = new Region("Dimwell", 8, 6);
		regionObjList.add(objDimwell);
		objLongwall = new Region("Longwall", 9, 12);
		regionObjList.add(objLongwall);
		objIGods = new Region("Isle of Gods", 10, 12);
		regionObjList.add(objIGods);
		objSSleepers = new Region("Seven Sleepers", 11, 18);
		regionObjList.add(objSSleepers);
		objNHill = new Region("Nap Hill", 12, 12);
		regionObjList.add(objNHill);

		// call function to create deck of cards when game is loaded
		PlayerCards.createPlayerCardsDeck();
		String personality_temp;

		// call function to get personality card for player
		personality_temp = PersonalityCards.getPersonalityCard();
		objPRed = new Player("red", personality_temp);
		playerObjList.add(objPRed);

		personality_temp = PersonalityCards.getPersonalityCard();
		objPYellow = new Player("yellow", personality_temp);
		playerObjList.add(objPYellow);

		personality_temp = PersonalityCards.getPersonalityCard();
		objPGreen = new Player("green", personality_temp);
		playerObjList.add(objPGreen);

		personality_temp = PersonalityCards.getPersonalityCard();
		objPBlue = new Player("blue", personality_temp);
		playerObjList.add(objPBlue);

		(new Thread(new GameEngine())).start();
	}

	/**
	 * Function createXML() creates an XML file and saves the current state of
	 * regions and players in that file in form of tags by using the lists of
	 * objects to retrieve values to be saved
	 * 
	 * @throws throws exception in case of failure to create XML file
	 */
	public static void createXML(String XMLFileName) {
		DocumentBuilderFactory icFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder icBuilder;
		try {
			icBuilder = icFactory.newDocumentBuilder();
			// create Document object dom to write to file
			Document doc = icBuilder.newDocument();
			// create root element i.e. XML
			Element mainRootElement = doc
					.createElementNS("CreateXMLDOM", "XML");
			// add it to doc
			doc.appendChild(mainRootElement);
			// create children of root element as Region tag
			for (Region a : regionObjList) {
				mainRootElement.appendChild(getRegion(doc, a.rName, a.rNumber,
						a.rBuildingCost, a.rMinionNum, a.rTroubleMarker,
						a.rDemon, a.rTroll));
			}
			int count = 1;
			// create children of root element as Player tag
			for (Player a : playerObjList) {
				mainRootElement.appendChild(getPlayer(doc, a.color,
						a.buildingHold, a.cashHold, a.minionHold,
						a.personality, a.pCards, count));
				count++;
			}
			// output DOM XML to console
			Transformer transformer = TransformerFactory.newInstance()
					.newTransformer();
			transformer.setOutputProperty(OutputKeys.INDENT, "yes");
			DOMSource source = new DOMSource(doc);
			StreamResult file = new StreamResult(new File("./src/"
					+ XMLFileName));
			transformer.transform(source, file);
		}

		catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Function acts as a utility method that creates a child node of region for
	 * root element and in return adds the parameters as its own children.
	 * Basically it creates hierarchy of elements
	 * 
	 * @param doc
	 *            Document
	 * @param name
	 *            String name of region
	 * @param rNumber
	 *            Integer number of region
	 * @param rBuildingCost
	 *            Integer building cost of region
	 * @param rMinionNum
	 *            Integer minion number of region
	 * @param rTroubleMarker
	 *            Integer trouble markers in a region
	 * @param rDemon
	 *            Integer number of demon in region
	 * @param rTroll
	 *            Integer number of trolls in region
	 * @return Node that should be added to root element
	 */
	public static Node getRegion(Document doc, String name, int rNumber,
			int rBuildingCost, int rMinionNum, int rTroubleMarker, int rDemon,
			int rTroll) {
		// Region gets added to root element and all parameters as Region's
		// children
		Element region = doc.createElement("Region");
		Element Region_name = doc.createElement("Name");
		Region_name.appendChild(doc.createTextNode(name));
		region.appendChild(Region_name);
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
	public static Node getRegionElements(Document doc, Element element,
			String name, int value) {
		Element node = doc.createElement(name);
		node.appendChild(doc.createTextNode(Integer.toString(value)));
		return node;
	}

	/**
	 * Function acts as a utility method that creates a child node of player for
	 * root element and in return adds the parameters as its own children.
	 * Basically it creates hierarchy of players
	 * 
	 * @param doc
	 *            Document
	 * @param color
	 *            String color of player
	 * @param buildingHold
	 *            Integer number of buildings with player
	 * @param cashHold
	 *            Integer cash with a player
	 * @param minionHold
	 *            Integer number of minions with player
	 * @param personality
	 *            String personality card with player
	 * @param pCards
	 *            HashMap info of player cards with payer
	 * @param playerNumber
	 *            number of player
	 * @return Node of player that should be added to root element
	 */
	public static Node getPlayer(Document doc, String color, int buildingHold,
			int cashHold, int minionHold, String personality,
			HashMap<String, List<Integer>> pCards, int playerNumber) {
		// Player gets added to root element and all parameters as Player's
		// children
		Element player = doc.createElement("Player");
		player.setAttribute("Number", Integer.toString(playerNumber));
		Element Player_color = doc.createElement("Color");
		Player_color.appendChild(doc.createTextNode(color));
		player.appendChild(Player_color);
		player.appendChild(getPlayerElements(doc, player, "Building_Hold",
				buildingHold));
		player.appendChild(getPlayerElements(doc, player, "Cash_Hold", cashHold));
		player.appendChild(getPlayerElements(doc, player, "Minion_Hold",
				minionHold));
		Element Personality = doc.createElement("Personality");
		Personality.appendChild(doc.createTextNode(personality));
		player.appendChild(Personality);

		Element Player_Cards = doc.createElement("Player_Cards");
		Player_Cards.appendChild(doc.createTextNode(pCards.toString()));
		player.appendChild(Player_Cards);
		return player;

	}

	// utility method to create text node
	public static Node getPlayerElements(Document doc, Element element,
			String name, int value) {
		Element node = doc.createElement(name);
		node.appendChild(doc.createTextNode(Integer.toString(value)));
		return node;
	}
}
>>>>>>> 66f9cae7621215b40472ad8d9a2e8c07ef8bf09e

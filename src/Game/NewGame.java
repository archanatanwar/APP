package Game;

import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.*;
import javax.swing.table.*;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

/**
 * <h1>New Game</h1>
 * <p>
 * Creates GUI when user clicks to load a New game<br>
 * and fills in the info to be displayed in tables
 * </p>
 * 
 * @author nav_k
 *
 */
public class NewGame extends JFrame {
	private JPanel panel1;
	private JButton Two_Players;
	private JButton Three_Players;
	private JButton Four_Players;
	private JScrollPane scrollPane1;
	JTable Players_Info;
	private JScrollPane scrollPane2;
	private JTable Region_Info;
	private JButton Update_Region;
	private JButton Save_Game;
	private JButton Exit;
	public JTable Card_Info;
	public JScrollPane scrollPane3;
	public JTable Bank_Info;
	public JScrollPane scrollPane4;
	public JTextField xmlName;

	static List<Integer> greenList = new ArrayList<>();
	static List<Integer> brownList = new ArrayList<>();

	public NewGame() {
		initComponents();
	}

	public void setDefaultRegionStatus(String color) {
		GameEngine.objTScours.placeDefaultMinion(color);
		GameEngine.objTShades.placeDefaultMinion(color);
		GameEngine.objDSisters.placeDefaultMinion(color);
	}

	public void setRegionInfo() {
		for (int i = 0; i <= 11; i++) {

			String showMinion = "";
			String showBuilding = "0";
			Set<String> keys = GameEngine.regionObjList.get(i).H_Player
					.keySet();
			for (String key : keys) {
				showMinion = showMinion
						+ " "
						+ GameEngine.regionObjList.get(i).H_Player.get(key).pMinionRegionwise
						+ key.charAt(0);
				int numBuilding = GameEngine.regionObjList.get(i).H_Player
						.get(key).pbuildingRegionwise;
				if (numBuilding == 1) {
					showBuilding = GameEngine.regionObjList.get(i).H_Player
							.get(key).color;
				}
			}

			Region_Info.setValueAt(GameEngine.regionObjList.get(i).rName, i, 0);
			Region_Info.setValueAt(showMinion, i, 1);
			Region_Info.setValueAt(showBuilding, i, 2);
			Region_Info.setValueAt(
					GameEngine.regionObjList.get(i).rTroubleMarker, i, 3);
			Region_Info
					.setValueAt(GameEngine.regionObjList.get(i).rDemon, i, 4);
			Region_Info
					.setValueAt(GameEngine.regionObjList.get(i).rTroll, i, 5);
		}
	}

	private void Two_PlayersActionPerformed(ActionEvent e)
	{
		GameEngine.playerObjList.remove(3);
		GameEngine.playerObjList.remove(2);
		if (e.getSource() == Two_Players) 
		{
			for (int i = 0; i < Players_Info.getRowCount(); i++)
				for (int j = 0; j < Players_Info.getColumnCount(); j++) 
				{
					Players_Info.setValueAt("", i, j);
				}

			for (int i = 0; i <= 1; i++) 
			{
				GameEngine.playerObjList.get(i).initialisePlayer();
				setDefaultRegionStatus(GameEngine.playerObjList.get(i).color);
				GameEngine.playerObjList.get(i).initialPlayerStatus();
			}

		}

		// shuffle list of objects to randomly select first player
		Collections.shuffle(GameEngine.playerObjList);
		
		int turnIndex = GameEngine.playerObjList.get(0).pNumber;
		for (int i = 0; i <= 1; i++) 
		{
			for(Player obj : GameEngine.playerObjList)
			{
				if(obj.pNumber == turnIndex)
				{
					greenList = obj.pCards.get("Green");
					brownList = obj.pCards.get("Brown");
					String greenCards = "";
					String brownCards = "";
					if (greenList != null)
						greenCards = greenList.toString();
					if (brownList != null)
						brownCards = brownList.toString();
					Players_Info.setValueAt(obj.pNumber, i, 0);
					Players_Info.setValueAt(obj.color, i, 1);
					Players_Info.setValueAt(obj.personality, i, 2);
					Players_Info.setValueAt(obj.minionHold, i, 3);
					Players_Info.setValueAt(obj.buildingHold, i, 4);
					Players_Info.setValueAt(obj.cashHold, i, 5);
					Players_Info.setValueAt("G: " + greenCards + " B: " + brownCards,i, 6);
				}
			}
			turnIndex++;
			turnIndex = turnIndex % (GameEngine.playerObjList.size());
			if(turnIndex == 0)
			{
				turnIndex = 2;
			}
		}
		setRegionInfo();
		Bank_Info.setValueAt(GameEngine.BankHold, 0, 0);
		Two_Players.setEnabled(false);
		Three_Players.setEnabled(false);
		Four_Players.setEnabled(false);
	}

	// ///////////// Three PLAYERS Info Fill //////////////////////////////

	private void Three_PlayersActionPerformed(ActionEvent e) 
	{
		GameEngine.playerObjList.remove(3);
		if (e.getSource() == Three_Players) 
		{
			for (int i = 0; i < Players_Info.getRowCount(); i++)
				for (int j = 0; j < Players_Info.getColumnCount(); j++)
				{
					Players_Info.setValueAt("", i, j);
				}

			for (int i = 0; i <= 2; i++) 
			{
				GameEngine.playerObjList.get(i).initialisePlayer();
				setDefaultRegionStatus(GameEngine.playerObjList.get(i).color);
				GameEngine.playerObjList.get(i).initialPlayerStatus();
			}
		}

		// shuffle list of objects to randomly select first player
				Collections.shuffle(GameEngine.playerObjList);
				
				int turnIndex = GameEngine.playerObjList.get(0).pNumber;
				for (int i = 0; i <= 2; i++) 
				{
					for(Player obj : GameEngine.playerObjList)
					{
						if(obj.pNumber == turnIndex)
						{
							greenList = obj.pCards.get("Green");
							brownList = obj.pCards.get("Brown");
							String greenCards = "";
							String brownCards = "";
							if (greenList != null)
								greenCards = greenList.toString();
							if (brownList != null)
								brownCards = brownList.toString();
							Players_Info.setValueAt(obj.pNumber, i, 0);
							Players_Info.setValueAt(obj.color, i, 1);
							Players_Info.setValueAt(obj.personality, i, 2);
							Players_Info.setValueAt(obj.minionHold, i, 3);
							Players_Info.setValueAt(obj.buildingHold, i, 4);
							Players_Info.setValueAt(obj.cashHold, i, 5);
							Players_Info.setValueAt("G: " + greenCards + " B: " + brownCards,i, 6);
						}
					}
					turnIndex++;
					turnIndex = turnIndex % (GameEngine.playerObjList.size());
					if(turnIndex == 0)
					{
						turnIndex = 3;
					}
				}

		setRegionInfo();
		Bank_Info.setValueAt(GameEngine.BankHold, 0, 0);
		Two_Players.setEnabled(false);
		Three_Players.setEnabled(false);
		Four_Players.setEnabled(false);
	}

	private void Four_PlayersActionPerformed(ActionEvent e) 
	{
		if (e.getSource() == Four_Players)
		{
			for (int i = 0; i < Players_Info.getRowCount(); i++)
				for (int j = 0; j < Players_Info.getColumnCount(); j++)
				{
					Players_Info.setValueAt("", i, j);
				}

			for (int i = 0; i <= 3; i++) 
			{
				GameEngine.playerObjList.get(i).initialisePlayer();
				setDefaultRegionStatus(GameEngine.playerObjList.get(i).color);
				GameEngine.playerObjList.get(i).initialPlayerStatus();
			}
		}
		
		// shuffle list of objects to randomly select first player
		Collections.shuffle(GameEngine.playerObjList);
		
		int turnIndex = GameEngine.playerObjList.get(0).pNumber;
		for (int i = 0; i <= 3; i++) 
		{
			for(Player obj : GameEngine.playerObjList)
			{
				if(obj.pNumber == turnIndex)
				{
					greenList = obj.pCards.get("Green");
					brownList = obj.pCards.get("Brown");
					String greenCards = "";
					String brownCards = "";
					if (greenList != null)
						greenCards = greenList.toString();
					if (brownList != null)
						brownCards = brownList.toString();
					Players_Info.setValueAt(obj.pNumber, i, 0);
					Players_Info.setValueAt(obj.color, i, 1);
					Players_Info.setValueAt(obj.personality, i, 2);
					Players_Info.setValueAt(obj.minionHold, i, 3);
					Players_Info.setValueAt(obj.buildingHold, i, 4);
					Players_Info.setValueAt(obj.cashHold, i, 5);
					Players_Info.setValueAt("G: " + greenCards + " B: " + brownCards,i, 6);
				}
			}
			turnIndex++;
			turnIndex = turnIndex % (GameEngine.playerObjList.size());
			if(turnIndex == 0)
			{
				turnIndex = 4;
			}
		}
		// set player's turn 
		GameEngine.playerObjList.get(turnIndex).pTurn = 1;
		setRegionInfo();
		Bank_Info.setValueAt(GameEngine.BankHold, 0, 0);
		Two_Players.setEnabled(false);
		Three_Players.setEnabled(false);
		Four_Players.setEnabled(false);
	}

	private void button1ActionPerformed(ActionEvent e) {
		// TODO add your code here
	}

	private void Save_GameActionPerformed(ActionEvent e) {
		if (Two_Players.isEnabled()) {
			JOptionPane.showMessageDialog(null,
					"Please Select Number of Players !");
		} else {
			String XMLFileName = xmlName.getText();
			String pattern = "(.*?).xml";
			Pattern pCheck = Pattern.compile(pattern);
			Matcher mCheck = pCheck.matcher(XMLFileName);
			if (mCheck.matches()) {
				GameEngine.createXML(XMLFileName);
			} else {
				xmlName.setText("Wrong Input!");
			}
		}
	}

	private void initComponents() {
		// Component initialization -

		panel1 = new JPanel();
		Two_Players = new JButton();
		Three_Players = new JButton();
		Four_Players = new JButton();
		scrollPane1 = new JScrollPane();
		Players_Info = new JTable();
		scrollPane2 = new JScrollPane();
		Region_Info = new JTable();
		Card_Info = new JTable();
		Update_Region = new JButton();
		Save_Game = new JButton();
		Exit = new JButton();
		scrollPane3 = new JScrollPane();
		Bank_Info = new JTable();
		scrollPane4 = new JScrollPane();
		xmlName = new JTextField();
		// ======== this ========
		setTitle("New Game");
		Container contentPane = getContentPane();

		// ======== panel1 ========
		{

			// ---- Two_Players ----
			Two_Players.setText("Start Two Players Game ");
			Two_Players.setFont(new Font("Tahoma", Font.PLAIN, 20));
			Two_Players.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					button1ActionPerformed(e);
					Two_PlayersActionPerformed(e);
				}
			});

			// ---- Three_Players ----
			Three_Players.setText("Start Three Players Game ");
			Three_Players.setFont(new Font("Tahoma", Font.PLAIN, 20));
			Three_Players.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					Three_PlayersActionPerformed(e);
				}
			});

			// ---- Four_Players ----
			Four_Players.setText("Start Four Players Game ");
			Four_Players.setFont(new Font("Tahoma", Font.PLAIN, 20));
			Four_Players.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					Four_PlayersActionPerformed(e);
				}
			});

			// ======== scrollPane1 ========
			{

				// ---- Players_Info ----
				Players_Info.setModel(new DefaultTableModel(new Object[][] {
						{ "", null, null, null, null, "", "" },
						{ "", null, null, null, null, null, null },
						{ "", null, null, null, null, null, null },
						{ "", null, null, null, null, null, null }, },
						new String[] { "Players Turn", "Color", "Personality",
								"Number Of Minions", "Number Of Buildings",
								"Money", "Card Numbers" }));
				Players_Info.setForeground(new Color(0, 102, 102));
				Players_Info.setBackground(new Color(255, 255, 204));
				Players_Info.setFont(new Font("Tahoma",
						Font.BOLD | Font.ITALIC, 11));
				scrollPane1.setViewportView(Players_Info);
			}

			// ======== scrollPane2 ========
			{

				// ---- Region_Info ----
				Region_Info.setModel(new DefaultTableModel(new Object[][] {
						{ "Dolly Sisters", null, null, null, null, null },
						{ "Unreal Estate", null, null, null, null, null },
						{ "Dragon's Landing", null, null, null, null, null },
						{ "Small Gods", null, null, null, null, null },
						{ "The Scours", null, null, null, null, null },
						{ "The Hippo", null, null, null, null, null },
						{ "The Shades", null, null, null, null, null },
						{ "Dimwell", null, null, null, null, null },
						{ "Longwall", null, null, null, null, null },
						{ "Isle of Gods", null, null, null, null, null },
						{ "Seven Sleepers", null, null, null, null, null },
						{ "Nap Hill", null, null, null, null, null }, },
						new String[] { "Region", "No. Of Minions",
								"No. Of Buildings", "Trouble Markers",
								"Demons", "Trolls" }));
				Region_Info.setBackground(new Color(255, 255, 204));
				Region_Info.setForeground(new Color(0, 102, 102));
				Region_Info.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC,
						11));
				scrollPane2.setViewportView(Region_Info);
			}
			Card_Info.setModel(new DefaultTableModel(new Object[][] {
					{ PersonalityCards.getPersonalityNames.get(0),
							CityAreaCards.getCityCardsName.get(0),
							RandomEventCards.getRandomCardsName.get(0) },
					{ PersonalityCards.getPersonalityNames.get(1),
							CityAreaCards.getCityCardsName.get(1),
							RandomEventCards.getRandomCardsName.get(1) },
					{ PersonalityCards.getPersonalityNames.get(2),
							CityAreaCards.getCityCardsName.get(2),
							RandomEventCards.getRandomCardsName.get(2) },
					{ PersonalityCards.getPersonalityNames.get(3),
							CityAreaCards.getCityCardsName.get(3),
							RandomEventCards.getRandomCardsName.get(3) },
					{ PersonalityCards.getPersonalityNames.get(4),
							CityAreaCards.getCityCardsName.get(4),
							RandomEventCards.getRandomCardsName.get(4) },
					{ PersonalityCards.getPersonalityNames.get(5),
							CityAreaCards.getCityCardsName.get(5),
							RandomEventCards.getRandomCardsName.get(5) },
					{ PersonalityCards.getPersonalityNames.get(6),
							CityAreaCards.getCityCardsName.get(6),
							RandomEventCards.getRandomCardsName.get(6) },
					{ null, CityAreaCards.getCityCardsName.get(7),
							RandomEventCards.getRandomCardsName.get(7) },
					{ null, CityAreaCards.getCityCardsName.get(8),
							RandomEventCards.getRandomCardsName.get(8) },
					{ null, CityAreaCards.getCityCardsName.get(9),
							RandomEventCards.getRandomCardsName.get(9) },
					{ null, CityAreaCards.getCityCardsName.get(10),
							RandomEventCards.getRandomCardsName.get(10) },
					{ null, CityAreaCards.getCityCardsName.get(11),
							RandomEventCards.getRandomCardsName.get(11) }, },
					new String[] { "Personality Cards", "City Cards",
							"Random Event Cards" }));
			Card_Info.setForeground(new Color(0, 102, 102));
			Card_Info.setBackground(new Color(255, 255, 204));
			Card_Info.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 11));
			scrollPane3.setViewportView(Card_Info);

			Bank_Info.setModel(new DefaultTableModel(
					new Object[][] { { 120, }, },
					new String[] { "Available Cash with the Bank", }));
			Bank_Info.setForeground(new Color(0, 102, 102));
			Bank_Info.setBackground(new Color(255, 255, 204));
			Bank_Info.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 11));
			scrollPane4.setViewportView(Bank_Info);

			xmlName.setText("Enter XML Name To Be Created !");

			// ---- Update_Region ----
			Update_Region.setText("Update Region Information");
			Update_Region.setFont(new Font("Tahoma", Font.PLAIN, 18));

			// ---- Save_Game ----
			Save_Game.setText("Save Current Game");
			Save_Game.setFont(new Font("Tahoma", Font.PLAIN, 18));
			Save_Game.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					Save_GameActionPerformed(e);
				}
			});

			// ---- Exit ----
			Exit.setText("Leave Ankh-Morpork");
			Exit.setFont(new Font("Tahoma", Font.PLAIN, 18));

			GroupLayout panel1Layout = new GroupLayout(panel1);
			panel1.setLayout(panel1Layout);
			panel1Layout
					.setHorizontalGroup(panel1Layout
							.createParallelGroup()
							.addGroup(
									GroupLayout.Alignment.TRAILING,
									panel1Layout
											.createSequentialGroup()
											.addGap(23, 23, 23)
											.addGroup(
													panel1Layout
															.createParallelGroup(
																	GroupLayout.Alignment.TRAILING)
															.addGroup(
																	panel1Layout
																			.createSequentialGroup()
																			.addComponent(
																					Exit,
																					GroupLayout.PREFERRED_SIZE,
																					200,
																					GroupLayout.PREFERRED_SIZE)
																			.addGap(46,
																					46,
																					46)
																			.addComponent(
																					Save_Game,
																					GroupLayout.PREFERRED_SIZE,
																					200,
																					GroupLayout.PREFERRED_SIZE)
																			.addGap(46,
																					46,
																					46)
																			.addComponent(
																					xmlName,
																					GroupLayout.PREFERRED_SIZE,
																					200,
																					GroupLayout.PREFERRED_SIZE)
																			.addPreferredGap(
																					LayoutStyle.ComponentPlacement.RELATED,
																					47,
																					Short.MAX_VALUE)
																			.addComponent(
																					Update_Region,
																					GroupLayout.PREFERRED_SIZE,
																					200,
																					GroupLayout.PREFERRED_SIZE))
															.addComponent(
																	scrollPane1)
															.addComponent(
																	scrollPane2,
																	GroupLayout.Alignment.LEADING)
															.addComponent(
																	scrollPane3)
															.addComponent(
																	scrollPane4)
															.addGroup(
																	GroupLayout.Alignment.LEADING,
																	panel1Layout
																			.createSequentialGroup()
																			.addComponent(
																					Two_Players)
																			.addGap(41,
																					41,
																					41)
																			.addComponent(
																					Three_Players)
																			.addPreferredGap(
																					LayoutStyle.ComponentPlacement.RELATED,
																					42,
																					Short.MAX_VALUE)
																			.addComponent(
																					Four_Players)))
											.addGap(26, 26, 26)));
			panel1Layout
					.setVerticalGroup(panel1Layout
							.createParallelGroup()
							.addGroup(
									panel1Layout
											.createSequentialGroup()
											.addContainerGap()
											.addGroup(
													panel1Layout
															.createParallelGroup(
																	GroupLayout.Alignment.BASELINE)
															.addComponent(
																	Two_Players,
																	GroupLayout.PREFERRED_SIZE,
																	37,
																	GroupLayout.PREFERRED_SIZE)
															.addComponent(
																	Four_Players,
																	GroupLayout.PREFERRED_SIZE,
																	37,
																	GroupLayout.PREFERRED_SIZE)
															.addComponent(
																	Three_Players,
																	GroupLayout.PREFERRED_SIZE,
																	37,
																	GroupLayout.PREFERRED_SIZE))
											.addGap(18, 18, 18)
											.addComponent(scrollPane1,
													GroupLayout.PREFERRED_SIZE,
													87,
													GroupLayout.PREFERRED_SIZE)
											.addGap(15, 15, 15)
											.addComponent(scrollPane2,
													GroupLayout.PREFERRED_SIZE,
													215,
													GroupLayout.PREFERRED_SIZE)
											.addGap(15, 15, 15)
											.addComponent(scrollPane3,
													GroupLayout.PREFERRED_SIZE,
													187,
													GroupLayout.PREFERRED_SIZE)
											.addGap(15, 15, 15)
											.addComponent(scrollPane4,
													GroupLayout.PREFERRED_SIZE,
													40,
													GroupLayout.PREFERRED_SIZE)
											.addGap(27, 27, 27)
											.addGroup(
													panel1Layout
															.createParallelGroup(
																	GroupLayout.Alignment.BASELINE)
															.addComponent(
																	Update_Region,
																	GroupLayout.PREFERRED_SIZE,
																	38,
																	GroupLayout.PREFERRED_SIZE)
															.addComponent(
																	Exit,
																	GroupLayout.PREFERRED_SIZE,
																	38,
																	GroupLayout.PREFERRED_SIZE)
															.addComponent(
																	xmlName,
																	GroupLayout.PREFERRED_SIZE,
																	38,
																	GroupLayout.PREFERRED_SIZE)
															.addComponent(
																	Save_Game,
																	GroupLayout.PREFERRED_SIZE,
																	38,
																	GroupLayout.PREFERRED_SIZE))
											.addContainerGap(18,
													Short.MAX_VALUE)));
		}

		GroupLayout contentPaneLayout = new GroupLayout(contentPane);
		contentPane.setLayout(contentPaneLayout);
		contentPaneLayout.setHorizontalGroup(contentPaneLayout
				.createParallelGroup().addComponent(panel1,
						GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE,
						Short.MAX_VALUE));
		contentPaneLayout.setVerticalGroup(contentPaneLayout
				.createParallelGroup().addComponent(panel1,
						GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE,
						Short.MAX_VALUE));
		pack();
		setLocationRelativeTo(getOwner());
		// - End of component initialization
	}
}

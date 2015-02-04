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

import javax.swing.*;
import javax.swing.table.*;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;



/**
 * @author Ayman Hasaneen
 */
public class NewGame extends JFrame {
	public NewGame() {
		initComponents();
	}

								///////////////  TWO PLAYERS Info Fill //////////////////////////////

	private void Two_PlayersActionPerformed(ActionEvent e) {
		// TODO add your code here
		
		
		
		
		if(e.getSource()==Two_Players){
			for (int i = 0; i < Players_Info.getRowCount(); i++)
			      for(int j = 1; j < Players_Info.getColumnCount(); j++) {
			    	  Players_Info.setValueAt("", i, j);
			      }
			
			
			String [] Players = {"First Player","Second Player"};
			String [] colors = {"Red","Green","Blue","Yellow"};
			String [] personality = {"Lord Selachii","Dragon King of Arms","Commander Vime","Lord Rust","Chrysoprase ","Lord Vetinari","Lord de Worde"};
			
			String[] Card_Numbers = new String[48];
			for(int i=0; i<Card_Numbers.length; i++){
				Card_Numbers[i]=Integer.toString(i+1);
				
	       }
			
			ArrayList<String> playersList = new ArrayList<String>(Arrays.asList(Players));
			ArrayList<String> colorsList = new ArrayList<String>(Arrays.asList(colors));
			ArrayList<String> personalityList = new ArrayList<String>(Arrays.asList(personality));
			ArrayList<String> Card_NumbersList = new ArrayList<String>(Arrays.asList(Card_Numbers));
			
			
			long seed1 = System.nanoTime();
			long seed2 = (System.nanoTime()+1000000);
			long seed3 = (System.nanoTime()+2000000);
			long seed4 = (System.nanoTime()+3000000);
			Collections.shuffle(playersList, new Random(seed1));
			Collections.shuffle(colorsList, new Random(seed2));
			Collections.shuffle(personalityList, new Random(seed3));
			Collections.shuffle(Card_NumbersList, new Random(seed4)); 			
			
			String[] Cards1={Card_NumbersList.get(5),Card_NumbersList.get(10),Card_NumbersList.get(15),Card_NumbersList.get(20),Card_NumbersList.get(25)};
			String[] Cards2={Card_NumbersList.get(13),Card_NumbersList.get(18),Card_NumbersList.get(23),Card_NumbersList.get(28),Card_NumbersList.get(33)};
			
			ArrayList<String> p1_Cards = new ArrayList<String>(Arrays.asList(Cards1));
			ArrayList<String> p2_Cards = new ArrayList<String>(Arrays.asList(Cards2));
			
			//JOptionPane.showMessageDialog(null, "Two Players!");
			
	             
	            // Parses the file supplied into a JDOM document
	            
	            Players_Info.setValueAt(playersList.get(0),0,0);
	            Players_Info.setValueAt(colorsList.get(0), 0, 1);
	            Players_Info.setValueAt(personalityList.get(0), 0, 2);
	            Players_Info.setValueAt(p1_Cards, 0,6);
	            Players_Info.setValueAt("12", 0, 3);
	            Players_Info.setValueAt("6", 0, 4);
	            Players_Info.setValueAt("10", 0, 5);
	            
	            Players_Info.setValueAt(playersList.get(1),1,0);
	            Players_Info.setValueAt(colorsList.get(1), 1, 1);
	            Players_Info.setValueAt(personalityList.get(1), 1, 2);
	            Players_Info.setValueAt(p2_Cards, 1,6);
	            Players_Info.setValueAt("12", 1, 3);
	            Players_Info.setValueAt("6", 1, 4);
	            Players_Info.setValueAt("10", 1, 5);
	       
		}
		
	}
	
								///////////////  Three PLAYERS Info Fill //////////////////////////////

	private void Three_PlayersActionPerformed(ActionEvent e) {
		// TODO add your code here
		
		if(e.getSource()==Three_Players){
			
			for (int i = 0; i < Players_Info.getRowCount(); i++)
			      for(int j = 1; j < Players_Info.getColumnCount(); j++) {
			    	  Players_Info.setValueAt("", i, j);
			      }
			
			String [] Players = {"First Player","Second Player","Third Player","Fourth Player"};
			String [] colors = {"Red","Green","Blue","Yellow"};
			String [] personality = {"Lord Selachii","Dragon King of Arms","Commander Vime","Lord Rust","Chrysoprase ","Lord Vetinari","Lord de Worde"};
			
			ArrayList<String> playersList = new ArrayList<String>(Arrays.asList(Players));
			ArrayList<String> colorsList = new ArrayList<String>(Arrays.asList(colors));
			ArrayList<String> personalityList = new ArrayList<String>(Arrays.asList(personality));
			
			
			String[] Card_Numbers = new String[48];
			for(int i=0; i<Card_Numbers.length; i++){
				Card_Numbers[i]=Integer.toString(i+1);
				
	       }
			
			ArrayList<String> Card_NumbersList = new ArrayList<String>(Arrays.asList(Card_Numbers));
			
			
			long seed1 = System.nanoTime();
			long seed2 = (System.nanoTime()+1000000);
			long seed3 = (System.nanoTime()+2000000);
			long seed4 = (System.nanoTime()+3000000);
			Collections.shuffle(playersList, new Random(seed1));
			Collections.shuffle(colorsList, new Random(seed2));
			Collections.shuffle(personalityList, new Random(seed3));
			Collections.shuffle(Card_NumbersList, new Random(seed4)); 			
			
			String[] Cards1={Card_NumbersList.get(5),Card_NumbersList.get(10),Card_NumbersList.get(15),Card_NumbersList.get(20),Card_NumbersList.get(25)};
			String[] Cards2={Card_NumbersList.get(13),Card_NumbersList.get(18),Card_NumbersList.get(23),Card_NumbersList.get(28),Card_NumbersList.get(33)};
			String[] Cards3={Card_NumbersList.get(21),Card_NumbersList.get(26),Card_NumbersList.get(31),Card_NumbersList.get(36),Card_NumbersList.get(41)};
			String[] Cards4={Card_NumbersList.get(29),Card_NumbersList.get(34),Card_NumbersList.get(39),Card_NumbersList.get(44),Card_NumbersList.get(47)};
			
			ArrayList<String> p1_Cards = new ArrayList<String>(Arrays.asList(Cards1));
			ArrayList<String> p2_Cards = new ArrayList<String>(Arrays.asList(Cards2));
			ArrayList<String> p3_Cards = new ArrayList<String>(Arrays.asList(Cards3));
			ArrayList<String> p4_Cards = new ArrayList<String>(Arrays.asList(Cards4));
		
	             
	            // Parses the file supplied into a JDOM document
	            
	            
	            Players_Info.setValueAt(playersList.get(0),0,0);
	            Players_Info.setValueAt(colorsList.get(0), 0, 1);
	            Players_Info.setValueAt(personalityList.get(0), 0, 2);
	            Players_Info.setValueAt(p1_Cards, 0,6);
	            Players_Info.setValueAt("12", 0, 3);
	            Players_Info.setValueAt("6", 0, 4);
	            Players_Info.setValueAt("10", 0, 5);
	            
	            
	            Players_Info.setValueAt(playersList.get(1),1,0);
	            Players_Info.setValueAt(colorsList.get(1), 1, 1);
	            Players_Info.setValueAt(personalityList.get(1), 1, 2);
	            Players_Info.setValueAt(p2_Cards, 1,6);
	            Players_Info.setValueAt("12", 1, 3);
	            Players_Info.setValueAt("6", 1, 4);
	            Players_Info.setValueAt("10", 1, 5);
	            
	            Players_Info.setValueAt(playersList.get(2),2,0);
	            Players_Info.setValueAt(colorsList.get(2),2, 1);
	            Players_Info.setValueAt(personalityList.get(2), 2, 2);
	            Players_Info.setValueAt(p3_Cards, 2,6);
	            Players_Info.setValueAt("12", 2, 3);
	            Players_Info.setValueAt("6", 2, 4);
	            Players_Info.setValueAt("10", 2, 5);
	       

				
		}
	}
								///////////////  Four PLAYERS Info Fill //////////////////////////////

	private void Four_PlayersActionPerformed(ActionEvent e) {
		// TODO add your code here
		
		
		if(e.getSource()==Four_Players){
			
			for (int i = 0; i < Players_Info.getRowCount(); i++)
			      for(int j = 1; j < Players_Info.getColumnCount(); j++) {
			    	  Players_Info.setValueAt("", i, j);
			      }
			
			
			
			
			
			String [] Players = {"First Player","Second Player","Third Player","Fourth Player"};
			String [] colors = {"Red","Green","Blue","Yellow"};
			String [] personality = {"Lord Selachii","Dragon King of Arms","Commander Vime","Lord Rust","Chrysoprase ","Lord Vetinari","Lord de Worde"};
			
			ArrayList<String> playersList = new ArrayList<String>(Arrays.asList(Players));
			ArrayList<String> colorsList = new ArrayList<String>(Arrays.asList(colors));
			ArrayList<String> personalityList = new ArrayList<String>(Arrays.asList(personality));
			
			
			String[] Card_Numbers = new String[48];
			for(int i=0; i<Card_Numbers.length; i++){
				Card_Numbers[i]=Integer.toString(i+1);
				
	       }
			
			ArrayList<String> Card_NumbersList = new ArrayList<String>(Arrays.asList(Card_Numbers));
			
			
			long seed1 = System.nanoTime();
			long seed2 = (System.nanoTime()+1000000);
			long seed3 = (System.nanoTime()+2000000);
			long seed4 = (System.nanoTime()+3000000);
			Collections.shuffle(playersList, new Random(seed1));
			Collections.shuffle(colorsList, new Random(seed2));
			Collections.shuffle(personalityList, new Random(seed3));
			Collections.shuffle(Card_NumbersList, new Random(seed4)); 			
			
			String[] Cards1={Card_NumbersList.get(5),Card_NumbersList.get(10),Card_NumbersList.get(15),Card_NumbersList.get(20),Card_NumbersList.get(25)};
			String[] Cards2={Card_NumbersList.get(13),Card_NumbersList.get(18),Card_NumbersList.get(23),Card_NumbersList.get(28),Card_NumbersList.get(33)};
			String[] Cards3={Card_NumbersList.get(21),Card_NumbersList.get(26),Card_NumbersList.get(31),Card_NumbersList.get(36),Card_NumbersList.get(41)};
			String[] Cards4={Card_NumbersList.get(29),Card_NumbersList.get(34),Card_NumbersList.get(39),Card_NumbersList.get(44),Card_NumbersList.get(47)};
			
			
			ArrayList<String> p1_Cards = new ArrayList<String>(Arrays.asList(Cards1));
			ArrayList<String> p2_Cards = new ArrayList<String>(Arrays.asList(Cards2));
			ArrayList<String> p3_Cards = new ArrayList<String>(Arrays.asList(Cards3));
			ArrayList<String> p4_Cards = new ArrayList<String>(Arrays.asList(Cards4));
			//String[] Cards = {Integer.toString(Card_NumbersList.get(5)),};
			
				
		
		
	             
	            // Parses the file supplied into a JDOM document
	            
	            
	            Players_Info.setValueAt(playersList.get(0),0,0);
	            Players_Info.setValueAt(colorsList.get(0), 0, 1);
	            Players_Info.setValueAt(personalityList.get(0), 0, 2);
	            Players_Info.setValueAt(p1_Cards, 0,6);
	            Players_Info.setValueAt("12", 0, 3);
	            Players_Info.setValueAt("6", 0, 4);
	            Players_Info.setValueAt("10", 0, 5);
	            
	            Players_Info.setValueAt(playersList.get(1),1,0);
	            Players_Info.setValueAt(colorsList.get(1), 1, 1);
	            Players_Info.setValueAt(personalityList.get(1), 1, 2);
	            Players_Info.setValueAt(p2_Cards, 1,6);
	            Players_Info.setValueAt("12", 1, 3);
	            Players_Info.setValueAt("6", 1, 4);
	            Players_Info.setValueAt("10", 1, 5);
	            
	            Players_Info.setValueAt(playersList.get(2),2,0);
	            Players_Info.setValueAt(colorsList.get(2),2, 1);
	            Players_Info.setValueAt(personalityList.get(2), 2, 2);
	            Players_Info.setValueAt(p3_Cards, 2,6);
	            Players_Info.setValueAt("12", 2, 3);
	            Players_Info.setValueAt("6", 2, 4);
	            Players_Info.setValueAt("10", 2, 5);
	            
	            Players_Info.setValueAt(playersList.get(3),3,0);
	            Players_Info.setValueAt(colorsList.get(3), 3, 1);
	            Players_Info.setValueAt(personalityList.get(3), 3, 2);
	            Players_Info.setValueAt(p4_Cards, 3,6);
	            Players_Info.setValueAt("12", 3, 3);
	            Players_Info.setValueAt("6", 3, 4);
	            Players_Info.setValueAt("10", 3, 5);
	            
	            
	            
	      
		}
	}

	private void button1ActionPerformed(ActionEvent e) {
		// TODO add your code here
	}

	private void Save_GameActionPerformed(ActionEvent e) {
		// TODO add your code here
		/*Players game_status = new Players();
		game_status.Save_Game();*/
		try{
			
			Document doc = new Document();
			Element players = new Element("players");
			doc.setRootElement(players);
			
			/*NewGame saved_game = new NewGame();
			saved_game.Players_Info.getValueAt(3, 4);*/
			
								////////////////////////////// First Player ////////////////////////////////
			
			
				Element player1 = new Element("player1");
				player1.setAttribute("player1",  Players_Info.getValueAt(0, 0).toString());
			
				Element Player1_Color = new Element("Player1_Color");
				Player1_Color.setAttribute("color", Players_Info.getValueAt(0, 1).toString());
							
				Element Player1_personality = new Element("Player1_personality");
				Player1_personality.setAttribute("person",  Players_Info.getValueAt(0, 2).toString());
				
				Element Player1_CardNo = new Element("Player1_CardNo");
				Player1_CardNo.setAttribute("Card_No",  Players_Info.getValueAt(0, 6).toString());
				
				Element Player1_No_Minions = new Element("Player1_No_Minions");
				Player1_No_Minions.setAttribute("minions_No",  Players_Info.getValueAt(0, 3).toString());
				
				Element Player1_No_Buildings = new Element("Player1_No_Buildings");
				Player1_No_Buildings.setAttribute("Buildings_No",  Players_Info.getValueAt(0, 4).toString());
				
				Element Player1_Money = new Element("Player1_Money");
				Player1_Money.setAttribute("Money",  Players_Info.getValueAt(0, 5).toString());
				
							
				player1.addContent(Player1_Color);
				player1.addContent(Player1_personality);
				player1.addContent(Player1_CardNo);
				player1.addContent(Player1_No_Minions);
				player1.addContent(Player1_No_Buildings);
				player1.addContent(Player1_Money);
				
				players.addContent(player1);
			
			///////////////////////////////////// Second Player /////////////////////////////////////////////////
			
				Element player2 = new Element("player2" );
				player2.setAttribute("player2",  Players_Info.getValueAt(1, 0).toString());
				
				
				Element Player2_Color1 = new Element("Player2_Color");
				Player2_Color1.setAttribute("color",  Players_Info.getValueAt(1, 1).toString());
				
				Element Player2_personality1 = new Element("Player2_personality");
				Player2_personality1.setAttribute("person",  Players_Info.getValueAt(1, 2).toString());
				
				Element Player2_CardNo1 = new Element("Player2_CardNo");
				Player2_CardNo1.setAttribute("Card_No",  Players_Info.getValueAt(1, 6).toString());
				
				Element Player2_No_Minions1 = new Element("Player2_No_Minions");
				Player2_No_Minions1.setAttribute("minions_No",  Players_Info.getValueAt(1, 3).toString());
				
				Element Player2_No_Buildings1 = new Element("Player2_No_Buildings");
				Player2_No_Buildings1.setAttribute("Buildings_No",  Players_Info.getValueAt(1, 4).toString());
				
				Element Player2_Money1 = new Element("Player2_Money");
				Player2_Money1.setAttribute("Money",  Players_Info.getValueAt(1, 5).toString());
				
							
				player2.addContent(Player2_Color1);
				player2.addContent(Player2_personality1);
				player2.addContent(Player2_CardNo1);
				player2.addContent(Player2_No_Minions1);
				player2.addContent(Player2_No_Buildings1);
				player2.addContent(Player2_Money1);
				
				players.addContent(player2);
				
				
				///////////////////////////////////// Third Player /////////////////////////////////////////////////
							
				Element player3 = new Element( "player3");
				player3.setAttribute("player3",  Players_Info.getValueAt(2, 0).toString());
				
				Element Player3_Color1 = new Element("Player3_Color");
				Player3_Color1.setAttribute("color",  Players_Info.getValueAt(2, 1).toString());
				
				Element Player3_personality1 = new Element("Player3_personality");
				Player3_personality1.setAttribute("person",  Players_Info.getValueAt(2, 2).toString());
				
				Element Player3_CardNo1 = new Element("Player3_CardNo");
				Player3_CardNo1.setAttribute("Card_No",  Players_Info.getValueAt(2, 6).toString());
				
				Element Player3_No_Minions1 = new Element("Player3_No_Minions");
				Player3_No_Minions1.setAttribute("minions_No",  Players_Info.getValueAt(2, 3).toString());
				
				Element Player3_No_Buildings1 = new Element("Player3_No_Buildings");
				Player3_No_Buildings1.setAttribute("Buildings_No",  Players_Info.getValueAt(2, 4).toString());
				
				Element Player3_Money1 = new Element("Player3_Money");
				Player3_Money1.setAttribute("Money",  Players_Info.getValueAt(2, 5).toString());
				
				
				player3.addContent(Player3_Color1);
				player3.addContent(Player3_personality1);
				player3.addContent(Player3_CardNo1);
				player3.addContent(Player3_No_Minions1);
				player3.addContent(Player3_No_Buildings1);
				player3.addContent(Player3_Money1);
				
				players.addContent(player3);
				
				
				///////////////////////////////////// Fourth Player /////////////////////////////////////////////////
				
				Element player4 = new Element( "player4");
				player4.setAttribute("player4",  Players_Info.getValueAt(3, 0).toString());
				
				
				Element Player4_Color1 = new Element("Player4_Color");
				Player4_Color1.setAttribute("color",  Players_Info.getValueAt(3, 1).toString());
				
				Element Player4_personality1 = new Element("Player4_personality");
				Player4_personality1.setAttribute("person",  Players_Info.getValueAt(3, 2).toString());
				
				Element Player4_CardNo1 = new Element("Player4_CardNo");
				Player4_CardNo1.setAttribute("Card_No",  Players_Info.getValueAt(3, 6).toString());
				
				Element Player4_No_Minions1 = new Element("Player4_No_Minions");
				Player4_No_Minions1.setAttribute("minions_No",  Players_Info.getValueAt(3, 3).toString());
				
				Element Player4_No_Buildings1 = new Element("Player4_No_Buildings");
				Player4_No_Buildings1.setAttribute("Buildings_No",  Players_Info.getValueAt(3, 4).toString());
				
				Element Player4_Money1 = new Element("Player4_Money");
				Player4_Money1.setAttribute("Money",  Players_Info.getValueAt(3, 5).toString());
				
				
				player4.addContent(Player4_Color1);
				player4.addContent(Player4_personality1);
				player4.addContent(Player4_CardNo1);
				player4.addContent(Player4_No_Minions1);
				player4.addContent(Player4_No_Buildings1);
				player4.addContent(Player4_Money1);
				
				players.addContent(player4);
		
		XMLOutputter outputter = new XMLOutputter(Format.getPrettyFormat()) ;
		
		outputter.output(doc, new FileOutputStream(new File("./src/Saved_Game.xml"))); 
		}
		
			catch(Exception e1) {
				e1.printStackTrace();
				}
	}

	
	
	private void initComponents() {
		//Component initialization - 
		
		panel1 = new JPanel();
		Two_Players = new JButton();
		Three_Players = new JButton();
		Four_Players = new JButton();
		scrollPane1 = new JScrollPane();
		Players_Info = new JTable();
		scrollPane2 = new JScrollPane();
		Region_Info = new JTable();
		Update_Region = new JButton();
		Save_Game = new JButton();
		Exit = new JButton();

		//======== this ========
		setTitle("New Game");
		Container contentPane = getContentPane();

		//======== panel1 ========
		{

			
			//---- Two_Players ----
			Two_Players.setText("Start Two Players Game ");
			Two_Players.setFont(new Font("Tahoma", Font.PLAIN, 20));
			Two_Players.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					button1ActionPerformed(e);
					Two_PlayersActionPerformed(e);
				}
			});

			//---- Three_Players ----
			Three_Players.setText("Start Three Players Game ");
			Three_Players.setFont(new Font("Tahoma", Font.PLAIN, 20));
			Three_Players.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					Three_PlayersActionPerformed(e);
				}
			});

			//---- Four_Players ----
			Four_Players.setText("Start Four Players Game ");
			Four_Players.setFont(new Font("Tahoma", Font.PLAIN, 20));
			Four_Players.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					Four_PlayersActionPerformed(e);
				}
			});

			//======== scrollPane1 ========
			{

				//---- Players_Info ----
				Players_Info.setModel(new DefaultTableModel(
					new Object[][] {
						{"", null, null, null, null, "", ""},
						{"", null, null, null, null, null, null},
						{"", null, null, null, null, null, null},
						{"", null, null, null, null, null, null},
					},
					new String[] {
						"Players Turn", "Color", "Personality", "Number Of Minions", "Number Of Buildings", "Money", "Card Numbers"
					}
				));
				Players_Info.setForeground(new Color(0, 102, 102));
				Players_Info.setBackground(new Color(255, 255, 204));
				Players_Info.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 11));
				scrollPane1.setViewportView(Players_Info);
			}

			//======== scrollPane2 ========
			{

				//---- Region_Info ----
				Region_Info.setModel(new DefaultTableModel(
					new Object[][] {
						{"Dolly Sisters", null, null, null, null, null},
						{"Unreal Estate", null, null, null, null, null},
						{"Dragon's Landing", null, null, null, null, null},
						{"Small Gods", null, null, null, null, null},
						{"The Scours", null, null, null, null, null},
						{"The Hippo", null, null, null, null, null},
						{"The Shades", null, null, null, null, null},
						{"Dimwell", null, null, null, null, null},
						{"Longwall", null, null, null, null, null},
						{"Isle of Gods", null, null, null, null, null},
						{"Seven Sleepers", null, null, null, null, null},
						{"Nap Hill", null, null, null, null, null},
					},
					new String[] {
						"Region", "No. Of Minions", "No. Of Buildings", "Trouble Markers", "Demons", "Trolls"
					}
				));
				Region_Info.setBackground(new Color(255, 255, 204));
				Region_Info.setForeground(new Color(0, 102, 102));
				Region_Info.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 11));
				scrollPane2.setViewportView(Region_Info);
			}

			//---- Update_Region ----
			Update_Region.setText("Update Region Information");
			Update_Region.setFont(new Font("Tahoma", Font.PLAIN, 18));

			//---- Save_Game ----
			Save_Game.setText("Save Current Game");
			Save_Game.setFont(new Font("Tahoma", Font.PLAIN, 18));
			Save_Game.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					Save_GameActionPerformed(e);
				}
			});

			//---- Exit ----
			Exit.setText("Leave Ankh-Morpork");
			Exit.setFont(new Font("Tahoma", Font.PLAIN, 18));

			GroupLayout panel1Layout = new GroupLayout(panel1);
			panel1.setLayout(panel1Layout);
			panel1Layout.setHorizontalGroup(
				panel1Layout.createParallelGroup()
					.addGroup(GroupLayout.Alignment.TRAILING, panel1Layout.createSequentialGroup()
						.addGap(23, 23, 23)
						.addGroup(panel1Layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
							.addGroup(panel1Layout.createSequentialGroup()
								.addComponent(Exit, GroupLayout.PREFERRED_SIZE, 255, GroupLayout.PREFERRED_SIZE)
								.addGap(46, 46, 46)
								.addComponent(Save_Game, GroupLayout.PREFERRED_SIZE, 255, GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 47, Short.MAX_VALUE)
								.addComponent(Update_Region, GroupLayout.PREFERRED_SIZE, 255, GroupLayout.PREFERRED_SIZE))
							.addComponent(scrollPane1)
							.addComponent(scrollPane2, GroupLayout.Alignment.LEADING)
							.addGroup(GroupLayout.Alignment.LEADING, panel1Layout.createSequentialGroup()
								.addComponent(Two_Players)
								.addGap(41, 41, 41)
								.addComponent(Three_Players)
								.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 42, Short.MAX_VALUE)
								.addComponent(Four_Players)))
						.addGap(26, 26, 26))
			);
			panel1Layout.setVerticalGroup(
				panel1Layout.createParallelGroup()
					.addGroup(panel1Layout.createSequentialGroup()
						.addContainerGap()
						.addGroup(panel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
							.addComponent(Two_Players, GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE)
							.addComponent(Four_Players, GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE)
							.addComponent(Three_Players, GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE))
						.addGap(18, 18, 18)
						.addComponent(scrollPane1, GroupLayout.PREFERRED_SIZE, 87, GroupLayout.PREFERRED_SIZE)
						.addGap(15, 15, 15)
						.addComponent(scrollPane2, GroupLayout.PREFERRED_SIZE, 215, GroupLayout.PREFERRED_SIZE)
						.addGap(27, 27, 27)
						.addGroup(panel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
							.addComponent(Update_Region, GroupLayout.PREFERRED_SIZE, 38, GroupLayout.PREFERRED_SIZE)
							.addComponent(Exit, GroupLayout.PREFERRED_SIZE, 38, GroupLayout.PREFERRED_SIZE)
							.addComponent(Save_Game, GroupLayout.PREFERRED_SIZE, 38, GroupLayout.PREFERRED_SIZE))
						.addContainerGap(18, Short.MAX_VALUE))
			);
		}

		GroupLayout contentPaneLayout = new GroupLayout(contentPane);
		contentPane.setLayout(contentPaneLayout);
		contentPaneLayout.setHorizontalGroup(
			contentPaneLayout.createParallelGroup()
				.addComponent(panel1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
		);
		contentPaneLayout.setVerticalGroup(
			contentPaneLayout.createParallelGroup()
				.addComponent(panel1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
		);
		pack();
		setLocationRelativeTo(getOwner());
		//  - End of component initialization 
	}

	//  Variables declaration - 
	
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
	// End of variables declaration  
}

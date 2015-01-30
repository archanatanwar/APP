package Ayman;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;

import javax.swing.*;
import javax.swing.table.*;

import org.jdom2.Document;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;



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
			//JOptionPane.showMessageDialog(null, "Two Players!");
			SAXBuilder builder = new SAXBuilder();
			try {
	             
	            // Parses the file supplied into a JDOM document
	            Document readDoc = builder.build(new File("./src/Players_Info.xml"));
	            Players_Info.setValueAt(readDoc.getRootElement().getChild("player1").getChild("Player1_Color").getAttributeValue("color"), 0, 1);
	            Players_Info.setValueAt(readDoc.getRootElement().getChild("player1").getChild("Player1_personality").getAttributeValue("person"), 0, 2);
	            Players_Info.setValueAt(readDoc.getRootElement().getChild("player1").getChild("Player1_CardNo").getAttributeValue("Card_No"), 0,6);
	            Players_Info.setValueAt(readDoc.getRootElement().getChild("player1").getChild("Player1_No_Minions").getAttributeValue("minions_No"), 0, 3);
	            Players_Info.setValueAt(readDoc.getRootElement().getChild("player1").getChild("Player1_No_Buildings").getAttributeValue("Buildings_No"), 0, 4);
	            Players_Info.setValueAt(readDoc.getRootElement().getChild("player1").getChild("Player1_Money").getAttributeValue("Money"), 0, 5);
	            
	            Players_Info.setValueAt(readDoc.getRootElement().getChild("player2").getChild("Player2_Color").getAttributeValue("color"), 1, 1);
	            Players_Info.setValueAt(readDoc.getRootElement().getChild("player2").getChild("Player2_personality").getAttributeValue("person"), 1, 2);
	            Players_Info.setValueAt(readDoc.getRootElement().getChild("player2").getChild("Player2_CardNo").getAttributeValue("Card_No"), 1,6);
	            Players_Info.setValueAt(readDoc.getRootElement().getChild("player2").getChild("Player2_No_Minions").getAttributeValue("minions_No"), 1, 3);
	            Players_Info.setValueAt(readDoc.getRootElement().getChild("player2").getChild("Player2_No_Buildings").getAttributeValue("Buildings_No"), 1, 4);
	            Players_Info.setValueAt(readDoc.getRootElement().getChild("player2").getChild("Player2_Money").getAttributeValue("Money"), 1, 5);
	        }

				catch (JDOMException e1) {
		            e1.printStackTrace();
		        }
		         
		        catch (IOException e1) {
		            // TODO Auto-generated catch block
		            e1.printStackTrace();
		        }
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
			
			SAXBuilder builder = new SAXBuilder();
			try {
	             
	            // Parses the file supplied into a JDOM document
	            Document readDoc = builder.build(new File("./src/Players_Info.xml"));
	            Players_Info.setValueAt(readDoc.getRootElement().getChild("player1").getChild("Player1_Color").getAttributeValue("color"), 0, 1);
	            Players_Info.setValueAt(readDoc.getRootElement().getChild("player1").getChild("Player1_personality").getAttributeValue("person"), 0, 2);
	            Players_Info.setValueAt(readDoc.getRootElement().getChild("player1").getChild("Player1_CardNo").getAttributeValue("Card_No"), 0,6);
	            Players_Info.setValueAt(readDoc.getRootElement().getChild("player1").getChild("Player1_No_Minions").getAttributeValue("minions_No"), 0, 3);
	            Players_Info.setValueAt(readDoc.getRootElement().getChild("player1").getChild("Player1_No_Buildings").getAttributeValue("Buildings_No"), 0, 4);
	            Players_Info.setValueAt(readDoc.getRootElement().getChild("player1").getChild("Player1_Money").getAttributeValue("Money"), 0, 5);
	            
	            Players_Info.setValueAt(readDoc.getRootElement().getChild("player2").getChild("Player2_Color").getAttributeValue("color"), 1, 1);
	            Players_Info.setValueAt(readDoc.getRootElement().getChild("player2").getChild("Player2_personality").getAttributeValue("person"), 1, 2);
	            Players_Info.setValueAt(readDoc.getRootElement().getChild("player2").getChild("Player2_CardNo").getAttributeValue("Card_No"), 1,6);
	            Players_Info.setValueAt(readDoc.getRootElement().getChild("player2").getChild("Player2_No_Minions").getAttributeValue("minions_No"), 1, 3);
	            Players_Info.setValueAt(readDoc.getRootElement().getChild("player2").getChild("Player2_No_Buildings").getAttributeValue("Buildings_No"), 1, 4);
	            Players_Info.setValueAt(readDoc.getRootElement().getChild("player2").getChild("Player2_Money").getAttributeValue("Money"), 1, 5);
	            
	            Players_Info.setValueAt(readDoc.getRootElement().getChild("player3").getChild("Player3_Color").getAttributeValue("color"), 2, 1);
	            Players_Info.setValueAt(readDoc.getRootElement().getChild("player3").getChild("Player3_personality").getAttributeValue("person"), 2, 2);
	            Players_Info.setValueAt(readDoc.getRootElement().getChild("player3").getChild("Player3_CardNo").getAttributeValue("Card_No"), 2,6);
	            Players_Info.setValueAt(readDoc.getRootElement().getChild("player3").getChild("Player3_No_Minions").getAttributeValue("minions_No"), 2, 3);
	            Players_Info.setValueAt(readDoc.getRootElement().getChild("player3").getChild("Player3_No_Buildings").getAttributeValue("Buildings_No"), 2, 4);
	            Players_Info.setValueAt(readDoc.getRootElement().getChild("player3").getChild("Player3_Money").getAttributeValue("Money"), 2, 5);
	        }

				catch (JDOMException e1) {
		            e1.printStackTrace();
		        }
		         
		        catch (IOException e1) {
		            // TODO Auto-generated catch block
		            e1.printStackTrace();
		        }
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
			
			SAXBuilder builder = new SAXBuilder();
			try {
	             
	            // Parses the file supplied into a JDOM document
	            Document readDoc = builder.build(new File("./src/Players_Info.xml"));
	            Players_Info.setValueAt(readDoc.getRootElement().getChild("player1").getChild("Player1_Color").getAttributeValue("color"), 0, 1);
	            Players_Info.setValueAt(readDoc.getRootElement().getChild("player1").getChild("Player1_personality").getAttributeValue("person"), 0, 2);
	            Players_Info.setValueAt(readDoc.getRootElement().getChild("player1").getChild("Player1_CardNo").getAttributeValue("Card_No"), 0,6);
	            Players_Info.setValueAt(readDoc.getRootElement().getChild("player1").getChild("Player1_No_Minions").getAttributeValue("minions_No"), 0, 3);
	            Players_Info.setValueAt(readDoc.getRootElement().getChild("player1").getChild("Player1_No_Buildings").getAttributeValue("Buildings_No"), 0, 4);
	            Players_Info.setValueAt(readDoc.getRootElement().getChild("player1").getChild("Player1_Money").getAttributeValue("Money"), 0, 5);
	            
	            Players_Info.setValueAt(readDoc.getRootElement().getChild("player2").getChild("Player2_Color").getAttributeValue("color"), 1, 1);
	            Players_Info.setValueAt(readDoc.getRootElement().getChild("player2").getChild("Player2_personality").getAttributeValue("person"), 1, 2);
	            Players_Info.setValueAt(readDoc.getRootElement().getChild("player2").getChild("Player2_CardNo").getAttributeValue("Card_No"), 1,6);
	            Players_Info.setValueAt(readDoc.getRootElement().getChild("player2").getChild("Player2_No_Minions").getAttributeValue("minions_No"), 1, 3);
	            Players_Info.setValueAt(readDoc.getRootElement().getChild("player2").getChild("Player2_No_Buildings").getAttributeValue("Buildings_No"), 1, 4);
	            Players_Info.setValueAt(readDoc.getRootElement().getChild("player2").getChild("Player2_Money").getAttributeValue("Money"), 1, 5);
	            
	            Players_Info.setValueAt(readDoc.getRootElement().getChild("player3").getChild("Player3_Color").getAttributeValue("color"), 2, 1);
	            Players_Info.setValueAt(readDoc.getRootElement().getChild("player3").getChild("Player3_personality").getAttributeValue("person"), 2, 2);
	            Players_Info.setValueAt(readDoc.getRootElement().getChild("player3").getChild("Player3_CardNo").getAttributeValue("Card_No"), 2,6);
	            Players_Info.setValueAt(readDoc.getRootElement().getChild("player3").getChild("Player3_No_Minions").getAttributeValue("minions_No"), 2, 3);
	            Players_Info.setValueAt(readDoc.getRootElement().getChild("player3").getChild("Player3_No_Buildings").getAttributeValue("Buildings_No"), 2, 4);
	            Players_Info.setValueAt(readDoc.getRootElement().getChild("player3").getChild("Player3_Money").getAttributeValue("Money"), 2, 5);
	            
	            Players_Info.setValueAt(readDoc.getRootElement().getChild("player4").getChild("Player4_Color").getAttributeValue("color"), 3, 1);
	            Players_Info.setValueAt(readDoc.getRootElement().getChild("player4").getChild("Player4_personality").getAttributeValue("person"), 3, 2);
	            Players_Info.setValueAt(readDoc.getRootElement().getChild("player4").getChild("Player4_CardNo").getAttributeValue("Card_No"), 3,6);
	            Players_Info.setValueAt(readDoc.getRootElement().getChild("player4").getChild("Player4_No_Minions").getAttributeValue("minions_No"), 3, 3);
	            Players_Info.setValueAt(readDoc.getRootElement().getChild("player4").getChild("Player4_No_Buildings").getAttributeValue("Buildings_No"), 3, 4);
	            Players_Info.setValueAt(readDoc.getRootElement().getChild("player4").getChild("Player4_Money").getAttributeValue("Money"), 3, 5);
	        }

				catch (JDOMException e1) {
		            e1.printStackTrace();
		        }
		         
		        catch (IOException e1) {
		            // TODO Auto-generated catch block
		            e1.printStackTrace();
		        }
		}
	}

	private void button1ActionPerformed(ActionEvent e) {
		// TODO add your code here
	}

	
	
	
	
	
	
	

	private void initComponents() {
		//  Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
		// Generated using JFormDesigner Evaluation license - Iym Husa
		panel1 = new JPanel();
		Two_Players = new JButton();
		Three_Players = new JButton();
		Four_Players = new JButton();
		scrollPane1 = new JScrollPane();
		Players_Info = new JTable();

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
						{"First Player", null, null, null, null, "", ""},
						{"Second Player", null, null, null, null, null, null},
						{"Third Player", null, null, null, null, null, null},
						{"Fourth Player", null, null, null, null, null, null},
					},
					new String[] {
						"Players", "Color", "Personality", "Number Of Minions", "Number Of Buildings", "Money", "Card Numbers"
					}
				));
				Players_Info.setForeground(new Color(0, 102, 102));
				Players_Info.setBackground(new Color(255, 255, 204));
				scrollPane1.setViewportView(Players_Info);
			}

			GroupLayout panel1Layout = new GroupLayout(panel1);
			panel1.setLayout(panel1Layout);
			panel1Layout.setHorizontalGroup(
				panel1Layout.createParallelGroup()
					.addGroup(panel1Layout.createSequentialGroup()
						.addGap(19, 19, 19)
						.addGroup(panel1Layout.createParallelGroup()
							.addComponent(scrollPane1, GroupLayout.DEFAULT_SIZE, 858, Short.MAX_VALUE)
							.addGroup(panel1Layout.createSequentialGroup()
								.addComponent(Two_Players)
								.addGap(57, 57, 57)
								.addComponent(Three_Players)
								.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 26, Short.MAX_VALUE)
								.addComponent(Four_Players)))
						.addGap(30, 30, 30))
			);
			panel1Layout.setVerticalGroup(
				panel1Layout.createParallelGroup()
					.addGroup(panel1Layout.createSequentialGroup()
						.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addGroup(panel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
							.addComponent(Two_Players, GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE)
							.addComponent(Four_Players, GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE)
							.addComponent(Three_Players, GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE))
						.addGap(18, 18, 18)
						.addComponent(scrollPane1, GroupLayout.PREFERRED_SIZE, 87, GroupLayout.PREFERRED_SIZE)
						.addGap(27, 27, 27))
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
				.addGroup(contentPaneLayout.createSequentialGroup()
					.addComponent(panel1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(0, 0, Short.MAX_VALUE))
		);
		pack();
		setLocationRelativeTo(getOwner());
		
	}

	// Variables declaration
	
	private JPanel panel1;
	private JButton Two_Players;
	private JButton Three_Players;
	private JButton Four_Players;
	private JScrollPane scrollPane1;
	private JTable Players_Info;
	
}

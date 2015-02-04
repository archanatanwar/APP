import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;

import javax.swing.*;

import org.jdom2.Document;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;



/**
 * @author Ayman Hasaneen
 */
public class Build1 extends JFrame implements Runnable {
	public Build1() {
		initComponents();
	}

	private void Load_GameActionPerformed(ActionEvent e) {
		// TODO add your code here
		SAXBuilder builder = new SAXBuilder();
		File f = new File("./src/Saved_Game.xml");
		 
		  if(f.exists()){
		try {
            
			
					
			            Document readDoc = builder.build(new File("./src/Saved_Game.xml"));
			            SavedGame Loaded =new SavedGame();
			          
			            Loaded.Loaded_Players_Info.setValueAt(readDoc.getRootElement().getChild("player1").getAttributeValue("player1"), 0, 0);
						Loaded.Loaded_Players_Info.setValueAt(readDoc.getRootElement().getChild("player1").getChild("Player1_Color").getAttributeValue("color"), 0, 1);
						Loaded.Loaded_Players_Info.setValueAt(readDoc.getRootElement().getChild("player1").getChild("Player1_personality").getAttributeValue("person"), 0, 2);
						Loaded.Loaded_Players_Info.setValueAt(readDoc.getRootElement().getChild("player1").getChild("Player1_CardNo").getAttributeValue("Card_No"), 0, 6);
						Loaded.Loaded_Players_Info.setValueAt(readDoc.getRootElement().getChild("player1").getChild("Player1_No_Minions").getAttributeValue("minions_No"), 0, 3);
						Loaded.Loaded_Players_Info.setValueAt(readDoc.getRootElement().getChild("player1").getChild("Player1_No_Buildings").getAttributeValue("Buildings_No"), 0, 4);
						Loaded.Loaded_Players_Info.setValueAt(readDoc.getRootElement().getChild("player1").getChild("Player1_Money").getAttributeValue("Money"), 0, 5);
						
						Loaded.Loaded_Players_Info.setValueAt(readDoc.getRootElement().getChild("player2").getAttributeValue("player2"), 1, 0);
						Loaded.Loaded_Players_Info.setValueAt(readDoc.getRootElement().getChild("player2").getChild("Player2_Color").getAttributeValue("color"), 1, 1);
						Loaded.Loaded_Players_Info.setValueAt(readDoc.getRootElement().getChild("player2").getChild("Player2_personality").getAttributeValue("person"), 1, 2);
						Loaded.Loaded_Players_Info.setValueAt(readDoc.getRootElement().getChild("player2").getChild("Player2_CardNo").getAttributeValue("Card_No"), 1, 6);
						Loaded.Loaded_Players_Info.setValueAt(readDoc.getRootElement().getChild("player2").getChild("Player2_No_Minions").getAttributeValue("minions_No"), 1, 3);
						Loaded.Loaded_Players_Info.setValueAt(readDoc.getRootElement().getChild("player2").getChild("Player2_No_Buildings").getAttributeValue("Buildings_No"), 1, 4);
						Loaded.Loaded_Players_Info.setValueAt(readDoc.getRootElement().getChild("player2").getChild("Player2_Money").getAttributeValue("Money"), 1, 5);
						
						Loaded.Loaded_Players_Info.setValueAt(readDoc.getRootElement().getChild("player3").getAttributeValue("player3"), 2, 0);
						Loaded.Loaded_Players_Info.setValueAt(readDoc.getRootElement().getChild("player3").getChild("Player3_Color").getAttributeValue("color"), 2, 1);
						Loaded.Loaded_Players_Info.setValueAt(readDoc.getRootElement().getChild("player3").getChild("Player3_personality").getAttributeValue("person"), 2, 2);
						Loaded.Loaded_Players_Info.setValueAt(readDoc.getRootElement().getChild("player3").getChild("Player3_CardNo").getAttributeValue("Card_No"), 2, 6);
						Loaded.Loaded_Players_Info.setValueAt(readDoc.getRootElement().getChild("player3").getChild("Player3_No_Minions").getAttributeValue("minions_No"), 2, 3);
						Loaded.Loaded_Players_Info.setValueAt(readDoc.getRootElement().getChild("player3").getChild("Player3_No_Buildings").getAttributeValue("Buildings_No"), 2, 4);
						Loaded.Loaded_Players_Info.setValueAt(readDoc.getRootElement().getChild("player3").getChild("Player3_Money").getAttributeValue("Money"), 2, 5);
						
						Loaded.Loaded_Players_Info.setValueAt(readDoc.getRootElement().getChild("player4").getAttributeValue("player4"), 3, 0);
						Loaded.Loaded_Players_Info.setValueAt(readDoc.getRootElement().getChild("player4").getChild("Player4_Color").getAttributeValue("color"), 3, 1);
						Loaded.Loaded_Players_Info.setValueAt(readDoc.getRootElement().getChild("player4").getChild("Player4_personality").getAttributeValue("person"), 3, 2);
						Loaded.Loaded_Players_Info.setValueAt(readDoc.getRootElement().getChild("player4").getChild("Player4_CardNo").getAttributeValue("Card_No"), 3, 6);
						Loaded.Loaded_Players_Info.setValueAt(readDoc.getRootElement().getChild("player4").getChild("Player4_No_Minions").getAttributeValue("minions_No"), 3, 3);
						Loaded.Loaded_Players_Info.setValueAt(readDoc.getRootElement().getChild("player4").getChild("Player4_No_Buildings").getAttributeValue("Buildings_No"), 3, 4);
						Loaded.Loaded_Players_Info.setValueAt(readDoc.getRootElement().getChild("player4").getChild("Player4_Money").getAttributeValue("Money"), 3, 5);
						
						
						
						Loaded.setVisible(true);
						
		        
					
			}
		
		
		

			catch (JDOMException e1) {
	            e1.printStackTrace();
	        }
	         
	        catch (IOException e1) {
	            // TODO Auto-generated catch block
	            e1.printStackTrace();
	        }
		  }
		  else JOptionPane.showMessageDialog(null, "No Saved File Exist To Load !");
		  
		
	}

	private void Start_GameActionPerformed(ActionEvent e) {
		// TODO add your code here
		
		if (e.getSource()==Start_Game ){
			
			NewGame Game = new NewGame();		// create Object from the Player Class the will display the form
			Game.setVisible(true); 	// make it visible
			//Form.setVisible(true);
			//JOptionPane.showMessageDialog(null, "You've got it !");
			
		}
	}

	private void initComponents() {
		//  Component initialization - DO NOT MODIFY  
	
		panel1 = new JPanel();
		Load_Game = new JButton();
		Start_Game = new JButton();

		//======== this ========
		setTitle("Game Build1");
		Container contentPane = getContentPane();
		contentPane.setLayout(null);

		//======== panel1 ========
		{

			


			//---- Load_Game ----
			Load_Game.setText("Load Saved Game");
			Load_Game.setFont(new Font("Tahoma", Font.PLAIN, 24));
			Load_Game.setForeground(Color.blue);
			Load_Game.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					Load_GameActionPerformed(e);
					Load_GameActionPerformed(e);
				}
			});

			//---- Start_Game ----
			Start_Game.setText("Start New Game");
			Start_Game.setForeground(Color.blue);
			Start_Game.setFont(new Font("Tahoma", Font.PLAIN, 24));
			Start_Game.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					Start_GameActionPerformed(e);
				}
			});

			GroupLayout panel1Layout = new GroupLayout(panel1);
			panel1.setLayout(panel1Layout);
			panel1Layout.setHorizontalGroup(
				panel1Layout.createParallelGroup()
					.addGroup(panel1Layout.createSequentialGroup()
						.addContainerGap(47, Short.MAX_VALUE)
						.addGroup(panel1Layout.createParallelGroup()
							.addComponent(Load_Game, GroupLayout.PREFERRED_SIZE, 244, GroupLayout.PREFERRED_SIZE)
							.addComponent(Start_Game, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
						.addContainerGap(54, Short.MAX_VALUE))
			);
			panel1Layout.setVerticalGroup(
				panel1Layout.createParallelGroup()
					.addGroup(panel1Layout.createSequentialGroup()
						.addContainerGap()
						.addComponent(Load_Game, GroupLayout.PREFERRED_SIZE, 49, GroupLayout.PREFERRED_SIZE)
						.addGap(35, 35, 35)
						.addComponent(Start_Game, GroupLayout.PREFERRED_SIZE, 52, GroupLayout.PREFERRED_SIZE)
						.addContainerGap(23, Short.MAX_VALUE))
			);
		}
		contentPane.add(panel1);
		panel1.setBounds(0, 0, 345, 170);

		{ // compute preferred size
			Dimension preferredSize = new Dimension();
			for(int i = 0; i < contentPane.getComponentCount(); i++) {
				Rectangle bounds = contentPane.getComponent(i).getBounds();
				preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
				preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
			}
			Insets insets = contentPane.getInsets();
			preferredSize.width += insets.right;
			preferredSize.height += insets.bottom;
			contentPane.setMinimumSize(preferredSize);
			contentPane.setPreferredSize(preferredSize);
		}
		pack();
		setLocationRelativeTo(getOwner());
		//  - End of component initialization 
	}

	//  - Variables declaration - DO NOT MODIFY 
	
	private JPanel panel1;
	private JButton Load_Game;
	private JButton Start_Game;
	// - End of variables declaration  
	public void run()
	{
		Build1 Form = new Build1();		// create Object from the Player Class the will display the form
		Form.setVisible(true); 	// make it visible
	}
	
	
	public static void main(String[] args) 
	{
		/*Players player1 = new Players();
		player1.Write_XML();*/
		(new Thread(new Build1())).start();
		
		//System.out.println("Wrote to file");
		
	}
}

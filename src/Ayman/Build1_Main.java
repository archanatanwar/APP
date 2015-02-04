
package Ayman;
import java.awt.*;
import java.awt.event.*;

import javax.swing.*;




/**
 * @author Ayman Hasaneen
 */
public class Build1_Main extends JFrame implements Runnable {
	public Build1_Main() {
		initComponents();
	}

	private void Load_GameActionPerformed(ActionEvent e) {
		// TODO add your code here
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
		//  Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
		
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
		// End of component initialization  //GEN-END:initComponents
	}

	//  Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
	
	private JPanel panel1;
	private JButton Load_Game;
	private JButton Start_Game;
	// End of variables declaration  //GEN-END:variables
	public void run()
	{
		Build1_Main Form = new Build1_Main();		// create Object from the Player Class the will display the form
		Form.setVisible(true); 	// make it visible
	}
	
	
	public static void main(String[] args) 
	{
		Players player1 = new Players();
		player1.write_players_info();
		(new Thread(new Build1_Main())).start();
		
		//System.out.println("Wrote to file");
		
	}
}

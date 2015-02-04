import java.awt.*;
import javax.swing.*;
import javax.swing.GroupLayout;
import javax.swing.table.*;




/**
 * @author Ayman Hasaneen
 */
public class SavedGame extends JFrame {
	public SavedGame() {
		initComponents();
	}

	private void initComponents() {
		//  Component initialization - 
		
		panel1 = new JPanel();
		scrollPane1 = new JScrollPane();
		Loaded_Players_Info = new JTable();
		scrollPane2 = new JScrollPane();
		Loaded_Region_Info = new JTable();

		//======== this ========
		setTitle("Loaded Game (Saved Game)");
		Container contentPane = getContentPane();

		//======== panel1 ========
		{

			
			//======== scrollPane1 ========
			{

				//---- Loaded_Players_Info ----
				Loaded_Players_Info.setModel(new DefaultTableModel(
					new Object[][] {
						{null, null, null, null, null, null, null},
						{null, null, null, null, null, null, null},
						{null, null, null, null, null, null, null},
						{null, null, null, null, null, null, null},
						{null, null, null, null, null, null, null},
					},
					new String[] {
						"Players Turn", "Color", "Personality", "Number Of Minions", "Number Of Buildings", "Money", "Card Numbers"
					}
				));
				Loaded_Players_Info.setBackground(new Color(255, 255, 204));
				Loaded_Players_Info.setForeground(new Color(0, 102, 102));
				Loaded_Players_Info.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 11));
				scrollPane1.setViewportView(Loaded_Players_Info);
			}

			//======== scrollPane2 ========
			{

				//---- Loaded_Region_Info ----
				Loaded_Region_Info.setModel(new DefaultTableModel(
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
						{"Isle Of Gods", null, null, null, null, null},
						{"Seven Sleepers", null, null, null, null, null},
						{"Nap Hill", null, null, null, null, null},
					},
					new String[] {
						"Region", "No. Of Minions", "No. Of Buildings", "Trouble Markers", "Demons", "Trolls"
					}
				));
				Loaded_Region_Info.setBackground(new Color(255, 255, 204));
				Loaded_Region_Info.setForeground(new Color(0, 102, 102));
				Loaded_Region_Info.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 12));
				scrollPane2.setViewportView(Loaded_Region_Info);
			}

			GroupLayout panel1Layout = new GroupLayout(panel1);
			panel1.setLayout(panel1Layout);
			panel1Layout.setHorizontalGroup(
				panel1Layout.createParallelGroup()
					.addGroup(panel1Layout.createSequentialGroup()
						.addContainerGap()
						.addGroup(panel1Layout.createParallelGroup()
							.addGroup(panel1Layout.createSequentialGroup()
								.addGap(0, 0, Short.MAX_VALUE)
								.addComponent(scrollPane2, GroupLayout.PREFERRED_SIZE, 913, GroupLayout.PREFERRED_SIZE))
							.addComponent(scrollPane1, GroupLayout.Alignment.TRAILING))
						.addContainerGap())
			);
			panel1Layout.setVerticalGroup(
				panel1Layout.createParallelGroup()
					.addGroup(panel1Layout.createSequentialGroup()
						.addGap(21, 21, 21)
						.addComponent(scrollPane1, GroupLayout.PREFERRED_SIZE, 103, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 29, Short.MAX_VALUE)
						.addComponent(scrollPane2, GroupLayout.PREFERRED_SIZE, 232, GroupLayout.PREFERRED_SIZE)
						.addGap(31, 31, 31))
			);
		}

		GroupLayout contentPaneLayout = new GroupLayout(contentPane);
		contentPane.setLayout(contentPaneLayout);
		contentPaneLayout.setHorizontalGroup(
			contentPaneLayout.createParallelGroup()
				.addGroup(contentPaneLayout.createSequentialGroup()
					.addComponent(panel1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(0, 0, Short.MAX_VALUE))
		);
		contentPaneLayout.setVerticalGroup(
			contentPaneLayout.createParallelGroup()
				.addGroup(contentPaneLayout.createSequentialGroup()
					.addComponent(panel1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(0, 0, Short.MAX_VALUE))
		);
		pack();
		setLocationRelativeTo(getOwner());
		// End of component initialization 
	}

	//  Variables declaration 
	
	private JPanel panel1;
	private JScrollPane scrollPane1;
			JTable Loaded_Players_Info;
	private JScrollPane scrollPane2;
	private JTable Loaded_Region_Info;
	//  End of variables declaration  
}

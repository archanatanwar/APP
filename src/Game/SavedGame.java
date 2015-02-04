package Game;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;

import javax.swing.GroupLayout;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.LayoutStyle;
import javax.swing.table.DefaultTableModel;

/**
 * <h1>Loads GUI for resume</h1>
 * <p>
 * Class is used to load GUI<br>
 * when user wants to load a saved game.
 * </p>
 */
public class SavedGame extends JFrame {

	// Variables declaration
	private JPanel panel1;
	private JScrollPane scrollPane1;
	public JTable Loaded_Players_Info; // table to load info of players
	private JScrollPane scrollPane2;
	public JTable Loaded_Region_Info; // table to load info of regions
	public JTable Card_Info;
	public JScrollPane scrollPane3;

	// End of variables declaration

	public SavedGame() {
		// calls method that creates GUI
		initComponents();
	}

	/**
	 * Function creates all the components to be displayed in the panel
	 */
	private void initComponents() {
		// Component initialization -
		panel1 = new JPanel();
		scrollPane1 = new JScrollPane();
		Loaded_Players_Info = new JTable();
		scrollPane2 = new JScrollPane();
		Loaded_Region_Info = new JTable();
		Card_Info = new JTable();
		scrollPane3 = new JScrollPane();

		// ======== this ========
		setTitle("Loaded Game (Saved Game)");
		Container contentPane = getContentPane();

		// ======== panel1 ========
		{
			// ======== scrollPane1 ========
			{
				// ---- Loaded_Players_Info ----

				Loaded_Players_Info
						.setModel(new DefaultTableModel(
								new Object[][] {
										{ null, null, null, null, null, null,
												null },
										{ null, null, null, null, null, null,
												null },
										{ null, null, null, null, null, null,
												null },
										{ null, null, null, null, null, null,
												null },
										{ null, null, null, null, null, null,
												null }, }, new String[] {
										"Players Turn", "Color", "Personality",
										"Number Of Minions",
										"Number Of Buildings", "Money",
										"Card Numbers" }));
				Loaded_Players_Info.setBackground(new Color(255, 255, 204));
				Loaded_Players_Info.setForeground(new Color(0, 102, 102));
				Loaded_Players_Info.setFont(new Font("Tahoma", Font.BOLD
						| Font.ITALIC, 11));
				scrollPane1.setViewportView(Loaded_Players_Info);
			}

			// ======== scrollPane2 ========
			{

				// ---- Loaded_Region_Info ----
				Loaded_Region_Info
						.setModel(new DefaultTableModel(
								new Object[][] {
										{ "Dolly Sisters", null, null, null,
												null, null },
										{ "Unreal Estate", null, null, null,
												null, null },
										{ "Dragon's Landing", null, null, null,
												null, null },
										{ "Small Gods", null, null, null, null,
												null },
										{ "The Scours", null, null, null, null,
												null },
										{ "The Hippo", null, null, null, null,
												null },
										{ "The Shades", null, null, null, null,
												null },
										{ "Dimwell", null, null, null, null,
												null },
										{ "Longwall", null, null, null, null,
												null },
										{ "Isle Of Gods", null, null, null,
												null, null },
										{ "Seven Sleepers", null, null, null,
												null, null },
										{ "Nap Hill", null, null, null, null,
												null }, }, new String[] {
										"Region", "No. Of Minions",
										"No. Of Buildings", "Trouble Markers",
										"Demons", "Trolls" }));
				Loaded_Region_Info.setBackground(new Color(255, 255, 204));
				Loaded_Region_Info.setForeground(new Color(0, 102, 102));
				Loaded_Region_Info.setFont(new Font("Tahoma", Font.BOLD
						| Font.ITALIC, 12));
				scrollPane2.setViewportView(Loaded_Region_Info);
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

			GroupLayout panel1Layout = new GroupLayout(panel1);
			panel1.setLayout(panel1Layout);
			panel1Layout
					.setHorizontalGroup(panel1Layout
							.createParallelGroup()
							.addGroup(
									panel1Layout
											.createSequentialGroup()
											.addContainerGap()
											.addGroup(
													panel1Layout
															.createParallelGroup()
															.addGroup(
																	panel1Layout
																			.createSequentialGroup()
																			.addGap(0,
																					0,
																					Short.MAX_VALUE)
																			.addComponent(
																					scrollPane2,
																					GroupLayout.PREFERRED_SIZE,
																					913,
																					GroupLayout.PREFERRED_SIZE))																			
															.addComponent(
																	scrollPane1,
																	GroupLayout.Alignment.TRAILING)
															.addComponent(
																			scrollPane3,
																			GroupLayout.Alignment.TRAILING))
											.addContainerGap()));
			panel1Layout
					.setVerticalGroup(panel1Layout
							.createParallelGroup()
							.addGroup(
									panel1Layout
											.createSequentialGroup()
											.addGap(21, 21, 21)
											.addComponent(scrollPane1,
													GroupLayout.PREFERRED_SIZE,
													103,
													GroupLayout.PREFERRED_SIZE)
											.addPreferredGap(
													LayoutStyle.ComponentPlacement.RELATED,
													29, Short.MAX_VALUE)
											.addComponent(scrollPane2,
													GroupLayout.PREFERRED_SIZE,
													232,
													GroupLayout.PREFERRED_SIZE)
											.addPreferredGap(
													LayoutStyle.ComponentPlacement.RELATED,
													29, Short.MAX_VALUE)
											.addComponent(scrollPane3,
													GroupLayout.PREFERRED_SIZE,
													150,
													GroupLayout.PREFERRED_SIZE)
											.addGap(31, 31, 31)));
		}

		GroupLayout contentPaneLayout = new GroupLayout(contentPane);
		contentPane.setLayout(contentPaneLayout);
		contentPaneLayout.setHorizontalGroup(contentPaneLayout
				.createParallelGroup().addGroup(
						contentPaneLayout
								.createSequentialGroup()
								.addComponent(panel1,
										GroupLayout.PREFERRED_SIZE,
										GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE)
								.addGap(0, 0, Short.MAX_VALUE)));
		contentPaneLayout.setVerticalGroup(contentPaneLayout
				.createParallelGroup().addGroup(
						contentPaneLayout
								.createSequentialGroup()
								.addComponent(panel1,
										GroupLayout.PREFERRED_SIZE,
										GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE)
								.addGap(0, 0, Short.MAX_VALUE)));
		pack();
		setLocationRelativeTo(getOwner());
		// End of component initialization
	}
}

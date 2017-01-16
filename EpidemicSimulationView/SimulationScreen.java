package EpidemicSimulationView;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.border.AbstractBorder;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import EpidemicSimulationController.SimulationController;
import EpidemicSimulationController.StringParser;
import EpidemicSimulationModel.Country;
import EpidemicSimulationModel.SimulationArguments;
import EpidemicSimulationModel.World;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class SimulationScreen extends JPanel {
	private Color titleColor = Color.WHITE;
	private SimulationController simController;
	public CountryButton[][] buttons;
	public JLabel countryLabel;
	public JPanel countryInfoPanel;
	public JLabel worldTotalPopulation;
	public JLabel worldHealthyPopulation;
	public JLabel worldInfectedPopulation;
	public JLabel worldSickPopulation;
	public JLabel worldDeadPopulation;
	public JLabel worldDoctorPopulation;
	public JLabel worldImmunePopulation;
	public JLabel countryTotalPopulation;
	public JLabel countryHealthyPopulation;
	public JLabel countryImmunePopulation;
	public JLabel countryInfectedPopulation;
	public JLabel countrySickPopulation;
	public JLabel countryDeadPopulation;
	public JLabel countryDoctorPopulation;
	public JLabel dayLabel;
	public JButton btnNext;
	public World world = World.getInstance();
	public int mapSize;

	public SimulationScreen(SimulationController simController) {
		this.simController = simController;
		System.out.println("SimulationScreen Constructor simController: " + simController);
	}

	public void start(World world) {
		this.world = world;
		this.mapSize = world.getMapSize();
		buttons = new CountryButton[mapSize][mapSize];
		handleEverything();
	}

	private void handleEverything() {
		this.setLayout(null);
		
		// font, color and borders for info panel contents
		Font font = new Font("Comic Sans MS", Font.BOLD, 17);
		Color color = new Color(250, 250, 113);
		AbstractBorder nameBorder = new BubbleBorder(Color.BLACK, 2, 16, 0);
		Border borderLine = BorderFactory.createLineBorder(new Color(250, 217, 228), 3);
		TitledBorder dayLabelBorder = BorderFactory.createTitledBorder(borderLine, " DAY ");
		TitledBorder totalPopLabelBorder = BorderFactory.createTitledBorder(borderLine, " Total Pop. ");
		TitledBorder healthyLabelBorder = BorderFactory.createTitledBorder(borderLine, " Healthy ");
		TitledBorder immuneLabelBorder = BorderFactory.createTitledBorder(borderLine, " Immune ");
		TitledBorder infectedLabelBorder = BorderFactory.createTitledBorder(borderLine, " Infected ");
		TitledBorder sickLabelBorder = BorderFactory.createTitledBorder(borderLine, " Sick ");
		TitledBorder deadLabelBorder = BorderFactory.createTitledBorder(borderLine, " Dead ");
		TitledBorder doctorLabelBorder = BorderFactory.createTitledBorder(borderLine, " Doctor ");
		
		dayLabelBorder.setTitleColor(titleColor);
		totalPopLabelBorder.setTitleColor(titleColor);
		healthyLabelBorder.setTitleColor(titleColor);
		immuneLabelBorder.setTitleColor(titleColor);
		infectedLabelBorder.setTitleColor(titleColor);
		sickLabelBorder.setTitleColor(titleColor);
		deadLabelBorder.setTitleColor(titleColor);
		doctorLabelBorder.setTitleColor(titleColor);

		// panel that contains buttons
		JPanel countryPanel = new JPanel();
		countryPanel.setLayout(new GridLayout(mapSize, mapSize));
		countryPanel.setLocation(0, 0);
		countryPanel.setSize(650, 450);

		// panel that contains info
		JPanel infoPanel = new JPanel();
		infoPanel.setLocation(0, 450);
		infoPanel.setSize(650, 200);
		infoPanel.setBackground(new Color(77, 0, 0));
		infoPanel.setLayout(new GridLayout(3, 0));

		// WORLD SECTION
		JPanel worldInfoPanel = new JPanel();
		worldInfoPanel.setBackground(new Color(77, 0, 0));

		JLabel worldLabel = new JLabel(" WORLD ");
		worldLabel.setForeground(Color.WHITE);
		worldLabel.setBorder(nameBorder);

		this.worldTotalPopulation = createPanel(font, color, totalPopLabelBorder);
		this.worldHealthyPopulation = createPanel(font, color, healthyLabelBorder);
		this.worldImmunePopulation = createPanel(font, color, immuneLabelBorder);
		this.worldInfectedPopulation = createPanel(font, color, infectedLabelBorder);
		this.worldSickPopulation = createPanel(font, color, sickLabelBorder);
		this.worldDeadPopulation = createPanel(font, color, deadLabelBorder);
		this.worldDoctorPopulation = createPanel(font, color, doctorLabelBorder);
		
		worldInfoPanel.add(worldLabel);
		worldInfoPanel.add(this.worldTotalPopulation);
		worldInfoPanel.add(this.worldHealthyPopulation);
		worldInfoPanel.add(this.worldImmunePopulation);
		worldInfoPanel.add(this.worldInfectedPopulation);
		worldInfoPanel.add(this.worldSickPopulation);
		worldInfoPanel.add(this.worldDeadPopulation);
		worldInfoPanel.add(this.worldDoctorPopulation);

		// COUNTRY SECTION
		countryInfoPanel = new JPanel();
		countryInfoPanel.setBackground(new Color(77, 0, 0));
		countryInfoPanel.setVisible(false);

		countryLabel = new JLabel("<COUNTRY>");
		countryLabel.setForeground(Color.WHITE);
		countryLabel.setBorder(nameBorder);
		
		this.countryTotalPopulation = createPanel(font, color, totalPopLabelBorder);
		this.countryHealthyPopulation = createPanel(font, color, healthyLabelBorder);
		this.countryImmunePopulation = createPanel(font, color, immuneLabelBorder);
		this.countryInfectedPopulation = createPanel(font, color, infectedLabelBorder);
		this.countrySickPopulation = createPanel(font, color, sickLabelBorder);
		this.countryDeadPopulation = createPanel(font, color, deadLabelBorder);
		this.countryDoctorPopulation = createPanel(font, color, doctorLabelBorder);

		countryInfoPanel.add(countryLabel);
		countryInfoPanel.add(this.countryTotalPopulation);
		countryInfoPanel.add(this.countryHealthyPopulation);
		countryInfoPanel.add(this.countryImmunePopulation);
		countryInfoPanel.add(this.countryInfectedPopulation);
		countryInfoPanel.add(this.countrySickPopulation);
		countryInfoPanel.add(this.countryDeadPopulation);
		countryInfoPanel.add(this.countryDoctorPopulation);

		//Day Label and Next Button
		JPanel btnAndDayPanel = new JPanel();
		btnAndDayPanel.setBackground(new Color(77, 0, 0));
		
		dayLabel = new JLabel(StringParser.parseNumber(world.days, 5));
		btnNext = new JButton("NEXT");
		btnNext.setBackground(new Color(77, 0, 0));
		btnNext.setForeground(new Color(247, 240, 170));
		btnNext.setFont(font);
		btnNext.addActionListener(new SimulationScreenListener(this, simController));
		
		createDayPanel(dayLabel, btnAndDayPanel, font, color, dayLabelBorder);
		btnAndDayPanel.add(btnNext);
		
		addCountryButtons(countryPanel, buttons, mapSize);
		addImagesToButtons(buttons);
		
		infoPanel.add(worldInfoPanel);
		infoPanel.add(countryInfoPanel);
		infoPanel.add(btnAndDayPanel);
		this.add(countryPanel);
		this.add(infoPanel);
		this.setVisible(true);
	}

	private void addImagesToButtons(JButton[][] buttons) {
		ImageIcon[] myIcons = new ImageIcon[55];
		for (int i = 0; i < mapSize; i++) {
			for (int j = 0; j < mapSize; j++) {
				myIcons[j] = new ImageIcon("Images/Map44.png");
				Image icon = myIcons[j].getImage();
				Image image = icon.getScaledInstance(650 / mapSize, 450 / mapSize, java.awt.Image.SCALE_SMOOTH);
				buttons[i][j].setIcon(new ImageIcon(image));
			}
		}
	}

	private void addCountryButtons(JPanel countryPanel, JButton[][] buttons, int mapSize) {
		for (int row = 0; row < mapSize; row++)
			for (int col = 0; col < mapSize; col++) {
				buttons[row][col] = new CountryButton(world.getCountryAt(row, col));
				countryPanel.add(buttons[row][col]);
				buttons[row][col].addActionListener(new SimulationScreenListener(this, simController));
			}
	}

	private JLabel createPanel(Font font, Color color, Border border) {
		JLabel label = new JLabel("  ");
		label.setFont(font);
		label.setForeground(color);
		label.setBorder(border);
		return label;
	}

	private void createDayPanel(JLabel dayLabel, JPanel panel, Font font, Color color, Border border) {
		dayLabel.setFont(font);
		dayLabel.setForeground(color);
		dayLabel.setBorder(border);
		panel.add(dayLabel);
	}
}

/*
 * Listener was split from the SimulationController , this allows us to create
 * different SimulationScreens without requiring controller for each one of
 * them. Will come in handy if we want to display simulationScreen's of old days
 * without stopping the simulation.
 */
class SimulationScreenListener implements ActionListener {
	SimulationScreen simScreen;
	SimulationController simController;

	public SimulationScreenListener(SimulationScreen simScreen, SimulationController simController) {
		this.simScreen = simScreen;
		this.simController = simController;

	}

	public void actionPerformed(ActionEvent e) {
		JLabel countryName = simScreen.countryLabel;
		SimulationArguments args = SimulationArguments.getInstance();
		simScreen.countryInfoPanel.setVisible(true);
		
		if (e.getSource() instanceof CountryButton) {
			CountryButton btn = (CountryButton) e.getSource();
			Country country = btn.country;
			countryName.setText(country.getCountryName());
			setCountryLabels(country);
			setWorldLabels(args);
			
		} else if (e.getSource() == simScreen.btnNext) {
			incrementDay();
			simController.simulateWorld();
			setWorldLabels(args);
		}
	}

	private void incrementDay() {
		simScreen.world.days++;
		simScreen.dayLabel.setText(StringParser.parseNumber(simScreen.world.days, 5));
	}

	private void setCountryLabels(Country country) {
		simScreen.countryTotalPopulation.setText(StringParser.parseNumber(country.getTotalPopulation(), 9));
		simScreen.countryHealthyPopulation.setText(StringParser.parseNumber(country.getHealthyPopulation(), 6));
		simScreen.countryImmunePopulation.setText(StringParser.parseNumber(country.getImmunePopulation(), 7));
		simScreen.countryInfectedPopulation.setText(StringParser.parseNumber(country.getInfectedPopulation(), 7));
		simScreen.countrySickPopulation.setText(StringParser.parseNumber(country.getSickPopulation(), 5));
		simScreen.countryDeadPopulation.setText(StringParser.parseNumber(country.getDeadPopulation(), 5));
		simScreen.countryDoctorPopulation.setText(StringParser.parseNumber(country.getDoctorPopulation(), 7));
	}
	
	private void setWorldLabels(SimulationArguments args){
		int totalPopulation = args.population;
		int healthyPopulation = simScreen.world.getTotalHealthyCount();
		int immunePopulation = simScreen.world.getTotalImmunePopulation();
		int infectedPopulation = simScreen.world.getTotalInfectedCount();
		int sickPopulation = simScreen.world.getTotalSickCount();
		int deadPopulation = simScreen.world.getTotalDeadCount();
		int doctorPopulation = simScreen.world.getTotalDoctorPopulation();
		
		simScreen.worldTotalPopulation.setText(StringParser.parseNumber(totalPopulation ,7));
		simScreen.worldHealthyPopulation.setText(StringParser.parseNumber(healthyPopulation, 6));
		simScreen.worldImmunePopulation.setText(StringParser.parseNumber(immunePopulation, 7));
		simScreen.worldInfectedPopulation.setText(StringParser.parseNumber(infectedPopulation ,7));
		simScreen.worldSickPopulation.setText(StringParser.parseNumber(sickPopulation ,4));
		simScreen.worldDeadPopulation.setText(StringParser.parseNumber(deadPopulation ,5));
		simScreen.worldDoctorPopulation.setText(StringParser.parseNumber(doctorPopulation ,6));
	}
}
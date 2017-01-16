package EpidemicSimulationView;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.text.NumberFormatter;

import EpidemicSimulationController.MenuController;
import EpidemicSimulationModel.GameData;
import EpidemicSimulationModel.SimulationArguments;

public class OptionsMenuView extends MenuPanel {
	private int xSize, ySize;
	private MenuController controller;

	int xStart, yStart,xInterval,yInterval;

	JFormattedTextField populationTextField;
	JFormattedTextField doctorVaccineTextField;
	JSlider superPercentageSlider;
	JSlider infectedPercentageSlider;
	JSlider doctorPercentageSlider;
	JSlider airTravelPercentageSlider;
	private JPanel mapSizePanel;
	ArrayList<JButton> mapSizeButtons;
	private JButton backButton;

	public OptionsMenuView(int xSize, int ySize, MenuController controller) {
		this.controller = controller;
		this.setLayout(null);
		this.setSize(xSize, ySize);
		this.xSize = xSize;
		this.ySize = ySize;
		initializeLocationValues();

		initializeMapSizePanel();
		initializePopulationTextField();
		initializeSuperPercentageSlider();
		initializeInfectedPercentageSlider();
		initializeDoctorPercentageSlider();
		initializeDoctorVaccineTextField();
		initializeAirTravelPercentage();
		initializeBackButton();
		this.setBackground();
	}

	public void setMapSize(int mapSize) {
		SimulationArguments args = SimulationArguments.getInstance();
		args.mapSize = mapSize;
	}

	public void returnToHomePage() {
		controller.switchToMainMenu();
	}
	
	public void setSimulationArguments(int infectedPercentage, int population,int doctorPercentage,
			int superPercentage,int doctorVaccineCount,int airTravelPercentage){
		SimulationArguments args = SimulationArguments.getInstance();
		
		args.infectedPercentage = infectedPercentage;
		args.population = population;
		args.doctorPercentage = doctorPercentage;
		args.superHumanPercentage = superPercentage;
		args.doctorHealAmount = doctorVaccineCount;
		args.travelPlanePercentage = airTravelPercentage;
		
//		controller.setInfectedPercentage(infectedPercentage);
//		controller.setPopulation(population);
//		controller.setDoctorPercentage(doctorPercentage);
	}

	public void initializeLocationValues() {
		this.xStart = this.xSize / 15;
		this.yStart = this.ySize / 8;
		this.yInterval = this.ySize / 6;
		this.xInterval = this.xSize / 4;
	}
	
	private void initializePopulationTextField() {
		NumberFormatter formatter = new NumberFormatter();
		formatter.setAllowsInvalid(false);
		this.populationTextField = new JFormattedTextField(formatter);
		populationTextField.setLocation((int) (xStart + xInterval * 1.6), (int) (yStart + yInterval * 0.5));
		populationTextField.setSize(xSize / 3, ySize / 15);
		populationTextField.setFont(new Font("Market", Font.BOLD, xSize / 32));
		populationTextField.setBackground(new Color(140, 50, 78));
		populationTextField.setForeground(Color.YELLOW);
		this.add(populationTextField);
	}
	
	private void initializeSuperPercentageSlider(){
		superPercentageSlider = new JSlider(0, 100, GameData.DEFAULT_SUPERHUMAN_PERCENTAGE);
		superPercentageSlider.setLocation((int) (xStart + xInterval * 1.6), (int) (yStart + yInterval * 1.3));
		superPercentageSlider.setSize(xSize / 3, ySize / 35);
		superPercentageSlider.setVisible(true);
		superPercentageSlider.addChangeListener(new SliderListener(this));
		superPercentageSlider.setBackground(new Color(240, 128, 128, 0));
		this.add(superPercentageSlider);
	}

	private void initializeInfectedPercentageSlider() {
		infectedPercentageSlider = new JSlider(0, 100, GameData.DEFAULT_INFECTED_PERCENTAGE);
		infectedPercentageSlider.setLocation((int) (xStart + xInterval * 1.6), (int) (yStart + yInterval * 1.9));
		infectedPercentageSlider.setSize(xSize / 3, ySize / 35);
		infectedPercentageSlider.setVisible(true);
		infectedPercentageSlider.addChangeListener(new SliderListener(this));
		infectedPercentageSlider.setBackground(new Color(240, 128, 128, 0));
		this.add(infectedPercentageSlider);
	}
	
	private void initializeDoctorPercentageSlider(){
		doctorPercentageSlider = new JSlider(0, 100, GameData.DEFAULT_DOCTOR_PERCENTAGE);
		doctorPercentageSlider.setLocation((int) (xStart + xInterval * 1.6), (int) (yStart + yInterval * 2.5));
		doctorPercentageSlider.setSize(xSize / 3, ySize / 35);
		doctorPercentageSlider.setVisible(true);
		doctorPercentageSlider.addChangeListener(new SliderListener(this));
		doctorPercentageSlider.setBackground(new Color(240, 128, 128, 0));
		this.add(doctorPercentageSlider);
	}
	
	private void initializeDoctorVaccineTextField() {
		NumberFormatter formatter = new NumberFormatter();
		formatter.setAllowsInvalid(false);
		this.doctorVaccineTextField = new JFormattedTextField(formatter);
		doctorVaccineTextField.setLocation((int) (xStart + xInterval * 1.6), (int) (yStart + yInterval * 3));
		doctorVaccineTextField.setSize(xSize / 3, ySize / 25);
		doctorVaccineTextField.setFont(new Font("Market", Font.BOLD, xSize / 32));
		doctorVaccineTextField.setBackground(new Color(140, 50, 78));
		doctorVaccineTextField.setForeground(Color.YELLOW);
		this.add(doctorVaccineTextField);
	}
	
	private void initializeAirTravelPercentage(){
		airTravelPercentageSlider = new JSlider(0, 100, GameData.DEFAULT_TRAVEL_PLANE_PERCENTAGE);
		airTravelPercentageSlider.setLocation((int) (xStart + xInterval * 1.6), (int) (yStart + yInterval * 3.6));
		airTravelPercentageSlider.setSize(xSize / 3, ySize / 35);
		airTravelPercentageSlider.setVisible(true);
		airTravelPercentageSlider.addChangeListener(new SliderListener(this));
		airTravelPercentageSlider.setBackground(new Color(240, 128, 128, 0));
		this.add(airTravelPercentageSlider);
	}

	private void initializeBackButton() {
		backButton = new MenuButton((int) (xInterval / 15), (int) (ySize - ySize / 6.7),"BACK",new BackButtonListener(this));
		this.add(backButton);
	}

	private void initializeMapSizePanel() {
		this.mapSizePanel = new JPanel();
		this.mapSizePanel.setSize(xSize / 2, ySize / 5);
		this.mapSizePanel.setLayout(new FlowLayout());
		this.mapSizePanel.setLocation((int) (xStart + xInterval * 1.3), (int) (yStart - yInterval * 0.2));
		this.mapSizePanel.setOpaque(false);
		this.mapSizeButtons = new ArrayList<JButton>();

		MapSizeButtonListener listener = new MapSizeButtonListener(this);

		for (int i = 0; i < GameData.mapSizes.size(); i++) {
			JButton button = new JButton();
			button.setForeground(Color.CYAN);
			button.setOpaque(false);
			button.setFont(new Font("Comic Sans MS", Font.PLAIN, 15));
			button.setText(GameData.mapSizes.get(i) + "");
			button.addActionListener(listener);
			button.setSize(50, 50);
			button.setLocation(100, 100);
			button.setBackground(Color.WHITE);
			this.mapSizePanel.add(button);
			this.mapSizeButtons.add(button);
		}
		mapSizeButtons.get(0).setForeground(Color.YELLOW);

		this.mapSizePanel.setVisible(true);
		this.add(mapSizePanel);
	}
	
	private Color getSituationColor(){
		if(this.superPercentageSlider!=null){
			if(this.superPercentageSlider.getValue() + this.infectedPercentageSlider.getValue() >=100){
				return Color.RED;
			}
		}
		return Color.cyan;
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.setColor(Color.CYAN);
		g.setFont(new Font("Market", Font.BOLD, xSize / 28));

		g.drawString("Map Size :", xStart, yStart);
		g.drawString("Population :", xStart, (int) (yStart + yInterval*0.75));

		g.drawString("Doctor Percentage :", xStart, (int) (yStart + yInterval * 2.62));
		g.drawString("%" + this.doctorPercentageSlider.getValue(),(int)(xStart + xInterval * 3),(int)(yStart + yInterval * 2.62));
		
		g.setColor(getSituationColor());
		
		g.drawString("Percentage Infected :", xStart, (int) (yStart + yInterval * 2));
		g.drawString("%" + this.infectedPercentageSlider.getValue(),(int)(xStart + xInterval * 3),(int)(yStart + yInterval * 2));

		g.drawString("Super Percentage :", xStart, (int) (yStart + yInterval * 1.4));
		g.drawString("%" + this.superPercentageSlider.getValue(),(int)(xStart + xInterval * 3),(int)(yStart + yInterval * 1.4));
		
		g.setColor(Color.cyan);
		
		g.drawString("Doctor Heal Amount :", xStart, (int) (yStart + yInterval*3.2));
		
		g.drawString("Air Travel Percentage :", xStart, (int) (yStart + yInterval * 3.7));
		g.drawString("%" + this.airTravelPercentageSlider.getValue(),(int)(xStart + xInterval * 3),(int)(yStart + yInterval * 3.7));
	}
}

class BackButtonListener implements ActionListener {
	OptionsMenuView view;

	public BackButtonListener(OptionsMenuView view) {
		this.view = view;
	}

	public void actionPerformed(ActionEvent event) {
		int infectedPercentage = 0;
		int doctorPercentage = 0;
		int superPercentage = 0;
		int airTravelPercentage = 0;
		long population = 0;
		long doctorVaccineCount = 0;

		infectedPercentage = view.infectedPercentageSlider.getValue();
		doctorPercentage = view.doctorPercentageSlider.getValue();
		superPercentage = view.superPercentageSlider.getValue();
		airTravelPercentage = view.airTravelPercentageSlider.getValue();
		try {
			population = (long)view.populationTextField.getValue();
		}
		catch (Exception e){
			population = GameData.DEFAULT_POPULATION;
		}
		try {
			doctorVaccineCount =(long)view.doctorVaccineTextField.getValue();
		} catch (Exception e) {
			doctorVaccineCount = GameData.DEFAULT_DOCTOR_HEAL_AMOUNT;
		}
		
		if(superPercentage + infectedPercentage >= 100) return;
		this.view.setSimulationArguments(infectedPercentage, (int)population, doctorPercentage,superPercentage,
				(int)doctorVaccineCount,airTravelPercentage);
		this.view.returnToHomePage();
	}

}

class MapSizeButtonListener implements ActionListener {
	OptionsMenuView view;

	public MapSizeButtonListener(OptionsMenuView view) {
		this.view = view;
	}

	public void actionPerformed(ActionEvent e) {
		JButton button = (JButton) e.getSource();
		button.setForeground(Color.YELLOW);
		button.setFont(new Font("Comic Sans MS", Font.BOLD, 15));
		int mapSize = Integer.parseInt(button.getText());
		this.view.setMapSize(mapSize);

		
		for (int i = 0; i < view.mapSizeButtons.size(); i++) {
			JButton abutton = view.mapSizeButtons.get(i);
			if (abutton.equals(button) == false) {
				abutton.setForeground(Color.CYAN);
				abutton.setFont(new Font("Comic Sans MS", Font.PLAIN, 15));
			}
		}
	}
}

class SliderListener implements ChangeListener {
	OptionsMenuView view;

	public SliderListener(OptionsMenuView view) {
		this.view = view;
	}

	public void stateChanged(ChangeEvent arg0) {
		view.repaint();
	}
}

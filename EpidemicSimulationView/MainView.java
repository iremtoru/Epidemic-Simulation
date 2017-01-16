package EpidemicSimulationView;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import EpidemicSimulationController.MenuController;
import EpidemicSimulationController.SimulationController;
import EpidemicSimulationModel.GameData;
import java.awt.image.BufferedImage;

public class MainView {
	//MainView name is just a random name, you can change it atwill,had to be something different than
	//homepage since class no longer serves that porpuse but i didnt want to waste time
	//thinking of a better name.
	
	static final int BUTTON_WIDTH = 150;
	static final int BUTTON_HEIGHT = 45;
	static final int FRAME_WIDTH = 655;
	static final int FRAME_HEIGHT = 680;

	private MenuController controller;
	private SimulationController simController;
	
	private JPanel container;
	
	private MainMenu mainMenu;
	private OptionsMenuView settingsMenu;
	private InformationPanel infoMenu;
	private SimulationScreen simScreen;
	
	private CardLayout cardLayout;
	private JFrame frame;
	
	public MainView(SimulationController simController){
		this.simController = simController;
	}
	
	public SimulationScreen getSimulationScreen(){
		return this.simScreen;
	}
	
	public void setSimulationScreen(SimulationScreen simScreen){
		this.simScreen = simScreen;
	}

	public void initialize(MenuController controller) {
		this.controller = controller;
		
		setCardLayout();
		createFrame();
		createMainMenu();
		createSettingsMenu();
		createInfoMenu();
		createSimulationScreen();
		
		cardLayout.show(container, "mainMenu");
		frame.repaint();
	}

	public void setCardLayout() {
		cardLayout = new CardLayout();

		container = new JPanel();
		container.setSize(FRAME_WIDTH, FRAME_HEIGHT);
		container.setLocation(0, 0);
		container.setLayout(cardLayout);
	}
	
	private void createFrame() {
		frame = new JFrame("Epidemic Simulation");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setSize(FRAME_WIDTH, FRAME_HEIGHT);
		frame.setLayout(null);
		frame.setLocationRelativeTo(null);
		frame.setFocusable(true);
		frame.setVisible(true);
		
		ImageIcon icon = new ImageIcon("Images/icon1.png");
		frame.setIconImage(icon.getImage());
		frame.add(container);
	}

	private void createMainMenu() {
		mainMenu = new MainMenu(controller);
		container.add(mainMenu, "mainMenu");
	}

	private void createSettingsMenu() {
		settingsMenu = new OptionsMenuView(FRAME_WIDTH,FRAME_HEIGHT,controller);
		settingsMenu.setLayout(null);
		container.add(settingsMenu, "settingsMenu");
	}

	private void createInfoMenu() {
		infoMenu = new InformationPanel(controller);
		container.add(infoMenu, "infoMenu");
	}
	
	private void createSimulationScreen() {
		//this.simScreen = new SimulationScreen(simController);
		
		this.container.add(simScreen,"simulationScreen");
	}

	private static ImageIcon getScaledImage(ImageIcon srcImgIcon, int w, int h) {
		Image srcImg = srcImgIcon.getImage();
		BufferedImage resizedImg = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
		Graphics2D g2 = resizedImg.createGraphics();

		g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
		g2.drawImage(srcImg, 0, 0, w, h, null);
		g2.dispose();

		return new ImageIcon(resizedImg);
	}

	protected static RoundedPanel createRoundedPanel(int x, int y, int width, int height) {
		RoundedPanel rp = new RoundedPanel(0, 0);
		// rp.setBackground(new Color(153, 115, 0));
		rp.setBackground(Color.DARK_GRAY);
		rp.setSize(width, height);
		rp.setLocation(x, y);
		return rp;
	}

	public void switchToMainMenu() {
		cardLayout.show(container, "mainMenu");
	}

	public void switchToSettingsMenu() {
		cardLayout.show(container, "settingsMenu");
	}

	public void switchToInfoMenu() {
		cardLayout.show(container, "infoMenu");
	}
	
	public void switchToSimulationScreen(){
		cardLayout.show(container, "simulationScreen");
	}

}

abstract class ModularActionListener implements ActionListener {
	MenuController controller;

	public ModularActionListener(MenuController controller){
		this.controller = controller;
	}
	
	public abstract void actionPerformed(ActionEvent e);
	
}

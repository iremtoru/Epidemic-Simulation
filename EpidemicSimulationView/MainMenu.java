package EpidemicSimulationView;

import java.awt.event.ActionEvent;

import javax.swing.JPanel;

import EpidemicSimulationController.MenuController;

public class MainMenu extends MenuPanel{
	MenuController controller;
	
	public MainMenu(MenuController controller){
		this.controller = controller;
		this.setLayout(null);
		this.setBackground();
		addButtons();
		this.add(MainView.createRoundedPanel(220, 86, 500, 800));
	}
	
	private void addButtons(){
		this.add(new MenuButton(245, 101, "START", new ModularActionListener(controller){
			public void actionPerformed(ActionEvent arg0) {
				controller.startSimulation();
			}
		}));
		
		this.add(new MenuButton(245, 170, "SETTINGS", new ModularActionListener(controller){
			public void actionPerformed(ActionEvent e) {
				controller.switchToSettingsMenu();
			}
		}));
		
		this.add(new MenuButton(245, 241, "INFO", new ModularActionListener(controller){
			public void actionPerformed(ActionEvent e) {
				controller.switchToInfoMenu();
			}
		}));
		
		this.add(new MenuButton(245, 310, "EXIT", new ModularActionListener(controller){
			public void actionPerformed(ActionEvent e) {
				controller.endGame();
			}
		}));
	}
}

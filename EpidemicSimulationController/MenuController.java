package EpidemicSimulationController;

import EpidemicSimulationView.MainView;

public class MenuController {
	private MainView view;
	private SimulationController simController;

	public MenuController(MainView view, SimulationController simController) {
		this.view = view;
		this.simController = simController;
	}

	public void switchToSettingsMenu() {
		this.view.switchToSettingsMenu();
	}

	public void switchToInfoMenu() {
		this.view.switchToInfoMenu();
	}

	public void switchToSimulationScreen() {
		view.switchToSimulationScreen();
	}

	public void startSimulation() {
		view.switchToSimulationScreen();
		simController.initializeSimulation();
	}

	public void switchToMainMenu() {
		view.switchToMainMenu();
	}

	public void endGame() {
		System.exit(0);
	}
}

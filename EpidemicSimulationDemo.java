import EpidemicSimulationController.MenuController;
import EpidemicSimulationController.SimulationController;
import EpidemicSimulationModel.Country;
import EpidemicSimulationView.MainView;
import EpidemicSimulationView.SimulationScreen;

public class EpidemicSimulationDemo {
	public static void main(String[] args) {
		SimulationController simController = new SimulationController();
		SimulationScreen simScreen = new SimulationScreen(simController);
		simController.setSimulationScreenView(simScreen);
		
		MainView mainView = new MainView(simController);
		mainView.setSimulationScreen(simScreen);
		
		MenuController controller = new MenuController(mainView, simController);
		mainView.initialize(controller);
	}
}

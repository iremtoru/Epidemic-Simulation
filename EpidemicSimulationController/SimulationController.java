package EpidemicSimulationController;

import EpidemicSimulationModel.Country;
import EpidemicSimulationModel.SimulationArguments;
import EpidemicSimulationModel.World;
import EpidemicSimulationView.SimulationScreen;

public class SimulationController {
	private SimulationScreen view;
	private World world;

	public void setSimulationScreenView(SimulationScreen view) {
		this.view = view;
	}

	public void initializeSimulation() {
		SimulationArguments args = SimulationArguments.getInstance();
		initializeWorld(args);
		view.start(this.world);
	}

	public void initializeWorld(SimulationArguments args) {
		World.initializeWorld();
		this.world = World.getInstance();
		world.initializeCountries();
		world.initializeNeighbours();
	}

	public void simulateWorld() {
		for (int i = 0; i < world.getMapSize(); i++) {
			for (int j = 0; j < world.getMapSize(); j++) {
				simulateCountryAt(i, j);
			}
		}
	}

	private void simulateCountryAt(int x, int y) {
		// It simulates humans health conditions and
		// population knowledge whom in the countries
		Country country = world.getCountryAt(x, y);
		country.simulate();
	}
}

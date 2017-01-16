package EpidemicSimulationModel;

public class HealthyBehavior extends HumanBehavior{

	@Override
	public HumanBehavior simulateHuman(Country country) {
		return this;
	}

}

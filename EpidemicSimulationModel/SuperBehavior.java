package EpidemicSimulationModel;

public class SuperBehavior extends HumanBehavior {

	@Override
	public HumanBehavior simulateHuman(Country country) {
		return this;
	}

}

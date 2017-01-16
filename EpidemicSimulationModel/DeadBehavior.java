package EpidemicSimulationModel;

public class DeadBehavior extends HumanBehavior{

	@Override
	public HumanBehavior simulateHuman(Country country) {
		return this;
	}
}

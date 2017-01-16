package EpidemicSimulationModel;

public abstract class HumanBehavior {
	protected int day = 0;
	public abstract HumanBehavior simulateHuman(Country country);
}

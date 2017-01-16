package EpidemicSimulationModel;

public class ImmuneBehavior extends HumanBehavior {
	
	public ImmuneBehavior() {
		this.day = GameData.DAYS_TO_BECOME_IMMUNE;
	}
	@Override
	public HumanBehavior simulateHuman(Country country) {
		day++;
		if(day >= GameData.DAYS_TO_BECOME_HEALTHY) {
			country.infectedPopulation--;
			country.healthyPopulation++;
			return new HealthyBehavior();
		}
		return this;
	}
	
}

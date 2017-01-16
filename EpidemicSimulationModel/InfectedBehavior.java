package EpidemicSimulationModel;

public class InfectedBehavior extends HumanBehavior{
	
	public InfectedBehavior(){
		this.day = 1;
	}
	
	public HumanBehavior simulateHuman(Country country) {
		day ++;
		if(day >= GameData.DAYS_TO_BECOME_SICK){
			country.infectedPopulation--;
			country.sickPopulation++;
			return new SickBehavior();
		}
		else{
			return this;
		}
	}
}

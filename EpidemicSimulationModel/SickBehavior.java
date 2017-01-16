package EpidemicSimulationModel;

import java.util.Random;

public class SickBehavior extends HumanBehavior {
	public SickBehavior(){
		this.day = GameData.DAYS_TO_BECOME_SICK;
	}

	@Override
	public HumanBehavior simulateHuman(Country country) {
		day ++;
		if (day == GameData.DAYS_TO_BECOME_PROB_DEAD) {
			Random possibility = new Random();
			int chance = possibility.nextInt(100);
			if (chance < GameData.PROB_OF_BECOME_DEAD) {
				country.sickPopulation--;
				country.deadPopulation ++;
				return new DeadBehavior();
			}
		}
		if(day >= GameData.DAYS_TO_BECOME_IMMUNE){
			country.sickPopulation--;
			country.infectedPopulation++;
			return new ImmuneBehavior();
			
		}
		return this;
	}
}

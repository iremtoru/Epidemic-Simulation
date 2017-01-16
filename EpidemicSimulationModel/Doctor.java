package EpidemicSimulationModel;

import java.util.ArrayList;
import java.util.Random;

public class Doctor extends Human {
	private SimulationArguments args = SimulationArguments.getInstance();
	private int healAmount = args.doctorHealAmount;

	public Doctor(int healthCondition) {
		super(healthCondition);
		healAmount = args.doctorHealAmount;
	}
	
	public void simulateInfection(Country country) {
		for(int i = 0;i<healAmount;i++){
			healRandomHuman(country);
		}
		super.simulateInfection(country);
	}
	
	public void healRandomHuman(Country country){
		if(country.healthyPopulation <= 0){
			return;
		}
		Human h = pickRandomHealthyHuman(country);
		if(h == null){
			return;
		}
		h.behavior = new SuperBehavior();
		country.healthyPopulation--;
		country.superPopulation++;
	}
	
	public Human pickRandomHealthyHuman(Country country){
		ArrayList<Human> humans = country.getHumans();
		
		for(int i = 0;i<humans.size();i++){
			if(humans.get(i).behavior instanceof HealthyBehavior){
				return humans.get(i);
			}
		}
		
		return null;
	}
}
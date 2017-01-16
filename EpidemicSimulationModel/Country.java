package EpidemicSimulationModel;

import java.util.ArrayList;
import java.util.Random;

import EpidemicSimulationController.CountryNameGenerator;

public class Country {
	private int totalPopulation;
	private ArrayList<Human> humans;
	private ArrayList<Human> movedHumans;
	private ArrayList<Human> leftHumans;
	private ArrayList<Country> neighbours;
	private CountryNameGenerator cnt = new CountryNameGenerator(6);
	private Random rand = new Random();
	private String countryName;
	public int healthyPopulation, deadPopulation, infectedPopulation , sickPopulation
	,superPopulation = 0;
	private int infectedPercentage;
	
	SimulationArguments args = SimulationArguments.getInstance();

	public Country(int population, int infectedPercentage) {
		this.countryName = cnt.nextString();
		this.totalPopulation = population;
		this.infectedPercentage = infectedPercentage;
		humans = new ArrayList<Human>(population);
		movedHumans = new ArrayList<Human>();
		leftHumans = new ArrayList<Human>();
		infectedPopulation = calculateInfectedPopulation(totalPopulation);
		healthyPopulation = calculateHealthyPopulation(totalPopulation);
		addHumans();
		totalPopulation = humans.size();
	}

	public ArrayList<Human> getHumans() {
		return this.humans;
	}

	public String getCountryName() {
		return countryName;
	}

	public int getHealthyPopulation() {
		return this.healthyPopulation;
	}

	public int getInfectedPopulation() {
		return this.infectedPopulation;
	}
	
	public int getSickPopulation(){
		return this.sickPopulation;
	}

	public int getDeadPopulation() {
		return this.deadPopulation;
	}
	
	public ArrayList<Country> getNeighbours(){
		return neighbours;
	}

	protected void addHumans() {
		for(int i = 0; i<calculateHealthyPopulation(calculateOrdinaryPopulation());i++){
			humans.add(new OrdinaryHuman(Human.HEALTHY));
		}
		for(int i = 0; i<calculateInfectedPopulation(calculateOrdinaryPopulation());i++){
			humans.add(new OrdinaryHuman(Human.INFECTED));
		}
		for(int i = 0; i<calculateSuperPopulation(calculateOrdinaryPopulation());i++){
			humans.add(new OrdinaryHuman(Human.SUPER));
		}
		for(int i = 0; i<calculateHealthyPopulation(calculateDoctorPopulation());i++){
			humans.add(new Doctor(Human.HEALTHY));
		}
		for(int i = 0; i<calculateInfectedPopulation(calculateDoctorPopulation());i++){
			humans.add(new Doctor(Human.INFECTED));
		}
		for(int i = 0; i<calculateSuperPopulation(calculateDoctorPopulation());i++){
			humans.add(new Doctor(Human.SUPER));
		}
		
		
	}
	
	public int calculateInfectedPopulation(int population){
//		System.out.println(population+"infected= "+population * infectedPercentage / 100);
		return population * infectedPercentage / 100;
	}
	
	public int calculateHealthyPopulation(int population){
		return population - infectedPopulation;
	}
	
	public int calculateSuperPopulation(int population){
		return population*args.superHumanPercentage/100;
	}
	
	public int calculateDoctorPopulation(){
		int doctorPopulation = totalPopulation * args.doctorPercentage / 100;
		return doctorPopulation;
	}
	
	public int calculateOrdinaryPopulation(){
		int ordinaryPopulation = totalPopulation - calculateDoctorPopulation();
		return ordinaryPopulation;
	}

	public int getTotalPopulation() {
		return totalPopulation;
	}

	public void setNeigbours(ArrayList<Country> neighbours) {
		this.neighbours = neighbours;
	}

	@Override
	public String toString() {
		return "Country Name: " + countryName + ", Total population: " + totalPopulation + ", healthyPopulation: "
				+ healthyPopulation + ", infectedPopulation: " + infectedPopulation + ", deadPopulation: " + deadPopulation;
	}

	public void simulate() {
		for(Human h:humans){
			h.simulateInfection(this);
		}
		for(Human h:humans){
			h.simulateMovement(this);
		}
		
		
		humans.removeAll(leftHumans);
		humans.addAll(movedHumans);	
		
		leftHumans.clear();
		movedHumans.clear();
		
		this.totalPopulation = humans.size();
	}

	public void transferPerson(Human human, Country destinationCountry) {
		this.leftHumans.add(human);
		destinationCountry.movedHumans.add(human);
		
		if(human.behavior instanceof InfectedBehavior
				||human.behavior instanceof ImmuneBehavior){
			
			destinationCountry.infectedPopulation++;
			this.infectedPopulation--;
		}
		else if(human.behavior instanceof HealthyBehavior){
			this.healthyPopulation--;
			
			if(destinationCountry.sickPopulation+destinationCountry.infectedPopulation >= 0){
				int randomPercentage = rand.nextInt(100);
				
				if(randomPercentage < GameData.INFECTION_TRANSMISSIBILITY_RATE){
					human.behavior = new InfectedBehavior();
					destinationCountry.infectedPopulation++;
				}
				else{
					destinationCountry.healthyPopulation++;
				}
			}
		}
		else if(human.behavior instanceof SickBehavior){
			destinationCountry.sickPopulation ++;
			this.sickPopulation --;
		}
		else if(human.behavior instanceof SuperBehavior){
			destinationCountry.superPopulation ++;
			this.superPopulation --;
		}
	}
	
	public int getDoctorPopulation(){
		int currentDoctorPopulation = 0;
		for(int i = 0; i < humans.size(); i++){
			if(humans.get(i) instanceof Doctor){
				currentDoctorPopulation++;
			}
		}
		 
		return currentDoctorPopulation;
	}
	
	public int getSuperPopulation(){
		int currentSuperPopulation = 0;
		for(int i = 0; i < humans.size(); i++){
			if(humans.get(i).behavior instanceof SuperBehavior){
				currentSuperPopulation++;
			}
		}
		 
		return currentSuperPopulation;
	}
	
	public int getImmunePopulation(){
		int currentImmunePopulation = 0;
		for(int i = 0; i < humans.size(); i++){
			if(humans.get(i).behavior instanceof ImmuneBehavior){
				currentImmunePopulation++;
			}
		}
		 
		return currentImmunePopulation;
	}


}

package EpidemicSimulationModel;

import java.util.ArrayList;
import java.util.Random;

public class World {
	private static World instance;
	
	public SimulationArguments args;
	public int infectedPopulation;
	public int deadPopulation;
	public int days;
	public static Country[][] country;

	private World() {
		this.args = SimulationArguments.getInstance();
		country = new Country[args.mapSize][args.mapSize];
		infectedPopulation = (int) ((args.infectedPercentage * args.population) / 100.0);
		days = GameData.DEFAULT_DAY_NO;
	}

	public static void initializeWorld(){
		instance = new World();
	}
	
	public static World getInstance(){
		return instance;
	}

	public int getMapSize() {
		return country.length;
	}
	
	//TODO COMPLETE METHODS BELOW
	public int getTotalPopulation(){
		int totalPopulation = 0;
		for(int i = 0; i < args.mapSize; i++){
			for(int j = 0; j < args.mapSize; j++){
				totalPopulation += country[i][j].getTotalPopulation();
			}
		}
		return totalPopulation;
	}
	
	public int getTotalInfectedCount(){
		int worldInfectedPopulation = 0;
		for(int i = 0; i < args.mapSize; i++){
			for(int j = 0; j < args.mapSize; j++){
				worldInfectedPopulation += country[i][j].getInfectedPopulation();
			}
		}
		return worldInfectedPopulation;
	}
	
	public int getTotalDeadCount(){
		int worldDeadPopulation = 0;
		for(int i = 0; i < args.mapSize; i++){
			for(int j = 0; j < args.mapSize; j++){
				worldDeadPopulation += country[i][j].getDeadPopulation();
			}
		}
		return worldDeadPopulation;
	}
	
	public int getTotalSickCount(){
		int worldSickPopulation = 0;
		for(int i = 0; i < args.mapSize; i++){
			for(int j = 0; j < args.mapSize; j++){
				worldSickPopulation += country[i][j].getSickPopulation();
			}
		}
		return worldSickPopulation;
	}
	
	public int getTotalHealthyCount(){
		int worldHealthyPopulation = 0;
		for(int i = 0; i < args.mapSize; i++){
			for(int j = 0; j < args.mapSize; j++){
				worldHealthyPopulation += country[i][j].getHealthyPopulation();
			}
		}
		return worldHealthyPopulation;
	}
	
	public int getTotalDoctorPopulation(){
		int worldDoctorPopulation = 0;
		for(int i = 0; i < args.mapSize; i++){
			for(int j = 0; j < args.mapSize; j++){
				worldDoctorPopulation += country[i][j].getDoctorPopulation();
			}
		}
		return worldDoctorPopulation;
	}
	
	public int getTotalSuperPopulation(){
		int worldSuperPopulation = 0;
		for(int i = 0; i < args.mapSize; i++){
			for(int j = 0; j < args.mapSize; j++){
				worldSuperPopulation += country[i][j].getSuperPopulation();
			}
		}
		return worldSuperPopulation;
	}
	
	public int getTotalImmunePopulation(){
		int worldImmunePopulation = 0;
		for(int i = 0; i < args.mapSize; i++){
			for(int j = 0; j < args.mapSize; j++){
				worldImmunePopulation += country[i][j].getImmunePopulation();
			}
		}
		return worldImmunePopulation;
	}


	public void initializeCountries(){
		int countriesWithoutPeople = getMapSize() * getMapSize();
		int populationWithoutCountry = args.population;
	
		for(int row = 0; row < country.length; row++){
			for(int col = 0; col< country[row].length; col++){
				int tempPop = calculatePopulationNumber(countriesWithoutPeople, populationWithoutCountry);
				countriesWithoutPeople--;
				populationWithoutCountry -= tempPop;
//				System.out.println("World.initializeCountries " + populationWithoutCountry);
				country[row][col] = new Country(tempPop, args.infectedPercentage);
			}
		}
	}
	
	// method name is temporary
	private int calculatePopulationNumber(int countriesWithoutPeople, int populationWithoutCountry){
		Random rand = new Random();
		int distributionRange = (args.population /(getMapSize() * getMapSize() * 10));
		if(distributionRange <= 0) distributionRange = 3;
		int randomNumber = rand.nextInt(distributionRange);
		if(countriesWithoutPeople != 1){
			return (populationWithoutCountry / countriesWithoutPeople) + randomNumber;
		}else{
			return populationWithoutCountry;
		}
	}

	public void initializeNeighbours() {
		for (int i = 0; i < this.getMapSize(); i++) {
			for (int j = 0; j < this.getMapSize(); j++) {
				getCountryAt(i, j).setNeigbours(getNeighboursOf(i, j));
			}
		}
	}
	
	public static Country getCountryAt(int x, int y) {
		return country[x][y];
	}

	public ArrayList<Country> getNeighboursOf(int x, int y) {
		ArrayList<Country> neighbours = new ArrayList<Country>();
		int mapSize = getMapSize();
		neighbours.add(getCountryAt(Math.floorMod(x - 1, mapSize), y));
		neighbours.add(getCountryAt(Math.floorMod(x + 1, mapSize), y));
		neighbours.add(getCountryAt(x, Math.floorMod(y + 1, mapSize)));
		neighbours.add(getCountryAt(x, Math.floorMod(y - 1, mapSize)));

		return neighbours;
	}
}

package EpidemicSimulationModel;

public class SimulationArguments {
	public int infectedPercentage;
	public int population;
	public int doctorPercentage;
	public int mapSize;
	public int superHumanPercentage;
	public int travelPlanePercentage;
	public int doctorHealAmount;
	
	private static SimulationArguments instance;
	
	private SimulationArguments(){
		this.mapSize = GameData.DEFAULT_MAP_SIZE;
		this.population = GameData.DEFAULT_POPULATION;
		this.infectedPercentage = GameData.DEFAULT_INFECTED_PERCENTAGE;
		this.doctorPercentage = GameData.DEFAULT_DOCTOR_PERCENTAGE;
		this.superHumanPercentage = GameData.DEFAULT_SUPERHUMAN_PERCENTAGE;
		this.travelPlanePercentage = GameData.DEFAULT_TRAVEL_PLANE_PERCENTAGE;
		this.doctorHealAmount = GameData.DEFAULT_DOCTOR_HEAL_AMOUNT;
	}
	
	public static SimulationArguments getInstance(){
		if(instance == null){
			instance = new SimulationArguments();
			return instance;
		}
		else return instance;
	}
	
	// this is not used
	private SimulationArguments(int mapSize,int population,int infectedPercentage,int doctorPercentage){
		this.mapSize = mapSize;
		this.population = population;
		this.infectedPercentage = infectedPercentage;
		this.doctorPercentage = doctorPercentage;
	}
	
}

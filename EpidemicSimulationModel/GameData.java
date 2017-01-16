package EpidemicSimulationModel;

import java.util.ArrayList;
import java.util.Arrays;

public class GameData {
	public static ArrayList<Integer> mapSizes = new ArrayList<Integer>(Arrays.asList(8, 10, 12, 14));
	public static final int DEFAULT_MAP_SIZE = 8;
	public static final int DEFAULT_DAY_NO = 1;
	public static final int DEFAULT_POPULATION = 10000;
	public static final int DEFAULT_INFECTED_PERCENTAGE = 30;
	public static final int DEFAULT_DOCTOR_PERCENTAGE = 10;
	public static final int DEFAULT_SUPERHUMAN_PERCENTAGE = 5;
	public static final int DEFAULT_TRAVEL_PLANE_PERCENTAGE = 20;
	public static final int INFECTION_TRANSMISSIBILITY_RATE = 40;
	
	public static final int DEFAULT_DOCTOR_HEAL_AMOUNT = 5;

	public static final int DAYS_TO_BECOME_SICK = 6;
	public static final int DAYS_TO_BECOME_PROB_DEAD = 14;
	public static final int DAYS_TO_BECOME_IMMUNE = 16;
	public static final int DAYS_TO_BECOME_HEALTHY = 18;
	public static final int PROB_OF_BECOME_DEAD = 25; 
	
}
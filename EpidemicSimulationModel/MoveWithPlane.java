package EpidemicSimulationModel;

import java.util.Random;

public class MoveWithPlane implements MovementBehavior {
	private static MoveWithPlane instance = null;
	private Random rand = new Random();
	private SimulationArguments args = SimulationArguments.getInstance();

	private MoveWithPlane(){
		
	}

	public static MoveWithPlane getInstance() {
		if (instance == null) {
			instance = new MoveWithPlane();
			return instance;
		} else
			return instance;
	}

	public void move(Human human, Country country) {
		if(human.behavior instanceof DeadBehavior){
			return;
		}
		int x = rand.nextInt(args.mapSize);
		int y = rand.nextInt(args.mapSize);
		
		
		// problem: bir ulkeden baska ulkeye transfer ederken removeall
		// ve addall methodlari cok verimsiz calisiyor ve program cok yavasliyor. 
		Country destinationCountry = pickRandomCountry(x, y);
		country.transferPerson(human, destinationCountry);
		
		
		//Country destinationCountry = pickRandomCountry(country);
		//for (int i = 0; i < country.args.mapSize; i++) {
		//	destinationCountry = pickRandomCountry(destinationCountry);
		//}
		//destinationCountry.transferPerson(human, country);
	}
	
	
	private Country pickRandomCountry(int x, int y){
		return World.getCountryAt(x, y);
	}

	private Country pickRandomCountry(Country currentCountry) {
		int coordinate = rand.nextInt(3);

		return currentCountry.getNeighbours().get(coordinate);
	}

}

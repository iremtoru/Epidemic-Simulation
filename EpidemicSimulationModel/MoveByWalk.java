package EpidemicSimulationModel;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Random;

public class MoveByWalk implements MovementBehavior {
	private static MoveByWalk instance = null;
	private Random rand = new Random();
	private int selectedNeighbour;
	private ArrayList<Country> neighbours;
	private boolean allNeighboursAreSick = true;
	private boolean moved = false;
	
	private MoveByWalk(){
		
	}
	
	public static MoveByWalk getInstance(){
		if(instance == null){
			instance = new MoveByWalk();
			return instance;
		}
		else return instance;
	}
	
	
	@Override
	public void move(Human human, Country currentCountry) {
		if(human.behavior instanceof DeadBehavior){
			return;
		}
		
		ArrayList<Country> unsickNeighbours = getUnsickNeighbours(currentCountry);
		
		if(unsickNeighbours.size() == 0){
			return;
		}
		else{
			int random = rand.nextInt(unsickNeighbours.size());
			currentCountry.transferPerson(human, unsickNeighbours.get(random));
		}
		
//		neighbours = currentCountry.getNeighbours();
//		checkIfAllNeighboursAreSick();
//		
//		if (!allNeighboursAreSick) {
//			tryToMove(human, currentCountry);
//		}			
	}
	
	private ArrayList<Country> getUnsickNeighbours(Country country){
		ArrayList<Country> result = new ArrayList<Country>(4);
		
		for (int i = 0; i < 3; i++) {
			if (country.getNeighbours().get(i).sickPopulation <= 0){
				result.add(country.getNeighbours().get(i));
			}		
		}
		
		return result;
	}

//	private void checkIfAllNeighboursAreSick() {
//		for (int i = 0; i < 3; i++) {
//			if (neighbours.get(i).infectedPopulation == 0)
//				allNeighboursAreSick = false;
//		}
//	}
	
//	private void tryToMove(Human human, Country currentCountry) {
//		pickANeighbour();
//		Country destinationCountry = neighbours.get(selectedNeighbour);
//		
//		if (destinationCountry.sickPopulation == 0) {
//			currentCountry.transferPerson(human, destinationCountry);
//		}else {
//			tryToMove(human, currentCountry);
//		}
//	}
	
//	private void pickANeighbour() {
//		selectedNeighbour = rand.nextInt(3);
//	}

}

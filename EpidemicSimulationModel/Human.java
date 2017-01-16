package EpidemicSimulationModel;

import java.util.Random;

public abstract class Human {
	HumanBehavior behavior;
	MovementBehavior movementBehavior;
	private MoveByWalk moveByWalkingBehavior = MoveByWalk.getInstance();
	private MoveWithPlane moveWithPlaneBehavior = MoveWithPlane.getInstance();
	protected int daysAfterInfection = 0; 
	int daysUntilMovement = 0;
	protected Random rand = new Random();
	private SimulationArguments simArgs = SimulationArguments.getInstance();
	static final int INFECTED = 0;
	static final int HEALTHY = 1;
	static final int SUPER = 2;
	private int rollANumber = 0;

	public Human(int healthCondition) {
		decideWhenToMove();
		
		if (healthCondition == HEALTHY) {
			behavior = new HealthyBehavior();
		} else if (healthCondition == INFECTED) {
			behavior = new InfectedBehavior();
		}
		else if(healthCondition == SUPER){
			behavior = new SuperBehavior();
		}
	}

	public void simulateInfection(Country country) {
		behavior = behavior.simulateHuman(country);		
	}
	
	public void simulateMovement(Country country){
		this.daysUntilMovement--;
		if(daysUntilMovement <= 0){
			decideHowToMove();
			moveToAnotherCountry(country);
		}
	}
	
	public void decideHowToMove(){
		// travelPlanePercentage is 20 by default.
		rollANumber = rand.nextInt(101);
		if(rollANumber > simArgs.travelPlanePercentage){
		movementBehavior = moveByWalkingBehavior;
		}else {
		movementBehavior = moveWithPlaneBehavior;
		}
	}
	
	public void decideWhenToMove(){
		daysUntilMovement = rand.nextInt(6);	
	}
	
	public void setBehavior(HumanBehavior behavior){
		this.behavior = behavior;
	}
	
	public void moveToAnotherCountry(Country country){
		movementBehavior.move(this, country);
		decideWhenToMove();
	}
}

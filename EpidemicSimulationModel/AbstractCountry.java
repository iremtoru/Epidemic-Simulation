package EpidemicSimulationModel;

import java.util.ArrayList;

public abstract class AbstractCountry {
	protected int totalPopulation;
	protected ArrayList<Human> humans;

	abstract protected void addHumans();
}

package EpidemicSimulationView;

import javax.swing.JButton;
import EpidemicSimulationModel.Country;
import EpidemicSimulationModel.GameData;

public class CountryButton extends JButton {
	Country country;
	
	public CountryButton(Country country) {
//		System.out.println("this is in CountryButton constructor for debugging: " + country);
		//System.out.println("countryButton constructor:  ---" + country.getHumans().get(0));
		
		this.country = country;
	}
	
	public String getInfo() {
		return country.toString();
	}

}

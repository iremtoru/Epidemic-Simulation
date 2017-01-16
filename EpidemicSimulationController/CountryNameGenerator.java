package EpidemicSimulationController;

import java.util.ArrayList;
import java.util.Random;

public class CountryNameGenerator {
	static final int MAX_WORDLING_COUNT = 3;

	  private final Random random = new Random();
	  
	  private ArrayList<String> vowels;
	  private ArrayList<String> consonants;

	  public CountryNameGenerator(int length) {
		  addConsonants();
		  addVowels();
	  }
	  
	  private void addVowels(){
		  vowels = new ArrayList<String>();
		  vowels.add("e");
		  vowels.add("u");
		  vowels.add("o");
		  vowels.add("a");
		  vowels.add("i");
	  }
	  
	  private void addConsonants(){
		  consonants = new ArrayList<String>();
		  consonants.add("q");
		  consonants.add("w");
		  consonants.add("r");
		  consonants.add("t");
		  consonants.add("y");
		  consonants.add("p");
		  consonants.add("s");
		  consonants.add("d");
		  consonants.add("f");
		  consonants.add("g");
		  consonants.add("h");
		  consonants.add("j");
		  consonants.add("k");
		  consonants.add("l");
		  consonants.add("z");
		  consonants.add("x");
		  consonants.add("c");
		  consonants.add("v");
		  consonants.add("b");
		  consonants.add("n");
		  consonants.add("m");
	  }
	  
	  private String generateWordling(){
		  boolean wordLingType = random.nextBoolean();
		  String wordling = "";
		  
		  String letter1 = wordling += vowels.get(random.nextInt(vowels.size()));
		  String letter2 = consonants.get(random.nextInt(consonants.size()));
		  
		  if(wordLingType){
			  wordling = letter1 + letter2;
		  }
		  else{
			  wordling = letter2 + letter1;
		  }
		  
		  return wordling;
	  }

	  public String nextString() {
		  int wordLingCount = random.nextInt(MAX_WORDLING_COUNT)+2;
		  
		  String countryName = "";
		  
		  for(int i = 0; i<wordLingCount;i++){
			  countryName += generateWordling();
		  }
		  
		  return (countryName.substring(0, 1).toUpperCase() + countryName.substring(1));
		  
	  }
}

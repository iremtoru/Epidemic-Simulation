package EpidemicSimulationController;

import java.util.ArrayList;

public class StringParser {
	private static final char SEPERATOR = ' ';
	
	public static String parseNumber(int number,int minDigitCount){
		String parsedNum =""+ number;
		String result = getZerosOfLength(minDigitCount - parsedNum.length()) + "" +parsedNum;
		
		return result;
//		return makeIdentedString(result);
	}
	
	private static String getZerosOfLength(int length){
		length++;
		String result = "";
		
		if(length > 0){
			for(int i = 0;i<length;i++){
				result += " ";
			}
		}
		
		return result;
	}
	
	
	private static String makeIdentedString(String str){
		ArrayList<String> parts = new ArrayList<String>();
		String result = "";
		
		for(int i = str.length();i>=0;i--){
			int diff = str.length() - i;
			
			if(diff%3 == 0){
				parts.add(str.substring(i,i+3));
			}
		}
		
		for(int i=parts.size()-1; i> 0;i--){
			result += SEPERATOR+parts.get(i);
			System.out.println(parts.get(i));
		}
		result += parts.get(0);
		return result;
	}
//	private static String makeIdentedString(String str){
//		int n = 0;
//		
//		for(int i = str.length(); i>=0;i--){
//			if(n%4 == 0 && n!=0){
//				str = str.substring(0, str.length()-n) 
//						+SEPERATOR+
//						str.substring((str.length()-n), str.length());
//			}
//			n++;
//		}
//		
//		if(str.charAt(0) == SEPERATOR){
//			return str.substring(1);
//		}
//		
//		return str;
//	}

}

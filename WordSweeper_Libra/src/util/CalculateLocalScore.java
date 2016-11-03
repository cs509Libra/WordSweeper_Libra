package util;

import java.util.HashMap;
import java.util.Map;

public class CalculateLocalScore {
	public static Integer calculateLetterScore(String letter){
		Map<String, Integer> letterScoreDictionary = getLetterScoreDictionary();
		Integer score = letterScoreDictionary.get(letter);
		return score;
	}
	
	public static Integer calculateLetterScore(char letter){
		Map<String, Integer> letterScoreDictionary = getLetterScoreDictionary();
		Integer score = letterScoreDictionary.get(Character.toString(letter));
		return score;
	}
	
	public static Integer calculateWordScore(String word){
		if(WordTable.isWord(word)){
			int length = word.length();
			int sumScoreOfLetters = 0;
			char[] letterArray = word.toCharArray();
			for(char c : letterArray){
				Integer lettScore = CalculateLocalScore.calculateLetterScore(Character.toUpperCase(c));
				sumScoreOfLetters += (int)lettScore;
			}
			int score = ((int) (Math.pow(2, length))) * 10 * sumScoreOfLetters;
			return (Integer)score;
		}
		return -1;
	}
	
	public static Map<String, Integer> getLetterScoreDictionary(){
		Map<String, Integer> letterScoreDictionary = new HashMap<String, Integer>();
		letterScoreDictionary.put("E", (Integer)1);
		letterScoreDictionary.put("T", (Integer)1);
		letterScoreDictionary.put("A", (Integer)2);
		letterScoreDictionary.put("O", (Integer)2);
		letterScoreDictionary.put("I", (Integer)2);
		letterScoreDictionary.put("N", (Integer)2);
		letterScoreDictionary.put("S", (Integer)2);
		letterScoreDictionary.put("H", (Integer)2);	
		letterScoreDictionary.put("R", (Integer)2);
		letterScoreDictionary.put("D", (Integer)3);
		letterScoreDictionary.put("L", (Integer)3);
		letterScoreDictionary.put("C", (Integer)3);
		letterScoreDictionary.put("U", (Integer)3);
		letterScoreDictionary.put("M", (Integer)3);		
		letterScoreDictionary.put("W", (Integer)3);
		letterScoreDictionary.put("F", (Integer)4);
		letterScoreDictionary.put("G", (Integer)4);
		letterScoreDictionary.put("Y", (Integer)4);
		letterScoreDictionary.put("P", (Integer)4);
		letterScoreDictionary.put("B", (Integer)4);		
		letterScoreDictionary.put("V", (Integer)5);
		letterScoreDictionary.put("K", (Integer)5);
		letterScoreDictionary.put("J", (Integer)7);
		letterScoreDictionary.put("X", (Integer)7);
		letterScoreDictionary.put("Q", (Integer)8);
		letterScoreDictionary.put("Z", (Integer)8);
		return letterScoreDictionary;
	}
}

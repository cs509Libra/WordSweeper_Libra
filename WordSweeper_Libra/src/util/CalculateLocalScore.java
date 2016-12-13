package util;

import java.util.HashMap;
import java.util.Map;
/**
 * This class calculate local scores based on the word player submits.
 * @author You Zhou, Qingquan Zhao, Han Bao, Ruochen Shi (Authors contribute equally)
 *
 */

public class CalculateLocalScore {
	
	/**
	 * {@link #calculateLetterScore(String)} provides score of single letter with string format.
	 * @param letter
	 * @return
	 */
	public static Integer calculateLetterScore(String letter){
		Integer score;
		if(letter.length()==0){
			return (Integer)0;
		}
		String upperLett = letter.toUpperCase();
		Map<String, Integer> letterScoreDictionary = getLetterScoreDictionary();
		if(upperLett.equals("QU")){
			score = letterScoreDictionary.get("Q") + letterScoreDictionary.get("U");
		}else{
			score = letterScoreDictionary.get(upperLett);
		}	
		return score;
	}
	
	/**
	 * {@link #calculateLetterScore(char)} provides score of single letter with char format.
	 * @param letter
	 * @return
	 */
	public static Integer calculateLetterScore(char letter){	
		String upperLett = Character.toString(letter).toUpperCase();
		return calculateLetterScore(upperLett);
	}
	/**
	 * The {@link #calculateWordScore(String, Integer)} provides the score of a word.
	 * @param word
	 * @param wordLength
	 * @return
	 */
	
	
	public static Integer calculateWordScore(String word, Integer wordLength){
		if(wordLength == 0){
			return 0;
		}
		if(WordTable.isWord(word)){
			int sumScoreOfLetters = 0;
			char[] letterArray = word.toCharArray();
			for(char c : letterArray){
				Integer lettScore = CalculateLocalScore.calculateLetterScore(Character.toUpperCase(c));
				sumScoreOfLetters += (int)lettScore;
			}
			int score = ((int) (Math.pow(2, wordLength))) * 10 * sumScoreOfLetters;
			return (Integer)score;
		}
		return 0;
	}
	
	/**
	 * {@link #getLetterScoreDictionary()} provides the map of single letter as the key and its score as the value.
	 * @return
	 */
	
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

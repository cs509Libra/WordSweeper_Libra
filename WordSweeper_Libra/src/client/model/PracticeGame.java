package client.model;

public class PracticeGame {
	public String generateRandomCellInfo(){
		String[] LetterSources = {"A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z"};
		String randomCellInfo = "";
		for(int i = 0 ; i < 16 ; i++)                  
		{
			randomCellInfo += LetterSources[(int)(Math.random()*26)];
		}
		return randomCellInfo;
	}
}

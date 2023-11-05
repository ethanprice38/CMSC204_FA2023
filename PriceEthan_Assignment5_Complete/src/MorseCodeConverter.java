import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class MorseCodeConverter {
	
	static MorseCodeTree tree = new MorseCodeTree();
	
	public static String convertToEnglish(File file) throws FileNotFoundException {
		
		Scanner scan = new Scanner(file);
		String morsecode = "";
		while (scan.hasNextLine()) {
			morsecode += scan.nextLine();
		}
		scan.close();
		
		return convertToEnglish(morsecode);
	}
	
	public static String convertToEnglish(String morseCode) {
		
		MorseCodeTree tree = new MorseCodeTree();
		String wordsTranslated = "";
		String lettersTranslated = "";
		String[] morsewordsTranslatedArr = morseCode.split("/");
		
		for (int i = 0; i < morsewordsTranslatedArr.length; i++) {
			String[] morselettersTranslated = morsewordsTranslatedArr[i].split(" ");
			for (int j = 0; j < morselettersTranslated.length; j++) {
				lettersTranslated += tree.fetch(morselettersTranslated[j]);
				wordsTranslated += lettersTranslated;
				lettersTranslated = "";
			}
			if (i < morsewordsTranslatedArr.length - 1) {
				wordsTranslated += " ";
			}
		}
		
		return wordsTranslated.toLowerCase();
	}

	public static String printTree() {
		ArrayList<String> treeToList = tree.toArrayList();
		String listToPrint = "";
		
		for (int i = 0; i < treeToList.size(); i++) {
			listToPrint += treeToList.get(i);
			if (i  < treeToList.size() - 1) {
				listToPrint += " ";
			}
		}
		
		
		return listToPrint.toLowerCase();
	}
}

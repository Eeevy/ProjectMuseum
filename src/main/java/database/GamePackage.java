package database;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 * Represents a full game, one category and ten questions
 * 
 * @author emmashakespeare
 *
 */
public class GamePackage {
	private ArrayList<Question> questionPackage;

	public GamePackage() {
		questionPackage = new ArrayList<Question>();
	}

	public void readQuestions() throws IOException {
		try (BufferedReader br = new BufferedReader(new InputStreamReader(
				new FileInputStream("src/database/QuestionFormSWE.txt"),"UTF-8"))){
			String str = br.readLine();
			while (str != null) {//så länge det finns en rad att läsa
				if(!(str.equals("%"))){
					String [] var = str.split("\\*");
					questionPackage.add(new Question(var[0], var[1], var[2], var[3], var[4], var[5]));
				}
				
				str = br.readLine();
			}

		}
	
	}
	
	
	public static void main(String[] args) {
		GamePackage run = new GamePackage();
		try {
			run.readQuestions();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}

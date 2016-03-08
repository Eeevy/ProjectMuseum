package database;

import java.net.URL;

/**
 * Represents a question with four alternatives and one correct answer
 * To be used when simulating a database with questions?
 * @author emmashakespeare
 *
 */
public class Question {
	
//	private String category;
//	private double packetId;
	private String question;
	private String a1;
	private String a2;
	private String a3;
	private String a4;
	private String correctAnswer;
//	private URL picUrl;
	
	public Question(String question, String a1, String a2, String a3,
			String a4, String correctAnswer) {
		this.question = question;
		this.a1 = a1;
		this.a2 = a2;
		this.a3 = a3;
		this.a4 = a4;
		this.correctAnswer = correctAnswer;
	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public String getA1() {
		return a1;
	}

	public void setA1(String a1) {
		this.a1 = a1;
	}

	public String getA2() {
		return a2;
	}

	public void setA2(String a2) {
		this.a2 = a2;
	}

	public String getA3() {
		return a3;
	}

	public void setA3(String a3) {
		this.a3 = a3;
	}

	public String getA4() {
		return a4;
	}

	public void setA4(String a4) {
		this.a4 = a4;
	}

	public String getCorrectAnswer() {
		return correctAnswer;
	}

	public void setCorrectAnswer(String correctAnswer) {
		this.correctAnswer = correctAnswer;
	}
//	public String getCategory() {
//		return category;
//	}
//
//	public void setCategory(String category) {
//		this.category = category;
//	}
//
//	public double getPacketId() {
//		return packetId;
//	}
//
//	public void setPacketId(double packetId) {
//		this.packetId = packetId;
//	}
//
//	public URL getPicUrl() {
//		return picUrl;
//	}
//
//	public void setPicUrl(URL picUrl) {
//		this.picUrl = picUrl;
//	}


}

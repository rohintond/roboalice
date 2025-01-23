package com.robo.alice.data;

import jakarta.persistence.Entity;

@Entity
public class Question {
	private String question;
	private String answer;

	protected Question() {

	}

	public Question(String question, String answer) {
		this.question = question;
		this.answer = answer;
	}

	@Override
	public String toString() {
		return question + "\\n" + answer;
	}

	public String getQuestion() {
		return question;
	}

	public String getAnswer() {
		return answer;
	}

}

package com.robo.alice.eval.evaluator;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.text.similarity.LevenshteinDistance;

import com.robo.alice.eval.EvalResult;
import com.robo.alice.eval.StringEvalResult;

class SimpleQuestionEvaluator implements Evaluator {
	private static final String evaluatorName = "Basic Questions";
	private static final int maxLevenshtienDistance = 6;
	private final List<String> questions;
	private final List<String> answers;

	SimpleQuestionEvaluator(List<List<String>> questions) {
		this.questions = new ArrayList<>();
		this.answers = new ArrayList<>();
		questions.stream().forEach((List<String> question) -> {
			this.questions.add(question.get(0).toLowerCase());
			this.answers.add(question.get(1));
		});
	}

	@Override
	public boolean matches(String expression) {
		return true;
	}

	@Override
	public EvalResult evaluate(String expression) {
		LevenshteinDistance lv = LevenshteinDistance.getDefaultInstance();
		String maxAnswer = "";
		int maxMatch = maxLevenshtienDistance;
		expression = expression.toLowerCase();
		for (int index = 0; index < questions.size(); index++) {
			String question = questions.get(index);
			int distance = lv.apply(question, expression);
			if (distance < maxMatch) {
				maxMatch = distance;
				maxAnswer = answers.get(index);
			}
		}
		if (maxMatch < maxLevenshtienDistance) {
			return new StringEvalResult(evaluatorName, SimpleQuestionEvaluator.class.getCanonicalName(), maxAnswer);
		}
		return new StringEvalResult(evaluatorName, SimpleQuestionEvaluator.class.getCanonicalName(),
				"Question does not match a known question. Please ask another.", false);
	}

}

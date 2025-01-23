package com.robo.alice.eval.evaluator;

import java.util.ArrayList;
import java.util.List;

import com.robo.alice.eval.EvalResult;
import com.robo.alice.eval.StringEvalResult;

public class EvaluatorTree {
	private static final String evaluatorName = "Basic Evaluator";

	private final List<Evaluator> evaluators;

	public EvaluatorTree(List<List<String>> basicQuestions) {
		evaluators = new ArrayList<Evaluator>();
		evaluators.add(new ArithmeticEvaluator());
		evaluators.add(new SimpleQuestionEvaluator(basicQuestions));
	}

	public EvalResult evaluate(String expression) {
		for (Evaluator evaluator : evaluators) {
			if (evaluator.matches(expression)) {
				return evaluator.evaluate(expression);
			}
		}

		return new StringEvalResult(evaluatorName, EvaluatorTree.class.getCanonicalName(),
				"The question could not be understood, please ask another.", false);

	}
}

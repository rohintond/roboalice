package com.robo.alice.eval;

import org.springframework.stereotype.Service;

import com.robo.alice.data.QuestionService;
import com.robo.alice.eval.evaluator.EvaluatorTree;

@Service
public class EvalService {
	private final EvaluatorTree evalTree;

	public EvalService(QuestionService questionService) {
		evalTree = new EvaluatorTree(questionService.getAllQuestions());
	}

	public EvalResult evaluate(String expression) {
		return evalTree.evaluate(expression);
	}

}

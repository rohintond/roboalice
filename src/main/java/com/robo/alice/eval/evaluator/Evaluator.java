package com.robo.alice.eval.evaluator;

import com.robo.alice.eval.EvalResult;

public interface Evaluator {
	boolean matches(String expression);

	EvalResult evaluate(String expression);
}

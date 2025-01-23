package com.robo.alice.eval;

public interface EvalResult {
	boolean evalSuccessful();
	String toString();
	String getEvaluatorName();
	String getEvaluatorFullName();
}

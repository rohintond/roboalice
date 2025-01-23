package com.robo.alice.eval;

abstract class EvalResultWithEvaluatorInfo implements EvalResult {
	private final String evaluatorName;
	
	private final String evaluatorFullName;
	
	EvalResultWithEvaluatorInfo(String evaluatorName, String evaluatorFullName) {
		this.evaluatorName = evaluatorName;
		this.evaluatorFullName = evaluatorFullName;
	}
	
	public String getEvaluatorName() {
		return evaluatorName;
	}
	
	public String getEvaluatorFullName() {
		return evaluatorFullName;
	}
}

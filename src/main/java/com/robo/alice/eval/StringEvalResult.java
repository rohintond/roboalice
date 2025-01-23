package com.robo.alice.eval;

public class StringEvalResult extends EvalResultWithEvaluatorInfo {
	private final String result;
	private final boolean successful;
	
	public StringEvalResult(String evaluatorName, String evaluatorFullName, String result) {
		this(evaluatorName, evaluatorFullName, result, true);
	}
	
	public StringEvalResult(String evaluatorName, String evaluatorFullName, String result, boolean successful) {
		super(evaluatorName, evaluatorFullName);
		this.result = result;
		this.successful = successful;
	}

	@Override
	public boolean evalSuccessful() {
		return successful;
	}

	@Override
	public String toString() {
		return result;
	}
}

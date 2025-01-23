package com.robo.alice.eval.evaluator;

import java.util.List;

import com.robo.alice.eval.EvalException;

class Calculator {
	double applyOperations(List<String> tokens) throws EvalException {
		return evaluate(tokens, 0);
	}

	private double evaluate(List<String> tokens, int tokenIdx) throws EvalException {
		try {
			double num1 = Double.parseDouble(tokens.get(tokenIdx));
			if (tokenIdx == tokens.size() - 1) {
				return num1;
			}
			if (tokenIdx == tokens.size() - 2) {
				throw new EvalException("Expression ends in an operator");
			}
			String operator = tokens.get(tokenIdx + 1);
			while (operator.equals("*") || operator.equals("/")) {
				double num2 = Double.parseDouble(tokens.get(tokenIdx + 2));
				if (operator.equals("*")) {
					num1 *= num2;
				} else {
					num1 /= num2;
				}
				if (tokenIdx < tokens.size() - 3) {
					operator = tokens.get(tokenIdx + 3);
				} else {
					operator = "";
				}
				tokenIdx += 2;
			}
			if (tokenIdx == tokens.size() - 1) {
				return num1;
			}
			if (operator.equals("+")) {
				return num1 + evaluate(tokens, tokenIdx + 2);
			} else {
				return num1 - evaluate(tokens, tokenIdx + 2);
			}

		} catch (NumberFormatException ne) {
			throw new EvalException(ne.getMessage());
		}
	}
}

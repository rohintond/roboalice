package com.robo.alice.eval.evaluator;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.robo.alice.eval.EvalException;

class Tokenizer {
	private final record TokenResult(boolean passed, String token, int nextPos) {
	};

	private static final Set<Character> operators = defineOperators();

	private static Set<Character> defineOperators() {
		Set<Character> operators = new HashSet<>();
		operators.add('+');
		operators.add('-');
		operators.add('*');
		operators.add('/');
		return operators;
	}

	List<String> tokenize(String expression) throws EvalException {
		List<String> tokens = new ArrayList<>();
		int pos = 0;
		while (pos < expression.length()) {
			TokenResult result = getNumber(expression, pos);
			verifyTokenResult(result, expression, pos);
			tokens.add(result.token);
			pos = skipWhitespace(expression, result.nextPos);
			if (pos < expression.length()) {
				result = getOperator(expression, pos);
				verifyTokenResult(result, expression, pos);
				tokens.add(result.token);
				pos = skipWhitespace(expression, result.nextPos);
				if (pos == expression.length()) {
					throw new EvalException("Expression ends in an operator");
				}
			}
		}
		return tokens;
	}

	private void verifyTokenResult(TokenResult result, String expression, int pos) throws EvalException {
		if (!result.passed) {
			throw new EvalException("Error at position " + (pos + 1) + " in " + expression);
		}
	}

	private TokenResult getNumber(String expression, int pos) {
		char start = expression.charAt(pos);
		int startPos = pos;
		boolean negative = false;
		if (start == '-') {
			pos++;
			negative = true;
		}
		while (pos < expression.length() && Character.isDigit(expression.charAt(pos))) {
			pos++;
		}
		if (pos < expression.length() && expression.charAt(pos) == '.') {
			pos++;
			while (pos < expression.length() && Character.isDigit(expression.charAt(pos))) {
				pos++;
			}
		}
		if (pos == startPos || (negative && pos == startPos + 1)) {
			return new TokenResult(false, "", startPos);
		}

		String token = expression.substring(startPos, pos);
		return new TokenResult(true, token, pos);
	}

	private TokenResult getOperator(String expression, int pos) {
		char ch = expression.charAt(pos);
		if (operators.contains(ch)) {
			return new TokenResult(true, "" + ch, pos + 1);
		}
		return new TokenResult(false, "", pos);
	}

	private int skipWhitespace(String expression, int pos) {
		while (pos < expression.length() && Character.isWhitespace(expression.charAt(pos))) {
			pos++;
		}
		return pos;
	}
}

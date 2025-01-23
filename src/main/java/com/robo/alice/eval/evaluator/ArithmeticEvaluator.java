package com.robo.alice.eval.evaluator;

import java.text.DecimalFormat;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Pattern;

import com.robo.alice.eval.EvalException;
import com.robo.alice.eval.EvalResult;
import com.robo.alice.eval.StringEvalResult;

class ArithmeticEvaluator implements Evaluator {

	private static final String evaluatorName = "Basic Arithmetic";
	private final Pattern arithmeticPattern = Pattern.compile("[\\d\\s\\+\\-\\*/\\.]*");
	private final DecimalFormat numberFormat;
	private final Set<Character> operators;

	public ArithmeticEvaluator() {
		numberFormat = new DecimalFormat();
		numberFormat.setMaximumFractionDigits(2);
		numberFormat.setMinimumFractionDigits(0);

		operators = new HashSet<>();
		operators.add('+');
		operators.add('-');
		operators.add('*');
		operators.add('/');
	}

	@Override
	public boolean matches(String expression) {
		return arithmeticPattern.matcher(expression).matches();
	}

	@Override
	public EvalResult evaluate(String expression) {
		try {
			List<String> tokens = new Tokenizer().tokenize(expression);
			double result = new Calculator().applyOperations(tokens);
			return new StringEvalResult(evaluatorName, ArithmeticEvaluator.class.getCanonicalName(),
					numberFormat.format(result));
		} catch (EvalException ev) {
			return new StringEvalResult(evaluatorName, ArithmeticEvaluator.class.getCanonicalName(),
					"Unable to resolve arithmetic expression: " + ev.getMessage(), false);
		}
	}

}

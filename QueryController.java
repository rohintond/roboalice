package com.robo.alice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.robo.alice.eval.EvalResult;
import com.robo.alice.eval.EvalService;

@RestController
public class QueryController {
	private static final Logger log = LoggerFactory.getLogger(QueryController.class);

	@Autowired
	private EvalService evalService;

	@GetMapping("/query")
	public QueryResult query(@RequestParam(value = "question", defaultValue = "") String question) {
		if (question == null || question.equals("")) {
			return new QueryResult(false, "Unknown", "Please enter a question.");
		}
		EvalResult result = evalService.evaluate(question);
		logResult(result);
		return new QueryResult(result.evalSuccessful(), result.getEvaluatorName(), result.toString());
	}

	private void logResult(EvalResult result) {
		if (result.evalSuccessful()) {
			log.info("Eval success from evaluator " + result.getEvaluatorFullName());
		} else {
			log.error(
					"Eval failure from evaluator " + result.getEvaluatorFullName() + " message: " + result.toString());
		}
	}
}

package com.robo.alice.eval.evaluator;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

class SimpleQuestionEvaluatorUTest {

	@Test
	void testBasicQuestions() {
		List<List<String>> questions = new ArrayList<>();
		questions.add(Arrays.asList("question1", "answer1"));
		questions.add(Arrays.asList("question2", "answer2"));
		questions.add(Arrays.asList("a really long question", "answer3"));
		SimpleQuestionEvaluator underTest = new SimpleQuestionEvaluator(questions);

		assertEquals("answer1", underTest.evaluate("question1").toString());
		assertEquals("answer2", underTest.evaluate("question2").toString());
		assertEquals("answer2", underTest.evaluate("QUestion2").toString());
		assertEquals("answer3", underTest.evaluate("a real long question").toString());
	}

}

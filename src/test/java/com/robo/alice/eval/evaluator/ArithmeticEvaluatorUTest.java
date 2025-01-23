package com.robo.alice.eval.evaluator;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class ArithmeticEvaluatorUTest {

	private static ArithmeticEvaluator underTest = new ArithmeticEvaluator();

	@Test
	void testMatches() {
		assertTrue(underTest.matches("3"));
		assertTrue(underTest.matches("3+7-9"));
		assertTrue(underTest.matches("3 + 7 - 9 "));
		assertTrue(underTest.matches("-3 + 2"));
		assertFalse(underTest.matches("3a"));
	}

	@Test
	void testEvaluate() {
		assertEquals("3", underTest.evaluate("3").toString());
		assertEquals("1", underTest.evaluate("3+7-9").toString());
		assertEquals("1", underTest.evaluate("3 + 7 - 9 ").toString());
		assertEquals("-1", underTest.evaluate("-3 + 2").toString());
		assertEquals("-3.14", underTest.evaluate("-22   /   7").toString());
	}

}

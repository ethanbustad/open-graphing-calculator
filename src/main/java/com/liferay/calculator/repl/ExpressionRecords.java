package com.liferay.calculator.repl;

import java.util.LinkedList;

public class ExpressionRecords {

	public ExpressionRecords() {
		_expressionRecords = new LinkedList<ExpressionRecord>();
	}

	public void add(String expression, String value) {
		_expressionRecords.add(new ExpressionRecord(expression, value));

		if (_expressionRecords.size() > MAX_SIZE) {
			_expressionRecords.removeFirst();
		}
	}

	public ExpressionRecord getLatest() {
		return _expressionRecords.peekLast();
	}

	private static final int MAX_SIZE = 20;

	private LinkedList<ExpressionRecord> _expressionRecords;

}
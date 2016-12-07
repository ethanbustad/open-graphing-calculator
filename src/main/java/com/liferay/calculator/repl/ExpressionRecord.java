package com.liferay.calculator.repl;

public class ExpressionRecord {

	public ExpressionRecord(String expression, String value) {
		_expression = expression;
		_value = value;
	}

	public String getExpression() {
		return _expression;
	}

	public String getValue() {
		return _value;
	}

	private String _expression;
	private String _value;

}
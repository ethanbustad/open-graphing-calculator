package com.liferay.calculator.repl;

import com.liferay.calculator.DataManager;
import com.liferay.calculator.SyntaxErrorException;

import com.udojava.evalex.Expression;
import com.udojava.evalex.Expression.ExpressionException;

import java.math.BigDecimal;

import javax.servlet.http.HttpServletRequest;

public class ReplDataManager implements DataManager {

	public ReplDataManager() {
		_expressionRecords = new ExpressionRecords();
	}

	public static ReplDataManager getInstance() {
		return _INSTANCE;
	}

	public ExpressionRecords getExpressionRecords() {
		return _expressionRecords;
	}

	@Override
	public void update(HttpServletRequest request) throws SyntaxErrorException {
		String expression = request.getParameter("expression");

		expression = processExpression(expression);

		try {
			BigDecimal result = new Expression(expression).eval();

			String output = result.toString();

			_expressionRecords.add(expression, output);
		}
		catch (ExpressionException ee) {
			throw new SyntaxErrorException(ee);
		}
	}

	private String processExpression(String expression) {
		if (expression.contains(_ANS)) {
			String replaceExpression = "";

			ExpressionRecord expressionRecord = _expressionRecords.getLatest();

			if (expressionRecord != null) {
				replaceExpression = expressionRecord.getValue();
			}

			expression = expression.replace(_ANS, replaceExpression);
		}

		return expression;
	}

	private static final String _ANS = "Ans";

	private static final ReplDataManager _INSTANCE = new ReplDataManager();

	private ExpressionRecords _expressionRecords;

}
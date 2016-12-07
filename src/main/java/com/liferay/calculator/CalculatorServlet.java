package com.liferay.calculator;

import com.liferay.calculator.ExpressionRecord;
import com.liferay.calculator.ExpressionRecords;

import com.udojava.evalex.Expression;
import com.udojava.evalex.Expression.ExpressionException;

import java.io.IOException;
import java.io.PrintWriter;

import java.math.BigDecimal;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletException;

public class CalculatorServlet extends HttpServlet{

	public CalculatorServlet() {
		_expressionRecords = new ExpressionRecords();
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
		throws IOException {

		PrintWriter out = response.getWriter();

		String expression = request.getParameter("expression");

		expression = processExpression(expression);

		try {
			BigDecimal result = new Expression(expression).eval();

			output = result.toString();

			_expressionRecords.add(expression, output);

			out.print(output);
		}
		catch (Exception e) {
			out.print(e.getMessage());
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

	private ExpressionRecords _expressionRecords;

	private final String _ANS = "Ans";

}
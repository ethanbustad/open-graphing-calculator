package com.liferay.calculator;

import com.liferay.calculator.repl.ReplDataManager;
import com.liferay.calculator.repl.ReplView;

import java.io.IOException;
import java.io.PrintWriter;

import java.math.BigDecimal;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletException;

import org.json.JSONArray;
import org.json.JSONException;

public class CalculatorServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
		throws IOException {

		PrintWriter out = response.getWriter();

		DataManager dm = ReplDataManager.getInstance();

		try {
			dm.update(request);

			View view = new ReplView();

			out.print(view.getData());
		}
		catch (JSONException je) {
			je.printStackTrace();

			out.print(je.toString());
		}
		catch (SyntaxErrorException see) {
			see.printStackTrace();

			out.print(see.getMessage());
		}
	}

}
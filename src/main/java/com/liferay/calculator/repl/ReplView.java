package com.liferay.calculator.repl;

import com.liferay.calculator.View;

import javax.servlet.http.HttpServletRequest;

import org.json.JSONException;
import org.json.JSONObject;

public class ReplView implements View {

	@Override
	public JSONObject getData() throws JSONException {
		JSONObject jsonObject = new JSONObject();

		ReplDataManager rdm = ReplDataManager.getInstance();

		ExpressionRecords expressionRecords = rdm.getExpressionRecords();

		ExpressionRecord expressionRecord = expressionRecords.getLatest();

		jsonObject.put("output", expressionRecord.getValue());

		return jsonObject;
	}

}
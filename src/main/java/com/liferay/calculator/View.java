package com.liferay.calculator;

import org.json.JSONException;
import org.json.JSONObject;

public interface View {

	public JSONObject getData() throws JSONException;

}
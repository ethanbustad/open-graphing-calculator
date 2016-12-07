package com.liferay.calculator;

import javax.servlet.http.HttpServletRequest;

public interface DataManager {

	public void update(HttpServletRequest request) throws SyntaxErrorException;

}
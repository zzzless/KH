package com.kh.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MemberLoginFormService implements IService{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		return "member/MemberLoginForm";
	}
	
}

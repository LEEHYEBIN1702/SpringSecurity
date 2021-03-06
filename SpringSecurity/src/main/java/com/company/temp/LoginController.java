package com.company.temp;

import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.company.common.KakaoAPI;

@Controller
public class LoginController {
	@Autowired KakaoAPI kakaoAPI;
	
	@RequestMapping("/callback")
	public String callback(@RequestParam Map<String, Object> map,HttpSession session) {
		String code = (String)map.get("code");
		String access_token = kakaoAPI.getAccessToken(code);
		System.out.println("access_token"+":"+access_token);
		Map<String, Object> userInfo = kakaoAPI.getUserInfo(access_token);
		System.out.println("userInfo"+":"+userInfo);
		//토큰을 세션에 저장
		session.setAttribute("access_token", access_token);
		session.setAttribute("loginid", userInfo.get("email"));
		//DB 저장
		return "home";
	}
	
	@RequestMapping("/logout")
	public String logout(HttpSession session) {
	//
	String access_token = (String) session.getAttribute("access_token");
	kakaoAPI.kakaoLogout(access_token);
	session.invalidate();
	return "home";
	
	}
}

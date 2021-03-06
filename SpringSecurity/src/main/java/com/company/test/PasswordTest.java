package com.company.test;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordTest {
	public static void main(String[] args) {
		BCryptPasswordEncoder bcrypt = new BCryptPasswordEncoder();
		String pw = bcrypt.encode("1234"); //비밀번호 암호화
		System.out.println(pw);
		System.out.println(bcrypt.matches("1111", pw) ); //pw와 값이 같은지 매치
	}
}

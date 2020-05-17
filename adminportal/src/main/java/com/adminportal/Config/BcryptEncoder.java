package com.adminportal.Config;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class BcryptEncoder {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		int i = 0;
		while (i < 5) {
			String password = "password";
			BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
			String hashedPassword = passwordEncoder.encode(password);

			System.out.println(hashedPassword);
			i++;
		}
	}

}

package com.think.lightningtalk.domain;

import static java.lang.String.format;

import java.util.regex.Pattern;

public class User {
	
	public static final Integer MAX_EMAIL_LENGTH = 255;
	
	public static final Pattern EMAIL_PATTERN =
            Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
	
	private final String email;
	
	private String password;
	
	public User(final String email, final String password) {
		if (email == null) {
			throw new NullPointerException("Email should not be null");
		}
		if (email.length() > MAX_EMAIL_LENGTH) {
			throw new IllegalArgumentException(format("Email should be less than %d characters", MAX_EMAIL_LENGTH));
		}
		if (!EMAIL_PATTERN.matcher(email).matches()) {
			throw new IllegalArgumentException(format("Email should match %s", EMAIL_PATTERN.pattern()));
		}
		
		this.email = email;
		setPassword(password);
	}

	public String getEmail() {
		return email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(final String password) {
		if (password == null) {
			throw new NullPointerException("Password should not be null");
		}
		if (password.length() == 0) {
			throw new IllegalArgumentException("Password should not be empty");
		}
		
		this.password = password;
	}

}

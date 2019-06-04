package com.think.lightningtalk.domain;

import static java.lang.String.format;

import java.util.regex.Pattern;

public class User {
	
	public static final int MAX_EMAIL_LENGTH = 255;
	
	public static final String EMAIL_REGEX = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$";
	
	public static final Pattern EMAIL_PATTERN = Pattern.compile(EMAIL_REGEX, Pattern.CASE_INSENSITIVE);
	
	private String email;
	
	private String password;

	public String getEmail() {
		return email;
	}
	
	public final void setEmail(final String email) {
		if (email == null) {
			throw new NullPointerException("Email should not be null");
		}
		if (email.length() > MAX_EMAIL_LENGTH) {
			throw new IllegalArgumentException(format("Email should be less than %d characters", MAX_EMAIL_LENGTH));
		}
		if (!EMAIL_PATTERN.matcher(email).matches()) {
			throw new IllegalArgumentException(format("Email should match %s", EMAIL_REGEX));
		}
		
		this.email = email.toLowerCase();
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "User [email=" + email + "]";
	}

}

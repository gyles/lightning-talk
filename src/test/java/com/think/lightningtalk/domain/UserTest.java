package com.think.lightningtalk.domain;

import static com.think.lightningtalk.domain.User.MAX_EMAIL_LENGTH;

import java.util.Arrays;

import org.junit.Test;

public class UserTest {
	
	@Test(expected = NullPointerException.class)
	public void testThatEmailIsRequired() {
		new User(null, "password");
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testThatEmailCannotExceedMaxLimit() {
		char[] chars = new char[MAX_EMAIL_LENGTH + 1];
		Arrays.fill(chars, '*');
		new User(new String(chars), "password");
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testThatEmailCannotBeInvalid() {
		new User("user@localhost", "password");
	}
	
	@Test(expected = NullPointerException.class)
	public void testThatPasswordIsRequired() {
		new User("user@localhost.local", null);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testThatPasswordIsNotEmpty() {
		new User("user@localhost.local", "");
	}
	
	@Test(expected = NullPointerException.class)
	public void testThatPasswordCannotBeSetToNull() {
		User user = new User("user@localhost.local", "password");
		user.setPassword(null);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testThatPasswordCannotBeSetToEmpty() {
		User user = new User("user@localhost.local", "password");
		user.setPassword("");
	}

}

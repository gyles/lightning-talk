package com.think.lightningtalk.domain;

import static com.think.lightningtalk.domain.User.MAX_EMAIL_LENGTH;

import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;

public class UserTest {
	
	private User user;
	
	@Before
	public void prepareUser() {
		user = new User();
	}
	
	@Test(expected = NullPointerException.class)
	public void testThatEmailCannotBeSetToNull() {
		user.setEmail(null);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testThatEmailCannotBeEmpty() {
		user.setEmail("");
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testThatEmailCannotExceedMaxLimit() {
		char[] chars = new char[MAX_EMAIL_LENGTH + 1];
		Arrays.fill(chars, '*');
		user.setEmail(new String(chars));
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testThatEmailCannotBeInvalid() {
		user.setEmail("user@localhost");
	}
	
	@Test(expected = NullPointerException.class)
	public void testThatPasswordCannotBeSetToNull() {
		user.setPassword(null);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testThatPasswordCannotBeSetToEmpty() {
		user.setPassword("");
	}

}

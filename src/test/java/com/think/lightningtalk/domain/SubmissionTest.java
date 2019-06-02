package com.think.lightningtalk.domain;

import static com.think.lightningtalk.domain.Submission.MAX_DESCRIPTION_LENGTH;
import static com.think.lightningtalk.domain.Submission.MAX_TOPIC_LENGTH;
import static java.time.Instant.now;

import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;

public class SubmissionTest {
	
	private User user;
	
	@Before
	public void prepareDependencies() {
		user = new User("user@localhost.local", "password");
	}
	
	@Test(expected = NullPointerException.class)
	public void testThatTopicIsRequired() {
		new Submission(null, "Description", now(), user);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testThatTopicIsNotEmpty() {
		new Submission("", "Description", now(), user);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testThatTopicCannotExceedMaxLimit() {
		char[] chars = new char[MAX_TOPIC_LENGTH + 1];
		Arrays.fill(chars, '*');
		new Submission(new String(chars), "Description", now(), user);
	}
	
	@Test(expected = NullPointerException.class)
	public void testThatTopicCannotBeSetToNull() {
		Submission submission = new Submission("Topic", "Description", now(), user);
		submission.setTopic(null);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testThatTopicCannotBeSetToEmpty() {
		Submission submission = new Submission("Topic", "Description", now(), user);
		submission.setTopic("");
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testThatTopicCannotBeSetToMaxLimit() {
		Submission submission = new Submission("Topic", "Description", now(), user);
		
		char[] chars = new char[MAX_TOPIC_LENGTH + 1];
		Arrays.fill(chars, '*');
		submission.setTopic(new String(chars));
	}
		
	@Test(expected = NullPointerException.class)
	public void testThatDescriptionIsRequired() {
		new Submission("Topic", null, now(), user);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testThatDescriptionIsNotEmpty() {
		new Submission("Topic", "", now(), user);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testThatDescriptionCannotExceedMaxLimit() {
		char[] chars = new char[MAX_DESCRIPTION_LENGTH + 1];
		Arrays.fill(chars, '*');
		new Submission("Topic", new String(chars), now(), user);
	}
	
	@Test(expected = NullPointerException.class)
	public void testThatDescriptionCannotBeSetToNull() {
		Submission submission = new Submission("Topic", "Description", now(), user);
		submission.setDescription(null);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testThatDescriptionCannotBeSetToEmpty() {
		Submission submission = new Submission("Topic", "Description", now(), user);
		submission.setDescription("");
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testThatDescriptionCannotBeSetToMaxLimit() {
		Submission submission = new Submission("Topic", "Description", now(), user);
		
		char[] chars = new char[MAX_DESCRIPTION_LENGTH + 1];
		Arrays.fill(chars, '*');
		submission.setDescription(new String(chars));
	}
	
	@Test(expected = NullPointerException.class)
	public void testThatDateIsRequired() {
		new Submission("Topic", "Description", null, user);
	}
	
	@Test(expected = NullPointerException.class)
	public void testThatUserIsRequired() {
		new Submission("Topic", "Description", now(), null);
	}
	
	public void testThatASuccessfulSubmissionCanBeMade() {
		new Submission("Topic", "Description", now(), user);
	}

}

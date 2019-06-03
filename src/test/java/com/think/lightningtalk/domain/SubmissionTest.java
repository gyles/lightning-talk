package com.think.lightningtalk.domain;

import static com.think.lightningtalk.domain.Submission.MAX_DESCRIPTION_LENGTH;
import static com.think.lightningtalk.domain.Submission.MAX_TOPIC_LENGTH;

import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;

public class SubmissionTest {
	
	private Submission submission;
	
	@Before
	public void prepareSubmission() {
		submission = new Submission();
	}
	
	@Test(expected = NullPointerException.class)
	public void testThatTopicCannotBeSetToNull() {
		submission.setTopic(null);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testThatTopicIsNotEmpty() {
		submission.setTopic("");
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testThatTopicCannotExceedMaxLimit() {
		char[] chars = new char[MAX_TOPIC_LENGTH + 1];
		Arrays.fill(chars, '*');
		submission.setTopic(new String(chars));
	}
		
	@Test(expected = NullPointerException.class)
	public void testThatDescriptionCannotBeSetToNull() {
		submission.setDescription(null);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testThatDescriptionIsNotEmpty() {
		submission.setDescription("");
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testThatDescriptionCannotExceedMaxLimit() {
		char[] chars = new char[MAX_DESCRIPTION_LENGTH + 1];
		Arrays.fill(chars, '*');
		submission.setDescription(new String(chars));
	}
	
	@Test(expected = NullPointerException.class)
	public void testThatDateIsRequired() {
		submission.setDate(null);
	}
	
	@Test(expected = NullPointerException.class)
	public void testThatEmailIsRequired() {
		submission.setEmail(null);
	}
}

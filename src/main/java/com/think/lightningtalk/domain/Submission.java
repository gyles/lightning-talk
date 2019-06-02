package com.think.lightningtalk.domain;

import static java.lang.String.format;

import java.time.Instant;

public class Submission {
	
	public static final Integer MAX_TOPIC_LENGTH = 80;
	
	public static final Integer MAX_DESCRIPTION_LENGTH = 120;
	
	private final User user;
	
	private String topic;
	
	private String description;
	
	private Instant date;
	
	private String ipaddress;
	
	private String hostname;
	
	private String osname;
	
	private String useragent;

	public Submission(final String topic, final String description, 
			final Instant date, final User user) {
		if (user == null) {
			throw new NullPointerException("User should not be null");
		}
		
		this.user = user;
		setTopic(topic);
		setDescription(description);
		setDate(date);
	}
	
	public User getUser() {
		return user;
	}

	public String getTopic() {
		return topic;
	}

	public void setTopic(final String topic) {
		if (topic == null) {
			throw new NullPointerException("Topic should not be null");
		}
		if (topic.length() == 0) {
			throw new IllegalArgumentException("Topic should not be empty");
		}
		if (topic.length() > MAX_TOPIC_LENGTH) {
			throw new IllegalArgumentException(format("Topic should be less than %d characters", MAX_TOPIC_LENGTH));
		}
		
		this.topic = topic;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(final String description) {
		if (description == null) {
			throw new NullPointerException("Description should not be null");
		}
		if (description.length() == 0) {
			throw new IllegalArgumentException("Description should not be empty");
		}
		if (description.length() > MAX_DESCRIPTION_LENGTH) {
			throw new IllegalArgumentException(format("Description should be less than %d characters", MAX_DESCRIPTION_LENGTH));
		}
		
		this.description = description;
	}
	
	public Instant getDate() {
		return date;
	}

	public void setDate(Instant date) {
		if (date == null) {
			throw new NullPointerException("Date should not be null");
		}
		this.date = date;
	}

	public String getIpaddress() {
		return ipaddress;
	}

	public void setIpaddress(final String ipaddress) {
		this.ipaddress = ipaddress;
	}

	public String getHostname() {
		return hostname;
	}

	public void setHostname(final String hostname) {
		this.hostname = hostname;
	}

	public String getOsname() {
		return osname;
	}

	public void setOsname(final String osname) {
		this.osname = osname;
	}

	public String getUseragent() {
		return useragent;
	}

	public void setUseragent(final String useragent) {
		this.useragent = useragent;
	}

}

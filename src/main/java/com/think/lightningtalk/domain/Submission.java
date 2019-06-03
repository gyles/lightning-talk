package com.think.lightningtalk.domain;

import static com.think.lightningtalk.domain.User.EMAIL_REGEX;
import static com.think.lightningtalk.domain.User.EMAIL_PATTERN;
import static com.think.lightningtalk.domain.User.MAX_EMAIL_LENGTH;
import static java.lang.String.format;

import java.io.Serializable;
import java.time.Instant;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class Submission implements Serializable {
	
	private static final long serialVersionUID = 1L;

	public static final int MAX_TOPIC_LENGTH = 80;
	
	public static final int MAX_DESCRIPTION_LENGTH = 120;
	
	@NotNull
	@Size(max = MAX_EMAIL_LENGTH)
	@Pattern(regexp = EMAIL_REGEX)
	private String email;
	
	@NotNull
	@Size(max = MAX_TOPIC_LENGTH)
	private String topic;
	
	@NotNull
	@Size(max = MAX_DESCRIPTION_LENGTH)
	private String description;
	
	@NotNull
	private Instant date;
	
	private String ipaddress;
	
	private String hostname;
	
	private String osname;
	
	private String useragent;
	
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
			throw new IllegalArgumentException(format("Email should match %s", EMAIL_PATTERN.pattern()));
		}
		if (this.email != null) {
			throw new NullPointerException("Email cannot be changed");
		}
		
		this.email = email.toLowerCase();
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((topic == null) ? 0 : topic.hashCode());
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
		Submission other = (Submission) obj;
		if (topic == null) {
			if (other.topic != null)
				return false;
		} else if (!topic.equals(other.topic))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Submission [email=" + email + ", topic=" + topic + ", description=" + description + ", date=" + date
				+ ", ipaddress=" + ipaddress + ", hostname=" + hostname + ", osname=" + osname + ", useragent="
				+ useragent + "]";
	}

}

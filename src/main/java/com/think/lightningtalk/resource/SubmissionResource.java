package com.think.lightningtalk.resource;

import static com.think.lightningtalk.domain.Submission.MAX_TOPIC_LENGTH;
import static com.think.lightningtalk.domain.Submission.MAX_DESCRIPTION_LENGTH;
import static com.think.lightningtalk.domain.User.MAX_EMAIL_LENGTH;

import java.io.Serializable;
import java.time.Instant;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class SubmissionResource implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@NotNull
	@Size(max = MAX_TOPIC_LENGTH)
	private String topic;
	
	@NotNull
	@Size(max = MAX_DESCRIPTION_LENGTH)
	private String description;
	
	@NotNull
	private Instant date;
	
	@NotNull
	@Size(max = MAX_EMAIL_LENGTH)
	private String email;
	
	private String ipaddress;
	
	private String hostname;
	
	private String osname;
	
	private String useragent;

	public String getTopic() {
		return topic;
	}

	public void setTopic(String topic) {
		this.topic = topic;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Instant getDate() {
		return date;
	}

	public void setDate(Instant date) {
		this.date = date;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getIpaddress() {
		return ipaddress;
	}

	public void setIpaddress(String ipaddress) {
		this.ipaddress = ipaddress;
	}

	public String getHostname() {
		return hostname;
	}

	public void setHostname(String hostname) {
		this.hostname = hostname;
	}

	public String getOsname() {
		return osname;
	}

	public void setOsname(String osname) {
		this.osname = osname;
	}

	public String getUseragent() {
		return useragent;
	}

	public void setUseragent(String useragent) {
		this.useragent = useragent;
	}
	
	

}

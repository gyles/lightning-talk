package com.think.lightningtalk.util;

import com.think.lightningtalk.domain.Submission;
import com.think.lightningtalk.entity.SubmissionEntity;

public final class SubmissionMapper {
	
	private SubmissionMapper() {}
	
	public static final SubmissionEntity toEntity(final Submission submission) {
		SubmissionEntity entity = null;
		if (submission != null) {
			entity = new SubmissionEntity();
			entity.setTopic(submission.getTopic());
			entity.setDescription(submission.getDescription());
			entity.setDate(submission.getDate());
			entity.setIpaddress(submission.getIpaddress());
			entity.setHostname(submission.getHostname());
			entity.setOsname(submission.getOsname());
			entity.setUseragent(submission.getUseragent());
		}
		
		return entity;
	}
	
	public static final Submission fromEntity(final SubmissionEntity entity) {
		Submission submission = null;
		if (entity != null) {
			submission = new Submission();
			submission.setTopic(entity.getTopic());
			submission.setDescription(entity.getDescription());
			submission.setEmail(entity.getUser().getEmail());
			submission.setDate(entity.getDate());
			submission.setIpaddress(entity.getIpaddress());
			submission.setHostname(entity.getHostname());
			submission.setOsname(entity.getOsname());
			submission.setUseragent(entity.getUseragent());
		}
		
		return submission;
	}

}

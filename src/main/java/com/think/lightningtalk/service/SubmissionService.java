package com.think.lightningtalk.service;

import java.time.Instant;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.think.lightningtalk.domain.Submission;

public interface SubmissionService {
	
	Long create(Submission submission);
	
	Long update(Long id, Submission submission);

    Page<Submission> findAll(Pageable pageable);
    
    Page<Submission> findAllByDate(Instant date, Pageable pageable);

    Optional<Submission> findOne(Long id);

    void delete(Long id);

}

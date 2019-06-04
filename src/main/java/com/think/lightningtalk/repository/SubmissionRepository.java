package com.think.lightningtalk.repository;

import java.time.Instant;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.think.lightningtalk.entity.SubmissionEntity;

@Repository
public interface SubmissionRepository extends JpaRepository<SubmissionEntity, Long> {
	
	@Query("select submission from SubmissionEntity submission where submission.user.email = ?1")
	Page<SubmissionEntity> findByUser(String email, Pageable pageable);
	
	@Query("select submission from SubmissionEntity submission where submission.date = (select min(submission.date) from SubmissionEntity submission where submission.date >= CURRENT_DATE)")
    Page<SubmissionEntity> findUpcoming(Pageable pageable);
	
	@Query("select submission from SubmissionEntity submission where submission.date = ?1")
    Page<SubmissionEntity> findAllByDate(Instant date, Pageable pageable);

}

package com.think.lightningtalk.service.impl;

import static com.think.lightningtalk.util.SubmissionMapper.toEntity;
import static java.util.stream.Collectors.toList;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.think.lightningtalk.domain.Submission;
import com.think.lightningtalk.entity.SubmissionEntity;
import com.think.lightningtalk.repository.SubmissionRepository;
import com.think.lightningtalk.repository.UserRepository;
import com.think.lightningtalk.service.SubmissionService;
import com.think.lightningtalk.util.SubmissionMapper;

@Service
@Transactional
public class SubmissionServiceImpl implements SubmissionService {
	
	private static final Logger LOG = LoggerFactory.getLogger(SubmissionServiceImpl.class);

    private final SubmissionRepository submissionRepository;
    
    private final UserRepository userRepository;

    public SubmissionServiceImpl(SubmissionRepository submissionRepository,
    		UserRepository userRepository) {
        this.submissionRepository = submissionRepository;
        this.userRepository = userRepository;
    }
	
    @Override
    public Long create(Submission submission) {
    	LOG.debug("Request to create Submission : {}", submission);
    	
    	SubmissionEntity entity = toEntity(submission);
    	entity.setUser(userRepository.findOneByEmailIgnoreCase(submission.getEmail()).orElse(null));
    	
        return submissionRepository.save(entity).getId();
    }
    
    @Override
    public Long update(Long id, Submission submission) {
    	LOG.debug("Request to update Submission : {} {}", id, submission);
    	
    	SubmissionEntity entity = toEntity(submission);
    	entity.setId(id);
    	entity.setUser(userRepository.findOneByEmailIgnoreCase(submission.getEmail()).orElse(null));
    	
        return submissionRepository.save(entity).getId();
    }

    @Override
    @Transactional(readOnly = true)
	public Page<Submission> findAll(Pageable pageable) {
		LOG.debug("Request to get all Submissions");
		
		Page<SubmissionEntity> result = submissionRepository.findAll(pageable);
		return new PageImpl<>(result.stream().map(SubmissionMapper::fromEntity).collect(toList()), 
				pageable, result.getSize());
	}
    
    @Override
    @Transactional(readOnly = true)
	public Page<Submission> findUpcoming(Pageable pageable) {
		LOG.debug("Request to get upcoming Submissions");
		
		Page<SubmissionEntity> result = submissionRepository.findUpcoming(pageable);
		return new PageImpl<>(result.stream().map(SubmissionMapper::fromEntity).collect(toList()), 
				pageable, result.getSize());
	}
    
    @Override
    @Transactional(readOnly = true)
    public Page<Submission> findAllByDate(Instant instant, Pageable pageable) {
    	LOG.debug("Request to get all Submissions for date : {}", instant);
    	
    	Page<SubmissionEntity> result = submissionRepository.findAllByDate(instant, pageable);
    	return new PageImpl<>(result.stream().map(SubmissionMapper::fromEntity).collect(toList()), 
				pageable, result.getSize());
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Submission> findOne(Long id) {
    	LOG.debug("Request to get Submission : {}", id);
    	
    	Optional<SubmissionEntity> result = submissionRepository.findById(id);
        return Optional.ofNullable(SubmissionMapper.fromEntity(result.orElse(null)));
    }

    @Override
    public void delete(Long id) {
    	LOG.debug("Request to delete Submission : {}", id);
    	
        submissionRepository.deleteById(id);
    }

	@Override
	public LocalDateTime nextSession() {
		LocalDateTime current = LocalDateTime.now();
		LocalDateTime next = LocalDateTime.now();
		if (current.getMonthValue() % 2 == 0) {
			next = next.plusMonths(2).withDayOfMonth(1);
		} else {
			next = next.plusMonths(1).withDayOfMonth(1);
		}
		int dayOfWeek = next.getDayOfWeek().getValue();
		
		return next.plusDays(dayOfWeek > 2 ? 9 - dayOfWeek : 2 - dayOfWeek).withHour(13).withMinute(30);
	}

}

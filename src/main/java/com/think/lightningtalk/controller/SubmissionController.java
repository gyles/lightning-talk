package com.think.lightningtalk.controller;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.think.lightningtalk.domain.Submission;
import com.think.lightningtalk.service.SubmissionService;

@RestController
@RequestMapping("/api")
public class SubmissionController {
	
	private static final Logger LOG = LoggerFactory.getLogger(SubmissionController.class);
	
	private final SubmissionService submissionService;

    public SubmissionController(SubmissionService submissionService) {
        this.submissionService = submissionService;
    }
    
    @PostMapping("/submissions")
    public ResponseEntity<Submission> createSubmission(@Valid @RequestBody Submission submission) throws URISyntaxException {
    	LOG.debug("REST request to save Submission : {}", submission);
    	
        Long id = submissionService.create(submission);
        return ResponseEntity.created(new URI("/api/submissions/" + id)).body(submission);
    }
    
    @PutMapping("/submissions/{id}")
    public ResponseEntity<Submission> updateSubmission(@PathVariable Long id, @Valid @RequestBody Submission submission) throws URISyntaxException {
    	LOG.debug("REST request to update Submission : {}", submission);
    	
    	submissionService.update(id, submission);
        return ResponseEntity.ok().body(submission);
    }
    
    @GetMapping("/submissions")
    public ResponseEntity<List<Submission>> getAllSubmissions(Pageable pageable, UriComponentsBuilder uriBuilder) {
    	LOG.debug("REST request to get a page of Submissions");
    	
        Page<Submission> page = submissionService.findAll(pageable);
        return ResponseEntity.ok().body(page.getContent());
    }
    
    @GetMapping("/submissions/{id}")
    public ResponseEntity<Submission> getSubmission(@PathVariable Long id) {
    	LOG.debug("REST request to get Submission : {}", id);
    	
        Optional<Submission> submission = submissionService.findOne(id);
        return submission.map(response -> ResponseEntity.ok().body(response)).orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
    
    @DeleteMapping("/submissions/{id}")
    public ResponseEntity<Void> deleteSubmission(@PathVariable Long id) {
    	LOG.debug("REST request to delete Submission : {}", id);
    	
        submissionService.delete(id);
        return ResponseEntity.noContent().build();
    }

}

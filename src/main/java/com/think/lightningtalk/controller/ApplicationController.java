package com.think.lightningtalk.controller;

import java.time.LocalDateTime;
import java.time.ZoneId;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.think.lightningtalk.domain.Submission;
import com.think.lightningtalk.resource.SubmissionResource;
import com.think.lightningtalk.service.SubmissionService;

@Controller
public class ApplicationController {
	
	@Autowired
	private SubmissionService submissionService;
	
	@GetMapping("/")
    public String getUpcomingSubmissions(Model model) {
    	model.addAttribute("upcoming", submissionService.findUpcoming(Pageable.unpaged()).getContent());
        return "/home.html";
    }
	
	@GetMapping("/submission")
    public String getSubmission(Model model) {
		SubmissionResource resource = new SubmissionResource();
		resource.setEmail(SecurityContextHolder.getContext().getAuthentication().getName());
		resource.setDate(submissionService.nextSession());
        model.addAttribute("submission", resource);
        return "/submission.html";
    }
	
	@PostMapping("/submission")
    public String postSubmission(@Valid @ModelAttribute SubmissionResource resource) {
		Submission submission = new Submission();
		submission.setTopic(resource.getTopic());
		submission.setDescription(resource.getDescription());
		submission.setEmail(SecurityContextHolder.getContext().getAuthentication().getName());
		submission.setDate(resource.getDate().atZone(ZoneId.of("UTC")).toInstant());
		submissionService.create(submission);
		
        return "redirect:/";
    }

}

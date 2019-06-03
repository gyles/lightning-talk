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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.think.lightningtalk.domain.User;
import com.think.lightningtalk.service.UserService;

@RestController
@RequestMapping("/api")
public class UserController {
	
private static final Logger LOG = LoggerFactory.getLogger(UserController.class);
	
	private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }
    
    @PostMapping("/users")
    public ResponseEntity<User> createUser(@Valid @RequestBody User user) throws URISyntaxException {
    	LOG.debug("REST request to save User : {}", user);
    	
        Long id = userService.create(user);
        return ResponseEntity.created(new URI("/api/users/" + id)).body(user);
    }
    
    @PutMapping("/users/{id}")
    public ResponseEntity<User> updateUser(@PathVariable Long id, @Valid @RequestBody User user) throws URISyntaxException {
    	LOG.debug("REST request to update User : {}", user);
    	
    	userService.update(id, user);
        return ResponseEntity.ok().body(user);
    }
    
    @GetMapping("/users")
    public ResponseEntity<List<User>> getAllUsers(Pageable pageable, UriComponentsBuilder uriBuilder) {
    	LOG.debug("REST request to get a page of Users");
    	
        Page<User> page = userService.findAll(pageable);
        return ResponseEntity.ok().body(page.getContent());
    }
    
    @GetMapping("/users/{id}")
    public ResponseEntity<User> getUser(@PathVariable Long id) {
    	LOG.debug("REST request to get User : {}", id);
    	
        Optional<User> user = userService.findOne(id);
        return user.map(response -> ResponseEntity.ok().body(response)).orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
    
    @DeleteMapping("/users/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
    	LOG.debug("REST request to delete User : {}", id);
    	
    	userService.delete(id);
        return ResponseEntity.noContent().build();
    }

}

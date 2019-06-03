package com.think.lightningtalk.service;

import java.time.Instant;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.think.lightningtalk.domain.User;

public interface UserService {
	
	Long create(User user);
	
	Long update(Long id, User user);

    Page<User> findAll(Pageable pageable);

    Optional<User> findOne(Long id);

    void delete(Long id);

}

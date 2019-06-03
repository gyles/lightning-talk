package com.think.lightningtalk.service.impl;

import static com.think.lightningtalk.util.UserMapper.toEntity;
import static java.util.stream.Collectors.toList;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.think.lightningtalk.domain.User;
import com.think.lightningtalk.entity.UserEntity;
import com.think.lightningtalk.repository.UserRepository;
import com.think.lightningtalk.service.UserService;
import com.think.lightningtalk.util.UserMapper;

@Service
@Transactional
public class UserServiceImpl implements UserService {
	
	private static final Logger LOG = LoggerFactory.getLogger(UserServiceImpl.class);

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

	@Override
	public Long create(User user) {
    	LOG.debug("Request to create User : {}", user);
    	
        return userRepository.save(toEntity(user)).getId();
    }

	@Override
	public Long update(Long id, User user) {
		LOG.debug("Request to update User : {} {}", id, user);
    	
        return userRepository.save(toEntity(id, user)).getId();
	}

	@Override
	public Page<User> findAll(Pageable pageable) {
		LOG.debug("Request to get all Users");
		
		Page<UserEntity> result = userRepository.findAll(pageable);
		return new PageImpl<>(result.stream().map(UserMapper::fromEntity).collect(toList()), 
				pageable, result.getSize());
	}

	@Override
	public Optional<User> findOne(Long id) {
    	LOG.debug("Request to get User : {}", id);
    	
    	Optional<UserEntity> result = userRepository.findById(id);
        return Optional.ofNullable(UserMapper.fromEntity(result.orElse(null)));
    }

	@Override
	public void delete(Long id) {
		LOG.debug("Request to delete User : {}", id);
    	
		userRepository.deleteById(id);	
	}

}

package com.think.lightningtalk.util;

import com.think.lightningtalk.domain.User;
import com.think.lightningtalk.entity.UserEntity;

public final class UserMapper {
	
	private UserMapper() {}
	
	public static final UserEntity toEntity(final Long id, final User user) {
		UserEntity entity = null;
		if (user != null) {
			entity = toEntity(user);
			entity.setId(id);
		}
		
		return entity;
	}
	
	public static final UserEntity toEntity(final User user) {
		UserEntity entity = null;
		if (user != null) {
			entity = new UserEntity();
			entity.setEmail(user.getEmail());
			entity.setPassword(user.getPassword());
		}
		
		return entity;
	}
	
	public static final User fromEntity(final UserEntity entity) {
		User user = null;
		if (entity != null) {
			user = new User();
			user.setEmail(entity.getEmail());
			user.setPassword(entity.getPassword());
		}
		
		return user;
	}

}

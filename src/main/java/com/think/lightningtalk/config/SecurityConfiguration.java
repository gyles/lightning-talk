package com.think.lightningtalk.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

import com.think.lightningtalk.service.UserService;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private ApplicationProperties properties;
	
	@Autowired
	private UserService userService;
	
	@Override
    protected void configure(HttpSecurity http) throws Exception {
        http
	        .csrf()
	        .disable()
            .authorizeRequests()
                .antMatchers("/", "/home", "/js/**", "/css/**").permitAll()
                .anyRequest().authenticated()
                .and()
            .formLogin()
                .loginPage("/login")
                .permitAll()
                .and()
            .logout()
                .permitAll();
    }

    @Bean
    @Override
    public UserDetailsService userDetailsService() {
    	List<UserDetails> users = new ArrayList<>();
    	for (ApplicationProperties.User user : properties.getUsers()) {
    		users.add(User.withUsername(user.getName())
    				.password(user.getPassword())
    				.roles(user.getRoles().toArray(new String[user.getRoles().size()]))
                    .build());
			com.think.lightningtalk.domain.User domainUser = 
					new com.think.lightningtalk.domain.User();
			domainUser.setEmail(user.getName());
			domainUser.setPassword(user.getPassword());
    		userService.create(domainUser);
    	}
        return new InMemoryUserDetailsManager(users);
    }

}

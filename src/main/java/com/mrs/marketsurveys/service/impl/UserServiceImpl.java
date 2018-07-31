package com.mrs.marketsurveys.service.impl;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.mrs.marketsurveys.domain.User;
import com.mrs.marketsurveys.repository.UserRepository;
import com.mrs.marketsurveys.service.UserService;

@Service(value = "userService")
public class UserServiceImpl implements UserDetailsService, UserService {
	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String name)
	        throws UsernameNotFoundException {
		User user = userRepository.findByName(name);
		if (user == null) {
			throw new UsernameNotFoundException(
			        "Invalid username or password.");
		}
		return new org.springframework.security.core.userdetails.User(
		        user.getName(), user.getPassword(),
		        getAuthority(user));
	}

	private Set<SimpleGrantedAuthority> getAuthority(User user) {
		Set<SimpleGrantedAuthority> authorities = new HashSet<>();
		user.getRoles().forEach(role -> {
			authorities
			        .add(new SimpleGrantedAuthority("ROLE_" + role.getName()));
		});
		return authorities;
	}

	@Override
	public User findByName(String name) {
		return userRepository.findByName(name);
	}

	@Override
	public User findById(Long id) {
		return userRepository.findById(id).get();
	}

}

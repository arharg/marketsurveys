package com.mrs.marketsurveys.utils;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;

import com.mrs.marketsurveys.domain.User;
import com.mrs.marketsurveys.service.UserService;

public class UserPrincipalRetriever {

	public static User retrieveUser(UserService userService,
	        Authentication auth) {
		return userService
		        .findByName(((UserDetails) auth.getPrincipal()).getUsername());
	}
}

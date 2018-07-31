package com.mrs.marketsurveys.service;

import com.mrs.marketsurveys.domain.User;

public interface UserService {

	User findByName(String name);

	User findById(Long id);
}

package com.mrs.marketsurveys.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.mrs.marketsurveys.domain.Party;
import com.mrs.marketsurveys.domain.User;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
	User findByName(String name);
}

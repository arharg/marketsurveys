package com.mrs.marketsurveys.domain;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@DiscriminatorValue("user")
public @Getter @Setter @NoArgsConstructor class User extends Party {
	@JsonIgnore
	private String password;

	@JsonIgnore
	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinTable(
	        name = "user_roles",
	        joinColumns = @JoinColumn(name = "party_id"),
	        inverseJoinColumns = @JoinColumn(name = "role_id"))
	private Set<Role> roles;

}

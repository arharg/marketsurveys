package com.mrs.marketsurveys.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "availability_condition")
public @Getter @Setter @NoArgsConstructor class Condition {

	@NotNull
	@Id
	@Enumerated(EnumType.STRING)
	@Column(nullable = false, unique = true)
	private ConditionAvailability id;

	private String description;

}

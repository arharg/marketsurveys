package com.mrs.marketsurveys.domain;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.mrs.marketsurveys.utils.IdGenerator;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@JsonInclude(value = JsonInclude.Include.NON_NULL)
@Entity
@Table(name = "request")
public @Getter @Setter @NoArgsConstructor class RequestForInformation {
	@Id
	@Column(name = "id", nullable = false, updatable = false)
	private String id = IdGenerator.createId();

	@NotNull
	@ManyToOne
	private Party requester;

	@NotNull
	@ManyToOne
	private Party provider;

	@ManyToOne
	private SubjectClassification subject;

	@ManyToOne
	private MarketSurvey surveyDescription;

	@NotNull
	@ElementCollection(targetClass = DistributionChannel.class)
	@JoinTable(name = "subscription_channel")
	@Enumerated(EnumType.STRING)
	private Set<DistributionChannel> subscriptionChannels = new HashSet<>();

	private String question;

	private String answer;

}

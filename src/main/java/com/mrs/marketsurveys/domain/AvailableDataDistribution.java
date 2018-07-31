package com.mrs.marketsurveys.domain;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.mrs.marketsurveys.utils.IdGenerator;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@JsonInclude(value = JsonInclude.Include.NON_EMPTY)
@Entity
@Table(name = "available_data_info")
public @Getter @Setter @NoArgsConstructor class AvailableDataDistribution {
	@Id
	@Column(name = "id", nullable = false, updatable = false)
	private String id = IdGenerator.createId();

	@Column(name = "message")
	private String message;

	@NotNull
	@ManyToOne
	private Party requester;

	@NotNull
	@ManyToOne
	private Party provider;

	@ManyToOne
	private RequestForInformation request;

	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
	@JoinTable(
	        name = "available_surveys",
	        joinColumns = @JoinColumn(name = "availableinfo_id"),
	        inverseJoinColumns = @JoinColumn(name = "survey_id"))
	private Set<MarketSurvey> surveys = new HashSet<>();

	@NotNull
	@ElementCollection(targetClass = DistributionChannel.class)
	@JoinTable(name = "available_distributionchannel")
	@Enumerated(EnumType.STRING)
	private Set<DistributionChannel> distributionChannels = new HashSet<>();

	public boolean addSurvey(MarketSurvey survey) {
		return surveys.add(survey);
	}

	public boolean addSurveys(Set<MarketSurvey> surveys) {
		return this.surveys.addAll(surveys);
	}

	public boolean removeSurvey(MarketSurvey survey) {
		return surveys.remove(survey);
	}

}

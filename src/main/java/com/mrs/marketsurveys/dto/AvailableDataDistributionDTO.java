package com.mrs.marketsurveys.dto;

import java.util.HashSet;
import java.util.Set;

import javax.validation.constraints.NotNull;

import com.mrs.marketsurveys.domain.DistributionChannel;
import com.mrs.marketsurveys.domain.Party;

import lombok.Data;

public @Data class AvailableDataDistributionDTO {

	private Party provider;

	private Set<Party> requesters;

	private Set<String> surveys = new HashSet<>();

	@NotNull
	private Set<DistributionChannel> distributionChannel;

	@NotNull
	private String message;

}

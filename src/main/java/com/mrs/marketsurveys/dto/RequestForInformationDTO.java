package com.mrs.marketsurveys.dto;

import java.util.Set;

import javax.validation.constraints.NotNull;

import com.mrs.marketsurveys.domain.DistributionChannel;
import com.mrs.marketsurveys.domain.Party;
import com.mrs.marketsurveys.domain.SubjectClassification;

import lombok.Data;

public @Data class RequestForInformationDTO {

	private Party requester;
	@NotNull
	private Set<Party> providers;

	private SubjectClassification subject;

	private String surveyDescription;

	private Set<DistributionChannel> subscriptionChannels;

	private String question;

	private String answer;

}

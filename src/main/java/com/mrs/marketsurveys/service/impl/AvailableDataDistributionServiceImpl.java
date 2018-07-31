package com.mrs.marketsurveys.service.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mrs.marketsurveys.domain.AvailableDataDistribution;
import com.mrs.marketsurveys.domain.MarketSurvey;
import com.mrs.marketsurveys.domain.Party;
import com.mrs.marketsurveys.domain.RequestForInformation;
import com.mrs.marketsurveys.dto.AvailableDataDistributionDTO;
import com.mrs.marketsurveys.repository.AvailableDataDistributionRepository;
import com.mrs.marketsurveys.service.AvailableDataDistributionService;
import com.mrs.marketsurveys.service.MarketSurveyService;

@Service(value = "AvailableDataDistributionService")
public class AvailableDataDistributionServiceImpl
        implements AvailableDataDistributionService {
	@Autowired
	private AvailableDataDistributionRepository dataDistributionRepository;
	@Autowired
	private MarketSurveyService surveyService;

	@Override
	public AvailableDataDistribution create(
	        AvailableDataDistribution dataDistribution) {

		return dataDistributionRepository.save(dataDistribution);
	}

	@Override
	public List<AvailableDataDistribution> createNewDistribution(
	        AvailableDataDistributionDTO dataDistributionRequest) {
		List<AvailableDataDistribution> dataDistributions = new ArrayList<>();

		Set<MarketSurvey> surveys = new HashSet<>(
		        surveyService
		                .findAllById(dataDistributionRequest.getSurveys()));

		for (Party requester : dataDistributionRequest.getRequesters()) {
			AvailableDataDistribution dataDistribution = new AvailableDataDistribution();
			dataDistribution.setRequester(requester);
			dataDistribution.setProvider(dataDistributionRequest.getProvider());
			dataDistribution.setSurveys(surveys);
			dataDistribution.setMessage(dataDistributionRequest.getMessage());
			dataDistribution.setDistributionChannels(
			        dataDistributionRequest.getDistributionChannel());

			dataDistributions.add(dataDistribution);
		}
		return dataDistributionRepository.saveAll(dataDistributions);
	}

	@Override
	public AvailableDataDistribution replyRequest(
	        RequestForInformation request,
	        AvailableDataDistributionDTO dataReply) {

		Set<MarketSurvey> surveys = new HashSet<>(
		        surveyService.findAllById(dataReply.getSurveys()));

		AvailableDataDistribution dataDistribution = new AvailableDataDistribution();
		dataDistribution.setRequester(request.getRequester());
		dataDistribution.setProvider(dataReply.getProvider());
		dataDistribution.setSurveys(surveys);
		dataDistribution.setMessage(dataReply.getMessage());
		dataDistribution
		        .setDistributionChannels(dataReply.getDistributionChannel());
		dataDistribution.setRequest(request);

		return dataDistributionRepository.save(dataDistribution);
	}

	@Override
	public AvailableDataDistribution findByRequest(
	        String requestId) {

		return dataDistributionRepository.findByRequestId(requestId);
	}

}

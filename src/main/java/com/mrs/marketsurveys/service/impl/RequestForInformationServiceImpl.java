package com.mrs.marketsurveys.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mrs.marketsurveys.domain.MarketSurvey;
import com.mrs.marketsurveys.domain.Party;
import com.mrs.marketsurveys.domain.RequestForInformation;
import com.mrs.marketsurveys.dto.RequestForInformationDTO;
import com.mrs.marketsurveys.repository.RequestForInformationRepository;
import com.mrs.marketsurveys.service.MarketSurveyService;
import com.mrs.marketsurveys.service.RequestForInformationService;

@Service(value = "RequestForInformationService")
public class RequestForInformationServiceImpl
        implements RequestForInformationService {

	@Autowired
	private RequestForInformationRepository requestRepository;
	@Autowired
	private MarketSurveyService surveyService;

	@Override
	public RequestForInformation findById(String id) {
		return requestRepository.findById(id)
		        .orElseThrow(() -> new EntityNotFoundException(
		                String.format("Request with ID %s not found!", id)));
	}

	@Override
	public RequestForInformation create(RequestForInformation request) {
		return requestRepository.save(request);
	}

	@Override
	public List<RequestForInformation> createAll(
	        RequestForInformationDTO requestDTO) {

		List<RequestForInformation> requests = new ArrayList<>();
		MarketSurvey surveyDescription = surveyService
		        .findById(requestDTO.getSurveyDescription());
		for (Party provider : requestDTO.getProviders()) {
			RequestForInformation request = new RequestForInformation();
			request.setProvider(provider);
			request.setRequester(requestDTO.getRequester());
			request.setSurveyDescription(surveyDescription);
			request.setSubject(requestDTO.getSubject());
			request.setQuestion(requestDTO.getQuestion());
			request.setAnswer(requestDTO.getAnswer());
			request.setSubscriptionChannels(
			        requestDTO.getSubscriptionChannels());
			requests.add(request);
		}
		return requestRepository.saveAll(requests);
	}

}

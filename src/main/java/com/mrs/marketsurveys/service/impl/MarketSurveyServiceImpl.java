package com.mrs.marketsurveys.service.impl;

import java.util.Collection;
import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mrs.marketsurveys.domain.MarketSurvey;
import com.mrs.marketsurveys.repository.MarketSurveyRepository;
import com.mrs.marketsurveys.service.MarketSurveyService;

@Service(value = "marketSurveyService")
public class MarketSurveyServiceImpl implements MarketSurveyService {
	@Autowired
	private MarketSurveyRepository surveyRepository;

	@Override
	public MarketSurvey findById(String id) {
		return surveyRepository.findById(id)
		        .orElseThrow(() -> new EntityNotFoundException(
		                String.format("Market survey with ID %s not found!",
		                        id)));
	}

	@Override
	public List<MarketSurvey> findAllById(Collection<String> ids) {
		return surveyRepository.findAllById(ids);
	}

	@Override
	public List<MarketSurvey> createAll(Collection<MarketSurvey> surveys) {

		return surveyRepository.saveAll(surveys);
	}

	@Override
	public List<MarketSurvey> getBySubjects(Collection<Integer> subjects) {

		return surveyRepository.findBySubjectsClassificationValueIn(subjects);
	}

}

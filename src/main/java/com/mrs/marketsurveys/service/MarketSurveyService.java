package com.mrs.marketsurveys.service;

import java.util.Collection;
import java.util.List;

import com.mrs.marketsurveys.domain.MarketSurvey;

public interface MarketSurveyService {
	MarketSurvey findById(String id);

	List<MarketSurvey> createAll(Collection<MarketSurvey> surveys);

	List<MarketSurvey> findAllById(Collection<String> ids);

	List<MarketSurvey> getBySubjects(Collection<Integer> subjects);
}

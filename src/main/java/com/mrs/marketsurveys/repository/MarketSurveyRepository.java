package com.mrs.marketsurveys.repository;

import java.util.Collection;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mrs.marketsurveys.domain.MarketSurvey;

public interface MarketSurveyRepository
        extends JpaRepository<MarketSurvey, String> {
	List<MarketSurvey> findBySubjectsClassificationValueIn(
	        Collection<Integer> subjects);
	// List<MarketSurvey> findByIdIn(Collection<String> ids);
}

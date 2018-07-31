package com.mrs.marketsurveys.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mrs.marketsurveys.domain.AvailableDataDistribution;

@Repository
public interface AvailableDataDistributionRepository
        extends JpaRepository<AvailableDataDistribution, String> {

	public AvailableDataDistribution findByRequestId(
	        String requestId);
}

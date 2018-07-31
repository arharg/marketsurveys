package com.mrs.marketsurveys.service;

import java.util.List;

import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;

import com.mrs.marketsurveys.domain.AvailableDataDistribution;
import com.mrs.marketsurveys.domain.RequestForInformation;
import com.mrs.marketsurveys.dto.AvailableDataDistributionDTO;

public interface AvailableDataDistributionService {
	public AvailableDataDistribution create(
	        AvailableDataDistribution dataDistribution);

	@PreAuthorize("#request.provider.name == principal.username")
	public AvailableDataDistribution replyRequest(
	        RequestForInformation request,
	        AvailableDataDistributionDTO dataReply);

	public List<AvailableDataDistribution> createNewDistribution(
	        AvailableDataDistributionDTO dataDistributionRequest);

	@PostAuthorize("returnObject.requester.name == principal.username || returnObject.provider.name == principal.username")
	public AvailableDataDistribution findByRequest(
	        String id);
}

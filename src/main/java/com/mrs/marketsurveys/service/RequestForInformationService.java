package com.mrs.marketsurveys.service;

import java.util.List;

import org.springframework.security.access.prepost.PostAuthorize;

import com.mrs.marketsurveys.domain.RequestForInformation;
import com.mrs.marketsurveys.dto.RequestForInformationDTO;

public interface RequestForInformationService {

	@PostAuthorize("returnObject.provider.name == principal.username || returnObject.requester.name == principal.username")
	public RequestForInformation findById(String id);

	public RequestForInformation create(RequestForInformation request);

	public List<RequestForInformation> createAll(
	        RequestForInformationDTO requestDTO);

}

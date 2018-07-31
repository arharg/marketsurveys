package com.mrs.marketsurveys.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.mrs.marketsurveys.domain.RequestForInformation;
import com.mrs.marketsurveys.domain.User;
import com.mrs.marketsurveys.dto.RequestForInformationDTO;
import com.mrs.marketsurveys.service.RequestForInformationService;
import com.mrs.marketsurveys.service.UserService;
import com.mrs.marketsurveys.utils.UserPrincipalRetriever;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping(value = "/api/requests")
@Api(value = "Requests for information API",
    description = "Endpoints for creating new requests for information on available data on Market Surveys")
public class RequestsForInformationController {

	@Autowired
	private RequestForInformationService requestService;
	@Autowired
	private UserService userService;

	@PreAuthorize("hasRole('REQUESTOR')")
	@RequestMapping(
	    method = RequestMethod.POST,
	    consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
	    produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ApiOperation(value = "Creates new request for information")
	@ApiResponses(value = {
	        @ApiResponse(code = 200, message = "Success",
	            response = RequestForInformation.class,
	            responseContainer = "List"),
	        @ApiResponse(code = 400, message = "Bad request"),
	        @ApiResponse(code = 401, message = "Unauthorized"),
	        @ApiResponse(code = 403, message = "Forbidden"),
	        @ApiResponse(code = 404, message = "Not Found"),
	        @ApiResponse(code = 500, message = "Failure") })
	public ResponseEntity<List<RequestForInformation>> createRequest(
	        @ApiParam("Request details for a new request to be created") @RequestBody @Valid RequestForInformationDTO requestDTO,
	        Authentication auth) {

		User requester = UserPrincipalRetriever.retrieveUser(userService, auth);
		requestDTO.setRequester(requester);
		List<RequestForInformation> requestsList = requestService
		        .createAll(requestDTO);

		return new ResponseEntity<>(requestsList, HttpStatus.OK);
	}

}

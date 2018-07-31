package com.mrs.marketsurveys.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.mrs.marketsurveys.domain.AvailableDataDistribution;
import com.mrs.marketsurveys.domain.Party;
import com.mrs.marketsurveys.domain.RequestForInformation;
import com.mrs.marketsurveys.dto.AvailableDataDistributionDTO;
import com.mrs.marketsurveys.service.AvailableDataDistributionService;
import com.mrs.marketsurveys.service.RequestForInformationService;
import com.mrs.marketsurveys.service.UserService;
import com.mrs.marketsurveys.utils.UserPrincipalRetriever;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping(value = "/api/datadistribution")
@Api(value = "Available Data Distribution API",
    description = "Endpoints for distributing information on available data on Market Surveys and replying to requests for information.")
public class AvailableDataDistributionController {

	@Autowired
	private AvailableDataDistributionService dataDistributionService;
	@Autowired
	private UserService userService;
	@Autowired
	private RequestForInformationService requestService;

	@PreAuthorize("hasRole('PROVIDER')")
	@RequestMapping(value = "create", method = RequestMethod.POST,
	    consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
	    produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ApiOperation(value = "Creates new information distribution")
	@ApiResponses(value = {
	        @ApiResponse(code = 200, message = "Success",
	            response = AvailableDataDistribution.class,
	            responseContainer = "List"),
	        @ApiResponse(code = 400, message = "Bad request"),
	        @ApiResponse(code = 401, message = "Unauthorized"),
	        @ApiResponse(code = 403, message = "Forbidden"),
	        @ApiResponse(code = 404, message = "Not Found"),
	        @ApiResponse(code = 500, message = "Failure") })
	public ResponseEntity<List<AvailableDataDistribution>> createNewDataDistribution(
	        @ApiParam("Description about the new information distribution, including target requestors and the related market surveys") @RequestBody @Valid AvailableDataDistributionDTO dataDistributionRequest,
	        Authentication auth) {

		Party provider = UserPrincipalRetriever.retrieveUser(userService, auth);
		dataDistributionRequest.setProvider(provider);
		List<AvailableDataDistribution> distributionList = dataDistributionService
		        .createNewDistribution(dataDistributionRequest);

		return new ResponseEntity<>(distributionList,
		        HttpStatus.OK);
	}

	@PreAuthorize("hasRole('PROVIDER')")
	@RequestMapping(value = "reply/{requestId}", method = RequestMethod.POST,
	    consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
	    produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ApiOperation(
	    value = "Creates new reply to a request of information, including available market surveys and/or custom message")
	@ApiResponses(value = {
	        @ApiResponse(code = 200, message = "Success",
	            response = AvailableDataDistribution.class),
	        @ApiResponse(code = 400, message = "Bad request"),
	        @ApiResponse(code = 401, message = "Unauthorized"),
	        @ApiResponse(code = 403, message = "Forbidden"),
	        @ApiResponse(code = 404, message = "Not Found"),
	        @ApiResponse(code = 500, message = "Failure") })
	public ResponseEntity<AvailableDataDistribution> reply(
	        @ApiParam("Identificator of the request") @PathVariable String requestId,
	        @ApiParam("The related avalaible information") @Valid @RequestBody AvailableDataDistributionDTO dataDistributionRequest,
	        Authentication auth) {

		RequestForInformation request = requestService
		        .findById(requestId);
		Party provider = UserPrincipalRetriever.retrieveUser(userService, auth);
		dataDistributionRequest.setProvider(provider);
		AvailableDataDistribution reply = dataDistributionService
		        .replyRequest(request, dataDistributionRequest);

		return new ResponseEntity<>(reply, HttpStatus.OK);
	}

	@RequestMapping(value = "reply/{requestId}", method = RequestMethod.GET,
	    produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ApiOperation(value = "If exists, returns the reply to a specific request")
	@ApiResponses(value = {
	        @ApiResponse(code = 200, message = "Success",
	            response = AvailableDataDistribution.class),
	        @ApiResponse(code = 400, message = "Bad request"),
	        @ApiResponse(code = 401, message = "Unauthorized"),
	        @ApiResponse(code = 403, message = "Forbidden"),
	        @ApiResponse(code = 404, message = "Not Found"),
	        @ApiResponse(code = 500, message = "Failure") })
	public ResponseEntity<AvailableDataDistribution> getReply(
	        @ApiParam("Identifier of the request") @PathVariable String requestId,
	        Authentication auth) {

		AvailableDataDistribution reply = dataDistributionService
		        .findByRequest(requestId);

		return new ResponseEntity<>(reply, HttpStatus.OK);
	}

}

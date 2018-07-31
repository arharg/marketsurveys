package com.mrs.marketsurveys.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.mrs.marketsurveys.domain.MarketSurvey;
import com.mrs.marketsurveys.dto.MarketSurveyFilter;
import com.mrs.marketsurveys.service.MarketSurveyService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping(value = "/api/marketsurveys")
@Api(value = "Market Surveys API",
    description = "Endpoints for creating, searching and retrieving avalaible and requested Market Surveys")
public class MarketSurveysController {

	@Autowired
	private MarketSurveyService surveyService;

	@RequestMapping(
	    value = "/{id}",
	    method = RequestMethod.GET,
	    produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ApiOperation(value = "Retrieves the details of an specific Market survey")
	@ApiResponses(value = {
	        @ApiResponse(code = 200, message = "Success",
	            response = MarketSurvey.class),
	        @ApiResponse(code = 400, message = "Bad request"),
	        @ApiResponse(code = 401, message = "Unauthorized"),
	        @ApiResponse(code = 404, message = "Not Found"),
	        @ApiResponse(code = 500, message = "Failure") })
	public ResponseEntity<MarketSurvey> details(
	        @ApiParam("Identifier of the market survey") @PathVariable String id,
	        Authentication auth) {
		MarketSurvey survey = surveyService.findById(id);
		return ResponseEntity.ok(survey);
	}

	@RequestMapping(
	    value = "/create",
	    method = RequestMethod.POST,
	    consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
	    produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ApiOperation(
	    value = "Creates new Market Surveys to use as details in requests or in information distributions")
	@ApiResponses(value = {
	        @ApiResponse(code = 200, message = "Success",
	            response = MarketSurvey.class,
	            responseContainer = "List"),
	        @ApiResponse(code = 400, message = "Bad request"),
	        @ApiResponse(code = 401, message = "Unauthorized"),
	        @ApiResponse(code = 404, message = "Not Found"),
	        @ApiResponse(code = 500, message = "Failure") })
	public ResponseEntity<List<MarketSurvey>> create(
	        @ApiParam("List of Market Surveys to be created") @RequestBody List<MarketSurvey> surveys,
	        Authentication auth) {
		List<MarketSurvey> savedSurveys = surveyService.createAll(surveys);
		return ResponseEntity.ok(savedSurveys);
	}

	@PreAuthorize("hasRole('PROVIDER')")
	@RequestMapping(
	    value = "/findbysubjects",
	    method = RequestMethod.POST,
	    consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
	    produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(
	    value = "Returns Market Surveys whose subjects are in the set passed as parameter")
	@ApiResponses(value = {
	        @ApiResponse(code = 200, message = "Success",
	            response = MarketSurvey.class,
	            responseContainer = "List"),
	        @ApiResponse(code = 400, message = "Bad request"),
	        @ApiResponse(code = 401, message = "Unauthorized"),
	        @ApiResponse(code = 403, message = "Forbidden"),
	        @ApiResponse(code = 404, message = "Not Found"),
	        @ApiResponse(code = 500, message = "Failure") })
	public ResponseEntity<List<MarketSurvey>> getBySubjects(
	        @ApiParam("Set of subjects of interest") @RequestBody MarketSurveyFilter filter,
	        Authentication auth) {

		List<MarketSurvey> surveys = surveyService
		        .getBySubjects(filter.getSubjects());
		return ResponseEntity.ok(surveys);
	}

}

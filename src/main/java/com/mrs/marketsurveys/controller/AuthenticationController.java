package com.mrs.marketsurveys.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.mrs.marketsurveys.config.auth.TokenProvider;
import com.mrs.marketsurveys.domain.AuthToken;
import com.mrs.marketsurveys.dto.UserDTO;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/oauth")
@Api(value = "Authentication API", description = "Generate authorization token")
public class AuthenticationController {

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private TokenProvider jwtTokenUtil;

	@RequestMapping(value = "/token", method = RequestMethod.POST)
	@ApiOperation(value = "Get JWT token from username and password", response = AuthToken.class)
	@ApiResponses(value = {
	        @ApiResponse(code = 200, message = "Success", response = AuthToken.class),
	        @ApiResponse(code = 401, message = "Unauthorized"),
	        @ApiResponse(code = 403, message = "Forbidden"),
	        @ApiResponse(code = 404, message = "Not Found"),
	        @ApiResponse(code = 500, message = "Failure") })
	public ResponseEntity<AuthToken> getAuthToken(
	        @RequestBody UserDTO loginUser) throws AuthenticationException {

		final Authentication authentication = authenticationManager
		        .authenticate(
		                new UsernamePasswordAuthenticationToken(
		                        loginUser.getUsername(),
		                        loginUser.getPassword()));
		SecurityContextHolder.getContext().setAuthentication(authentication);
		final String token = jwtTokenUtil.generateToken(authentication);

		return ResponseEntity.ok(new AuthToken(token));
	}

}

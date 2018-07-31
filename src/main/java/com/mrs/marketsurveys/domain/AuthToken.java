package com.mrs.marketsurveys.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
public @Data class AuthToken {
	public static final String TOKEN_PREFIX = "Bearer ";
	public static final String HEADER_STRING = "Authorization";
	public static final String AUTHORITIES_KEY = "scopes";
	public static final long ACCESS_TOKEN_VALIDITY_SECONDS = 5 * 60 * 60;
	public static final String SIGNING_KEY = "1mrsmsa8";

	private String token;
}

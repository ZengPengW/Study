package com.zp.springsecurity.security;

import org.springframework.security.core.AuthenticationException;

public class ImageCodeException extends AuthenticationException{

	public ImageCodeException(String msg) {
		super(msg);
	}

}

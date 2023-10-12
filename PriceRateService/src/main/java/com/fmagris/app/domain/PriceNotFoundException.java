package com.fmagris.app.domain;

public class PriceNotFoundException extends Exception{

	public PriceNotFoundException() {
		super("Price data not found");		
	}

	public PriceNotFoundException(String message) {
		super(message);
	}
	
	
	
}

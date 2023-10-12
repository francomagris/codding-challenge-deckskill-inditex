package com.fmagris.app.domain.ports;

import java.util.Date;
import java.util.List;

import com.fmagris.app.domain.InvalidDateFormatException;
import com.fmagris.app.domain.PriceDTO;
import com.fmagris.app.domain.PriceNotFoundException;

public interface PricePortIn {
	public PriceDTO getProductPriceInformation(String aplicationDate, Long productId, Long brandId) throws InvalidDateFormatException, PriceNotFoundException;
}

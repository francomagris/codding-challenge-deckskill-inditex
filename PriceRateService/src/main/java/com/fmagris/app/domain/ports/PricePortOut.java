package com.fmagris.app.domain.ports;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

import com.fmagris.app.domain.PriceDTO;
import com.fmagris.app.domain.PriceNotFoundException;

public interface PricePortOut {
	public PriceDTO findProductsPrice(Date aplicationDate, Long productId, Long brandId) throws PriceNotFoundException, ParseException;
}

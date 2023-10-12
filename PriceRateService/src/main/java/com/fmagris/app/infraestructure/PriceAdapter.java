package com.fmagris.app.infraestructure;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.fmagris.app.domain.PriceDTO;
import com.fmagris.app.domain.PriceNotFoundException;
import com.fmagris.app.domain.ports.PricePortOut;

@Component
public class PriceAdapter implements PricePortOut {

	@Autowired
	PriceRepository repository;
	
	@Override
	public PriceDTO findProductsPrice(Date aplicationDate, Long productId, Long brandId) throws PriceNotFoundException, ParseException {	
		PriceEntity priceResult = repository.findProductPrice(aplicationDate, productId, brandId)
        .stream()
        .findFirst()
        .orElseThrow(PriceNotFoundException::new);	
		
		return priceResult.convertEntityToDto(aplicationDate);	
	}
}

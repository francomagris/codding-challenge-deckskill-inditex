package com.fmagris.app.aplication;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fmagris.app.domain.InvalidDateFormatException;
import com.fmagris.app.domain.PriceDTO;
import com.fmagris.app.domain.PriceNotFoundException;
import com.fmagris.app.domain.ports.PricePortIn;
import com.fmagris.app.domain.ports.PricePortOut;

@Service
public class ProductPriceUseCases implements PricePortIn{
	
	@Autowired
	private PricePortOut productPricePort;
	
	private Date parseAplicationDate;

	@Override
	public PriceDTO getProductPriceInformation(String aplicationDateStr, Long productId, Long brandId) throws InvalidDateFormatException, PriceNotFoundException{
		SimpleDateFormat expectedDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");		
		
	    try {
	    	parseAplicationDate = expectedDateFormat.parse(aplicationDateStr);
	    	PriceDTO productPrice = productPricePort.findProductsPrice(parseAplicationDate, productId, brandId);
			
			return productPrice;
	    } catch (ParseException e) {
	        throw new InvalidDateFormatException("Incorrect date format. Should be like yyyy-MM-dd HH:mm:ss. \n"
	        										+ "Exaple: 2029-06-15 17:00:00"); 
	    }catch(PriceNotFoundException ex) {
	    	throw new PriceNotFoundException("Product price not found with data: \n"
	    										+ " AplicationDate : " + aplicationDateStr
	    										+ "\n Product_id: " + productId
	    										+ "\n Brand_id: " + brandId); 
	    }
	}

}

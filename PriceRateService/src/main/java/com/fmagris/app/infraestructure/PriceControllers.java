package com.fmagris.app.infraestructure;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fmagris.app.domain.PriceDTO;
import com.fmagris.app.domain.PriceNotFoundException;
import com.fmagris.app.domain.ports.PricePortIn;


@RestController
@RequestMapping("api-priceRateService/")
public class PriceControllers {
	
	@Autowired
	PricePortIn pricePortIn;
	
	@GetMapping(value= "find_{applicationDate}_{product_id}_{brand}")
	public ResponseEntity<?> getProductPrice(
			@PathVariable(name= "applicationDate") String applicationDate,
			@PathVariable(name= "product_id") Long productId,
			@PathVariable(name= "brand") Long brandId){
		try {	
			PriceDTO result = pricePortIn.getProductPriceInformation(applicationDate, productId, brandId);
			return new ResponseEntity<PriceDTO>(result, HttpStatus.OK);
		}catch(PriceNotFoundException e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}catch(Exception ex){
			ex.printStackTrace();
			return new ResponseEntity<String>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}	
	
}

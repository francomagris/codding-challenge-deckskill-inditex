package com.fmagris.app.infraestructure;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.fmagris.app.domain.InvalidDateFormatException;
import com.fmagris.app.domain.PriceDTO;
import com.fmagris.app.domain.ports.PricePortIn;

@SpringBootTest(webEnvironment = WebEnvironment.DEFINED_PORT)
class PriceControllersTest {

	@Autowired
	PricePortIn portIn;

	@Autowired
	private TestRestTemplate restTemplate;

	final String ENDPOINT = "http://localhost:8081/api-priceRateService/find_";
	static PriceDTO expectedReturnCase1, expectedReturnCase2, expectedReturnCase3, expectedReturnCase4, expectedReturnCase5;


	@Test
	void getProductPrice_succes() {
		
		// Test 1: petición a las 10:00 del día 14 del producto 35455   para la brand 1 (ZARA)
		PriceDTO result = restTemplate.getForEntity(ENDPOINT + "2020-06-14 10:00:00_35455_1", PriceDTO.class).getBody();
		assertEquals(1L, result.getPriceList(), "Should return Price List 1");
		assertEquals(35.5d, result.getPrice(), "Should return 35.5");
        
		// Test 2: petición a las 16:00 del día 14 del producto 35455   para la brand 1 (ZARA)
		result = restTemplate.getForEntity(ENDPOINT + "2020-06-14 16:00:00_35455_1", PriceDTO.class).getBody();
		assertEquals(2L, result.getPriceList(), "Should return Price List 2");
		assertEquals(25.45, result.getPrice(), "Should return 25.45");
		
		// Test 3: petición a las 21:00 del día 14 del producto 35455   para la brand 1 (ZARA)
		result = restTemplate.getForEntity(ENDPOINT + "2020-06-14 21:00:00_35455_1", PriceDTO.class).getBody();
		assertEquals(1L, result.getPriceList(), "Should return Price List 1");
		assertEquals(35.5d, result.getPrice(), "Should return 35.5");
        
		// Test 4: petición a las 10:00 del día 15 del producto 35455   para la brand 1 (ZARA)
        result = restTemplate.getForEntity(ENDPOINT + "2020-06-15 10:00:00_35455_1", PriceDTO.class).getBody();
        assertEquals(3L, result.getPriceList(), "Should return Price List 3");
        assertEquals(30.5d, result.getPrice(), "Should return 30.5");
        
		// Test 5: petición a las 21:00 del día 16 del producto 35455   para la brand 1 (ZARA)
        result = restTemplate.getForEntity(ENDPOINT + "2020-06-16 16:00:00_35455_1", PriceDTO.class).getBody();
        assertEquals(4L, result.getPriceList(), "Should return Price List 4");
        assertEquals(38.95d, result.getPrice(), "Should return 38.95");
        
	}

	@Test
	void getProductPrice_InvalidDateFormat() {
		ResponseEntity<String> response =  restTemplate.getForEntity(ENDPOINT + "2020-06-15_35455_1", String.class);
		assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
		assertTrue(response.getBody().replaceAll(" ", "").contains("Incorrect date format".replaceAll(" ", "")));		
	}

	@Test
	void getProductPrice_PriceNotFoundException() {
		ResponseEntity<String> response =  restTemplate.getForEntity(ENDPOINT + "2025-06-16 16:00:00_35455_1", String.class);
		assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
		assertTrue(response.getBody().replaceAll(" ", "").contains("Product price not found".replaceAll(" ", "")));		

	}


}

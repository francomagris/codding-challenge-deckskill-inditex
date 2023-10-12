package com.fmagris.app.aplication;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import java.text.SimpleDateFormat;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.fmagris.app.domain.InvalidDateFormatException;
import com.fmagris.app.domain.PriceDTO;
import com.fmagris.app.domain.PriceNotFoundException;
import com.fmagris.app.domain.ports.PricePortOut;

@ExtendWith(MockitoExtension.class)
class ProductPriceUseCasesTest {

	@InjectMocks
	ProductPriceUseCases productPriceUsecases;

	@Mock
	PricePortOut pricePortOut;

	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	@Test
	public void testGetProductPriceInformation_Success() throws Exception {

		when(pricePortOut.findProductsPrice(any(), anyLong(), anyLong())).thenReturn(PriceDTO.builder().build());

		PriceDTO result = productPriceUsecases.getProductPriceInformation("2029-06-15 17:00:00", 35455L, 1L);
		assertNotNull(result);

		verify(pricePortOut, times(1)).findProductsPrice(sdf.parse("2029-06-15 17:00:00"), 35455L, 1L);
	}

	@Test
	public void getProductPriceInformation_shouldReturnInvalidDateFormatException() throws Exception {
		assertThrows(InvalidDateFormatException.class, () -> {
			productPriceUsecases.getProductPriceInformation("2029-06-15 17", 35455L, 1L);
		});
		
		verify(pricePortOut, times(0)).findProductsPrice(any(),any(),any());

	}
		
}

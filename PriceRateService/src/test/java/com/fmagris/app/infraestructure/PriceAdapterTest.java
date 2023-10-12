package com.fmagris.app.infraestructure;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.fmagris.app.domain.PriceDTO;
import com.fmagris.app.domain.PriceNotFoundException;
import com.fmagris.app.domain.ports.PricePortOut;

@ExtendWith(MockitoExtension.class)
class PriceAdapterTest {
	
	@InjectMocks
	PriceAdapter priceAdapter;
	
	@Mock
	PriceRepository repository;
	

	@Test
	void findProductsPrice_Success() throws Exception{
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		PriceDTO expectedDTO = PriceDTO.builder()
							.productId(3455L)
							.brandId(1L)
							.aplicationDate("2029-06-15 17:00:00")
							.price(36.6d)
							.priceList(1L).build();
		
		PriceEntity entity = new PriceEntity();
					entity.setBrandId(1L);
					entity.setProductId(3455L);
					entity.setStartDate(sdf.parse("2029-06-14 17:00:00"));
					entity.setEndDate(sdf.parse("2029-06-20 17:00:00"));
					entity.setPrice(36.6d);
					entity.setPriceList(1L);
					entity.setPriority(1L);
					entity.setCurrency("EUR");

		when(repository.findProductPrice(any(Date.class), anyLong(), anyLong())).thenReturn(Arrays.asList(entity));
		
		PriceDTO result = priceAdapter.findProductsPrice(sdf.parse("2029-06-15 17:00:00"), 3455L, 1L);
		assertEquals(expectedDTO, result);
	}
	
	
	@Test
	void findProductsPrice_PriceNotFoundException() throws Exception{
		when(repository.findProductPrice(any(Date.class), anyLong(), anyLong())).thenReturn(Collections.EMPTY_LIST);
		
		assertThrows(PriceNotFoundException.class, 
				() -> priceAdapter.findProductsPrice(new Date(), 3455L, 1L));
		
	    verify(repository, times(1)).findProductPrice(any(), any(), any());
	}

}

package com.fmagris.app.domain;

import java.util.Date;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PriceDTO {
	private Long brandId;
	private Long productId;
	private Long priceList;
	private Double price;
	private String aplicationDate;
}

package com.fmagris.app.infraestructure;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.fmagris.app.domain.PriceDTO;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "PRICES")
@IdClass(PriceId.class)
public class PriceEntity {
	@Id
	@Column(name = "BRAND_ID")
	private Long brandId;
	
	@Id
	@Column(name = "START_DATE")
	private Date startDate;
	
	@Id
	@Column(name = "END_DATE")
	private Date endDate;
	
	@Id
	@Column(name = "PRICE_LIST")
	private Long priceList;
	
	@Id
	@Column(name = "PRODUCT_ID")
	private Long productId;
	
	@Id
	@Column(name = "PRIORITY")
	private Long priority;
	
	@Column(name = "PRICE")
	private Double price;
	
	@Column(name = "CURR")
	private String currency;
	
	
	
	public PriceDTO convertEntityToDto(Date aplicationDate) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");	
		
		PriceDTO dto = PriceDTO.builder()
				.brandId(brandId)
				.priceList(priceList)
				.productId(productId)
				.price(price)
				.aplicationDate(sdf.format(aplicationDate))
				.build();
		return dto;
	}
	
}

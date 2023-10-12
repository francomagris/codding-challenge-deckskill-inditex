package com.fmagris.app.infraestructure;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

import jakarta.persistence.Embeddable;
import lombok.Data;

@Data
public class PriceId implements Serializable {
	
    private Long brandId;
    private Date startDate;
    private Date endDate;
    private Long priceList;
    private Long productId;
    private Long priority;

}


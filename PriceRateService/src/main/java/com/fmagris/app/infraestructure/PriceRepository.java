package com.fmagris.app.infraestructure;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PriceRepository extends JpaRepository<PriceEntity, Long>{
	@Query(value = "SELECT *"
	        + " FROM PRICES"
	        + " WHERE "
	        + "  START_DATE <= :aplicationDate AND"
	        + "  END_DATE >= :aplicationDate AND"
	        + "  PRODUCT_ID = :productId AND"
	        + "  BRAND_ID = :brandId"
	        + " ORDER BY PRIORITY DESC", 
	        nativeQuery = true)
	public List<PriceEntity> findProductPrice(@Param("aplicationDate") Date aplicationDate,
										@Param("productId") Long productId,
										@Param("brandId") Long brandId);
}

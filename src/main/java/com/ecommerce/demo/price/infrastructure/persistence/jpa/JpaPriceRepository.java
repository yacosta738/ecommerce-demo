package com.ecommerce.demo.price.infrastructure.persistence.jpa;


import com.ecommerce.demo.price.infrastructure.persistence.jpa.entities.PriceEntity;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * JPA price repository.
 *
 * @author Yuniel Acosta
 */
@Repository
public interface JpaPriceRepository extends JpaRepository<PriceEntity, Long> {

  @Query(value = " select p from PriceEntity p where p.productId = :productId "
      + " and  p.brandId = :brandId and :applicationDate BETWEEN p.startDate and p.endDate ")
  List<PriceEntity> findPrice(@Param("productId") Long productId, @Param("brandId") Long brandId,
      @Param("applicationDate") LocalDateTime applicationDate);
}
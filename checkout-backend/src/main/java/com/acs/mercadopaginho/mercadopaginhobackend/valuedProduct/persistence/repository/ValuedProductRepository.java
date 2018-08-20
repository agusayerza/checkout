package com.acs.mercadopaginho.mercadopaginhobackend.valuedProduct.persistence.repository;

import com.acs.mercadopaginho.mercadopaginhobackend.valuedProduct.persistence.model.ValuedProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ValuedProductRepository extends JpaRepository<ValuedProduct, Long> {
    Optional<ValuedProduct> findFirstByProductId(Long productId);
    List<ValuedProduct> findAllByProductIdOrderByDateTimeDesc(Long productId);
}

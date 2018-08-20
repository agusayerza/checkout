package com.acs.mercadopaginho.mercadopaginhobackend.sale.persistence.repository;

import com.acs.mercadopaginho.mercadopaginhobackend.sale.persistence.model.Sale;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SaleRepository extends JpaRepository<Sale, Long> {
    List<Sale> findAllByUserId(Long userId);
}

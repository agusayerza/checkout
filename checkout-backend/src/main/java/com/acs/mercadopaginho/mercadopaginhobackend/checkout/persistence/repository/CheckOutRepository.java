package com.acs.mercadopaginho.mercadopaginhobackend.checkout.persistence.repository;


import com.acs.mercadopaginho.mercadopaginhobackend.checkout.persistence.model.CheckOut;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CheckOutRepository extends JpaRepository<CheckOut, Long> {

}
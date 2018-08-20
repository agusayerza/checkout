package com.acs.mercadopaginho.mercadopaginhobackend.address.persistence.repository;

import com.acs.mercadopaginho.mercadopaginhobackend.address.persistence.model.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {

}

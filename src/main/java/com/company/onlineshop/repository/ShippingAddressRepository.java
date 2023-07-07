package com.company.onlineshop.repository;

import com.company.onlineshop.model.ShippingAddress;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ShippingAddressRepository extends JpaRepository<ShippingAddress, Integer> {

    Optional<ShippingAddress> findByShippingAddressIdAndDeletedAtIsNull(Integer id);


}

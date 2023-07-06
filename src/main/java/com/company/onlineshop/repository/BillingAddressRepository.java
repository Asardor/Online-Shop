package com.company.onlineshop.repository;

import com.company.onlineshop.model.BillingAddress;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BillingAddressRepository extends JpaRepository<BillingAddress,Integer> {

    Optional<BillingAddress> findByBillingAddressIdAndDeletedAtIsNull(Integer billingAddressId);
}

package com.company.onlineshop.repository;

import com.company.onlineshop.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {
    Optional<Customer> findByCustomerIdAndDeletedAtIsNull(Integer customerId);
}

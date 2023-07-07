package com.company.onlineshop.repository;

import com.company.onlineshop.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {
    Optional<Order> findByOrderIdAndDeletedAtIsNull(Integer orderId);
}

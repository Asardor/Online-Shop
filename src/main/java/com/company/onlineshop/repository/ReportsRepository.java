package com.company.onlineshop.repository;

import com.company.onlineshop.model.Reports;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ReportsRepository extends JpaRepository<Reports, Integer> {

    Optional<Reports> findByReportsIdAndDeletedAtIsNull(Integer reportsId);

}

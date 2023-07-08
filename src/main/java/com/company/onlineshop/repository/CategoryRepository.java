package com.company.onlineshop.repository;

import com.company.onlineshop.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {

    Optional<Category> findByCategoryIdAndDeletedAtIsNull(Integer categoryId);

}

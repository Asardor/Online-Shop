package com.company.onlineshop.repository;

import com.company.onlineshop.dto.ProductDto;
import com.company.onlineshop.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {

    Optional<Product> findByProductIdAndDeletedAtIsNull(Integer productId);

    @Query(value = "select p from Product p where " +
            "coalesce(:productId,p.productId)=p.productId and " +
            "coalesce(:name,p.name)=p.name and " +
            "coalesce(:receivedPrice,p.receivedPrice)=p.receivedPrice and " +
            "coalesce(:sellingPrice,p.sellingPrice)=p.sellingPrice and " +
            "coalesce(:prodMass,p.prodMass)=p.prodMass and " +
            "coalesce(:amount,p.amount)=p.amount")
    Page<Product> getProductBasicSearch(@Param(value = "productId") Integer productId,
                                        @Param(value = "name") String name,
                                        @Param(value = "receivedPrice") Double receivedPrice,
                                        @Param(value = "sellingPrice") Double sellingPrice,
                                        @Param(value = "prodMass") Double prodMass,
                                        @Param(value = "amount") Double amount,
                                        Pageable pageable);
}

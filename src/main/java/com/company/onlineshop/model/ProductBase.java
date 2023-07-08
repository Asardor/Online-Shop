package com.company.onlineshop.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.Range;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = ("product_base"))
public class ProductBase {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = ("prod_id"))
    private Integer prodId;
    private Double totalPrice;
    private Double amount;
    private Boolean status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime deletedAt;
}

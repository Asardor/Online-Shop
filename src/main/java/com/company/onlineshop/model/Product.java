package com.company.onlineshop.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = ("product"))
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Column(name = ("product_id"))
    private Integer productId;
    private String name;
    private Double receivedPrice;
    private Double sellingPrice;
    private Double prodMass;
    private Double amount;
    private Boolean status;
    private LocalDate expiredAt;
    private LocalDateTime cratedAt;
    private LocalDateTime updatedAt;
    private LocalDateTime deletedAt;
}

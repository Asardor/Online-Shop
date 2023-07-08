package com.company.onlineshop.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductDto {
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

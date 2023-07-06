package com.company.onlineshop.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BasketDto {

    private Integer basketId;
    private Integer customerId;
    private Integer orderId;

    private Double totalPrice;
    private Boolean status;

//    private List<ProductDto> products;

    private LocalDateTime createdAt;
}

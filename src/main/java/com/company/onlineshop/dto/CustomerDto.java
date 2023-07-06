package com.company.onlineshop.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CustomerDto {

    private Integer customerId;
    private Integer accountId;
    private Integer shippingAddressId;
    private Integer orderId;
    private String firstName;
    private String lastName;
    private String customerPhone;


    private BasketDto basket;
    private List<OrderDto> orders;

    private Boolean status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime deletedAt;


}

package com.company.onlineshop.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = ("shippingAddress"))
public class ShippingAddress {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @SequenceGenerator(name = "shippingAddress_seq_id", sequenceName = "shippingAddress_seq_id", allocationSize = 1)
    private Integer shippingAddressId;
    private Integer customerId;
    private Integer cartId;

    private String address;
    private String city;
    private String state;
    private String country;

    private Boolean status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime deletedAt;

}

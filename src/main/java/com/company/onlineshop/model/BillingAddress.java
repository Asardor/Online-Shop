package com.company.onlineshop.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = ("billingAddress"))
public class BillingAddress {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @SequenceGenerator(name = "basket_seq_id", sequenceName = "basket_seq_id", allocationSize = 1)
    private Integer billingAddressId;
    private String country;
    private String city;
    private String address;

    //TODO: cart yaratasan -> san
///    @OneToOne(cascade = CascadeType.ALL)
///    @JoinColumn(name ="billingAddressId", referencedColumnName = "billingAddressId",updatable = false,insertable = false)
//    private Cart cart;


    private Boolean status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime deletedAt;
}

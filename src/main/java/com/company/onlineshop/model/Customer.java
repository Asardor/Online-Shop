package com.company.onlineshop.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = ("customer"))
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @SequenceGenerator(name = "customer_seq_id", sequenceName = "customer_seq_id", allocationSize = 1)
    private Integer customerId;
    private Integer accountId;
    private Integer shippingAddressId;
    private Integer orderId;
    private String firstName;
    private String lastName;
    private String customerPhone;


    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name =("customerId"), referencedColumnName = ("customerId"),updatable = false,insertable = false)
    private Basket basket;

   @OneToMany(cascade = CascadeType.ALL, mappedBy = "customerId", fetch = FetchType.EAGER)
    private List<Order> orders;

    private Boolean status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime deletedAt;

}

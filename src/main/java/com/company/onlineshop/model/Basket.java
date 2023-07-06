package com.company.onlineshop.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = ("basket"))
public class Basket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @SequenceGenerator(name = "basket_seq_id", sequenceName = "basket_seq_id", allocationSize = 1)
    private Integer basketId;
    private Integer customerId;
    private Integer orderId;

    private Double totalPrice;
    private Boolean status;

    //TODO:product yaratasan -> san
///    @OneToMany(mappedBy = "basketId", cascade = CascadeType.ALL, fetch =FetchType.EAGER)
//    private List<Product> products;

    private LocalDateTime createdAt;

}

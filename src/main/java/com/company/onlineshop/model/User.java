package com.company.onlineshop.model;


import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;

@Data
@Entity
@Table(name = ("users"))
public class User {
    @Id
    @GeneratedValue(generator = "user_seq_id")
    @SequenceGenerator(name = "user_seq_id",sequenceName = "user_seq_id",allocationSize =1)
    private Integer userId;
    private String firstname;
    private String lastname;
    private String nickname;
    private String username;
    private String phoneNumber;
    private LocalDate birthdate;
    private String passportSeries;
    private LocalDate workingDate;
    private LocalDateTime workingTime;
    private String password;

   // private Set<Account> accounts;

    private Integer imageId;
    @OneToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @JoinColumn(name =("imageId"),referencedColumnName = ("imageId"),insertable = false,updatable = false)
    private Image image;

    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER,mappedBy = "userId")
    private Set<Authorities> authority;

   // private Map<String,String> emailPassword;

    private Boolean status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime deletedAt;
}

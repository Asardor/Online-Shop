package com.company.onlineshop.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = ("authorities"))
public class Authorities {
    @Id
    @GeneratedValue(generator = "auth_seq_id")
    @SequenceGenerator(name = "auth_seq_id", sequenceName = "auth_seq_id", allocationSize = 1)
    private Integer authId;
    private Integer userId;
    private String username;
    private String authority;
    private Boolean status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime deletedAt;
}

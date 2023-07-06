package com.company.onlineshop.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class AuthoritiesDto {
    private Integer authId;
    private Integer userId;
    private String username;
    private String authority;
    private Boolean status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime deletedAt;
}

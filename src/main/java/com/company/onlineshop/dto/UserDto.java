package com.company.onlineshop.dto;


import com.company.onlineshop.model.Image;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.CascadeType;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserDto {
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

    // private Set<AccountDto> accounts;

    private Integer imageId;
    private ImageDto image;

    private Set<AuthoritiesDto> authority;

    //    private Map<String,String> emailPassword;
    private Boolean status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime deletedAt;
}

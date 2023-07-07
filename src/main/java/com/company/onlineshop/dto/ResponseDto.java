package com.company.onlineshop.dto;

import lombok.*;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;


@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ResponseDto<T> {

    private String massage;
    private boolean success;
    private T data;
    private List<ErrorDto> errors;

}


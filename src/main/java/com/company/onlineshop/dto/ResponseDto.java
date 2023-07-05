package com.company.onlineshop.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.util.List;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ResponseDto<T> {

    private int code;
    private String message;
    private boolean success;
    private T date;
    private List<ErrorDto> errors;



}

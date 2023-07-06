package com.company.onlineshop.dto;

import lombok.*;
import org.springframework.boot.context.properties.bind.DefaultValue;

import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ResponseDto<T> {
    private String massage;
    private Boolean success;
    private T data;
    private List<ErrorDto> errors;
}

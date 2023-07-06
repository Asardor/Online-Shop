package com.company.onlineshop.service;

import com.company.onlineshop.dto.OrderDto;
import com.company.onlineshop.dto.ResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderService {
    public ResponseDto<OrderDto> create(OrderDto dto) {
        return null;
    }

    public ResponseDto<OrderDto> get(Integer orderId) {
        return null;
    }

    public ResponseDto<OrderDto> update(OrderDto dto, Integer orderId) {
        return null;
    }

    public ResponseDto<OrderDto> delete(Integer orderId) {
        return null;
    }
}

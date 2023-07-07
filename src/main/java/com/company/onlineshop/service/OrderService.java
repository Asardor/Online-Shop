package com.company.onlineshop.service;

import com.company.onlineshop.dto.BillingAddressDto;
import com.company.onlineshop.dto.OrderDto;
import com.company.onlineshop.dto.ResponseDto;
import com.company.onlineshop.model.Order;
import com.company.onlineshop.repository.OrderRepository;
import com.company.onlineshop.service.mapper.OrderMapper;
import com.company.onlineshop.service.validate.OrderValidate;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class OrderService {


    private final OrderMapper orderMapper;
    private final OrderValidate orderValidate;
    private final OrderRepository orderRepository;



    public ResponseDto<OrderDto> create(OrderDto dto) {
        try {
            Order order=this.orderMapper.toEntity(dto);
            order.setCreatedAt(LocalDateTime.now());
            order.setStatus(true);
            this.orderRepository.save(order);
            return ResponseDto.<OrderDto>builder()
                    .massage("Ok")
                    .success(true)
                    .data(orderMapper.toDtoNotBasket(order))
                    .build();

        } catch (Exception e) {
            return ResponseDto.<OrderDto>builder()
                    .success(false)
                    .massage(e.getMessage())
                    .build();
        }
    }

    public ResponseDto<OrderDto> get(Integer orderId) {
        return this.orderRepository.findByOrderIdAndDeletedAtIsNull(orderId)
                .map(o ->ResponseDto.<OrderDto>builder()
                        .success(true)
                        .massage("Ok")
                        .data(orderMapper.toDto(o))
                        .build())
                .orElse(ResponseDto.<OrderDto>
                                builder()
                        .success(false)
                        .massage("Billing Address is not found")
                        .build());
    }

    public ResponseDto<OrderDto> update(OrderDto dto, Integer orderId) {
        try {
            return this.orderRepository.findByOrderIdAndDeletedAtIsNull(orderId)
                    .map(order -> {
                        order.setUpdatedAt(LocalDateTime.now());
                        orderMapper.update(dto, order);
                        orderRepository.save(order);
                        return ResponseDto.<OrderDto>builder()
                                .success(true)
                                .massage("Ok")
                                .data(orderMapper.toDto(order))
                                .build();
                    }).orElse(ResponseDto.<OrderDto>
                                    builder()
                            .success(false)
                            .massage("Billing Address is not found")
                            .build());
        } catch (Exception e) {
                return ResponseDto.<OrderDto>builder()
                        .success(false)
                        .massage(e.getMessage())
                        .build();
        }
    }

    public ResponseDto<OrderDto> delete(Integer orderId) {

        try {
            return orderRepository.findByOrderIdAndDeletedAtIsNull(orderId)
                    .map(order -> {
                        order.setStatus(false);
                        order.setDeletedAt(LocalDateTime.now());
                        orderRepository.save(order);
                        return ResponseDto.<OrderDto>builder()
                                .success(true)
                                .massage("Ok")
                                .data(orderMapper.toDtoNotBasket(order))
                                .build();
                    }).orElse(ResponseDto.<OrderDto>builder()
                            .success(false)
                            .massage(String.format("This order %s is not found", orderId))
                            .build());
        } catch (Exception e) {
            return ResponseDto.<OrderDto>builder()
                    .success(false)
                    .massage(e.getMessage())
                    .build();
        }


    }
}

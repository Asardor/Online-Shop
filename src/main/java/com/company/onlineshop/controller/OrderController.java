package com.company.onlineshop.controller;

import com.company.onlineshop.dto.OrderDto;
import com.company.onlineshop.dto.ResponseDto;
import com.company.onlineshop.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "order")
public class OrderController {

    private final OrderService orderService;


    @PostMapping("/create")
    public ResponseDto<OrderDto> create(@RequestBody OrderDto dto){
        return orderService.create(dto);
    }

    @GetMapping("/get/{id}")
    public ResponseDto<OrderDto> get(@PathVariable(value = "id") Integer orderId){
        return orderService.get(orderId);
    }

    @PutMapping("/update/{id}")
    public ResponseDto<OrderDto> update(@RequestBody OrderDto dto, @PathVariable(value = "id") Integer orderId){
        return orderService.update(dto, orderId);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseDto<OrderDto> delete(@PathVariable(value = "id") Integer orderId){
        return orderService.delete(orderId);
    }

}

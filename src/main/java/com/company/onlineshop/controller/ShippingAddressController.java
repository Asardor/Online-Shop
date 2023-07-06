package com.company.onlineshop.controller;

import com.company.onlineshop.dto.ResponseDto;
import com.company.onlineshop.dto.ShippingAddressDto;
import com.company.onlineshop.service.ShippingAddressService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "shipping")
@RequiredArgsConstructor
public class ShippingAddressController {

    private final ShippingAddressService shippingAddressService;


    @PostMapping("/create")
    public ResponseDto<ShippingAddressDto> create(@RequestBody ShippingAddressDto dto){
        return shippingAddressService.create(dto);
    }

    @GetMapping("/get/{id}")
    public ResponseDto<ShippingAddressDto> get(@PathVariable(value = "id") Integer shippingAddressId){
        return shippingAddressService.get(shippingAddressId);
    }

    @PostMapping("/update/{id}")
    public ResponseDto<ShippingAddressDto> update(@RequestBody ShippingAddressDto dto, @PathVariable(value = "id") Integer shippingAddressId){
        return shippingAddressService.update(dto, shippingAddressId);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseDto<ShippingAddressDto> delete(@PathVariable(value = "id") Integer shippingAddressId){
        return shippingAddressService.delete(shippingAddressId);
    }

}

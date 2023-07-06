package com.company.onlineshop.controller;

import com.company.onlineshop.dto.BillingAddressDto;
import com.company.onlineshop.dto.ResponseDto;
import com.company.onlineshop.service.BillingAddressService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "billing")
@RequiredArgsConstructor
public class BillingAddressController {

    private final BillingAddressService billingAddressService;


    @PostMapping("/create")
    public ResponseDto<BillingAddressDto> create(@RequestBody BillingAddressDto dto){
        return billingAddressService.create(dto);
    }

    @GetMapping("/get/{id}")
    public ResponseDto<BillingAddressDto> get(@PathVariable(value = "id") Integer billingAddressId){
        return billingAddressService.get(billingAddressId);
    }

    @PutMapping("/update/{id}")
    public ResponseDto<BillingAddressDto> update(@RequestBody BillingAddressDto dto, @PathVariable(value = "id") Integer billingAddressId){
        return billingAddressService.update(dto, billingAddressId);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseDto<BillingAddressDto> delete(@PathVariable(value = "id") Integer billingAddressId){
        return billingAddressService.delete(billingAddressId);
    }


}

package com.company.onlineshop.controller;

import com.company.onlineshop.dto.BillingAddressDto;
import com.company.onlineshop.dto.ResponseDto;
import com.company.onlineshop.service.BillingAddressService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "billing")
@RequiredArgsConstructor
public class BillingAddressController {

    private final BillingAddressService billingAddressService;




    @Operation(tags = "This is method create new basket")
    @PostMapping("/create")
    public ResponseDto<BillingAddressDto> create(@RequestBody BillingAddressDto dto){
        return billingAddressService.create(dto);
    }
    @Operation(tags = "This method getting the billingAddressId")
    @GetMapping("/get/{id}")
    public ResponseDto<BillingAddressDto> get(@PathVariable(value = "id") Integer billingAddressId){
        return billingAddressService.get(billingAddressId);
    }

    @Operation(tags = "This method is updated by the billingAddressId")
    @PutMapping("/update/{id}")
    public ResponseDto<BillingAddressDto> update(@RequestBody BillingAddressDto dto, @PathVariable(value = "id") Integer billingAddressId){
        return billingAddressService.update(dto, billingAddressId);
    }

    @Operation(tags = "This method is deleted by the billingAddressId")
    @DeleteMapping("/delete/{id}")
    public ResponseDto<BillingAddressDto> delete(@PathVariable(value = "id") Integer billingAddressId){
        return billingAddressService.delete(billingAddressId);
    }


}

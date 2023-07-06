package com.company.onlineshop.controller;

import com.company.onlineshop.dto.CustomerDto;
import com.company.onlineshop.dto.ResponseDto;
import com.company.onlineshop.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "customer")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;


    @PostMapping("/create")
    public ResponseDto<CustomerDto> create(@RequestBody CustomerDto dto){
        return customerService.create(dto);
    }

    @GetMapping("/get/{id}")
    public ResponseDto<CustomerDto> get(@PathVariable(value = "id") Integer customerId){
        return customerService.get(customerId);
    }

    @PutMapping("/update/{id}")
    public ResponseDto<CustomerDto> update(@RequestBody CustomerDto dto, @PathVariable(value = "id") Integer customerId){
        return customerService.update(dto, customerId);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseDto<CustomerDto> delete(@PathVariable(value = "id") Integer customerId){
        return customerService.delete(customerId);
    }

}

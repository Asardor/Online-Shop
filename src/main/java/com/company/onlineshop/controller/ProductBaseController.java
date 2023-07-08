package com.company.onlineshop.controller;

import com.company.onlineshop.dto.ProductBaseDto;
import com.company.onlineshop.dto.ResponseDto;
import com.company.onlineshop.service.ProductBaseService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/prod-base")
@RequiredArgsConstructor
public class ProductBaseController {
    private final ProductBaseService productBaseService;

    @PostMapping("/create")
    public ResponseDto<ProductBaseDto> create(@RequestBody ProductBaseDto dto) {
        return this.productBaseService.create(dto);
    }


    @GetMapping("/get/{id}")
    public ResponseDto<ProductBaseDto> get(@PathVariable("id") Integer prodBaseId) {
        return this.productBaseService.get(prodBaseId);
    }


    @PutMapping("/update/{id}")
    public ResponseDto<ProductBaseDto> update(@PathVariable("id") Integer prodBaseId,
                                              @RequestBody ProductBaseDto dto) {
        return this.productBaseService.update(prodBaseId, dto);
    }


    @DeleteMapping("/delete/{id}")
    public ResponseDto<ProductBaseDto> delete(@PathVariable("id") Integer prodBaseId) {
        return this.productBaseService.delete(prodBaseId);
    }
}

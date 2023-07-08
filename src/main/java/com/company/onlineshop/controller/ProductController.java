package com.company.onlineshop.controller;

import com.company.onlineshop.dto.ProductDto;
import com.company.onlineshop.dto.ResponseDto;
import com.company.onlineshop.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("product")
@RequiredArgsConstructor
public class ProductController  {
    private final ProductService productService;
    @PostMapping("/create")
    public ResponseDto<ProductDto> create(@RequestBody ProductDto dto) {
        return this.productService.create(dto);
    }


    @GetMapping("/get/{id}")
    public ResponseDto<ProductDto> get(@PathVariable("id") Integer productId) {
        return this.productService.get(productId);
    }

    @PutMapping("/update/{id}")
    public ResponseDto<ProductDto> update(@PathVariable("id") Integer productId,
                                          @RequestBody ProductDto dto) {
        return this.productService.update(productId, dto);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseDto<ProductDto> delete(@PathVariable("id") Integer productId) {
        return this.productService.delete(productId);
    }

    @GetMapping("/get-search")
    public ResponseDto<Page<ProductDto>> getUniversialSearch(@RequestParam Map<String, String> params){
      return this.productService.getSearch(params);
    }

}

package com.company.onlineshop.controller;

import com.company.onlineshop.dto.BasketDto;
import com.company.onlineshop.dto.ResponseDto;
import com.company.onlineshop.service.BasketService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "basket")
public class BasketController {
    private final BasketService basketService;


    @Operation(tags = "This is method create new basket")
    @PostMapping("/create")
    public ResponseDto<BasketDto> create(@RequestBody BasketDto dto){
        return basketService.create(dto);
    }

    @Operation(tags = "This method getting the basket by id")
    @GetMapping("/get/{id}")
    public ResponseDto<BasketDto> get(@PathVariable(value = "id") Integer basketId){
        return basketService.get(basketId);
    }
    @Operation(tags = "This method is updated by the basket id")
    @PutMapping("/update/{id}")
    public ResponseDto<BasketDto> update(@RequestBody BasketDto dto, @PathVariable(value = "id") Integer basketId){
        return basketService.update(dto, basketId);
    }
    @Operation(tags = "This method is deleted by the basket id")
    @DeleteMapping("/delete/{id}")
    public ResponseDto<BasketDto> delete(@PathVariable(value = "id") Integer basketId){
        return basketService.delete(basketId);
    }

}

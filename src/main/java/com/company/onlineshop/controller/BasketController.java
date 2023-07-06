package com.company.onlineshop.controller;

import com.company.onlineshop.dto.BasketDto;
import com.company.onlineshop.dto.ResponseDto;
import com.company.onlineshop.service.BasketService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "basket")
public class BasketController {
    private final BasketService basketService;


    @PostMapping("/create")
    public ResponseDto<BasketDto> create(@RequestBody BasketDto dto){
        return basketService.create(dto);
    }

    @GetMapping("/get/{id}")
    public ResponseDto<BasketDto> get(@PathVariable(value = "id") Integer basketId){
        return basketService.get(basketId);
    }

    @PutMapping("/update/{id}")
    public ResponseDto<BasketDto> update(@RequestBody BasketDto dto, @PathVariable(value = "id") Integer basketId){
        return basketService.update(dto, basketId);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseDto<BasketDto> delete(@PathVariable(value = "id") Integer basketId){
        return basketService.delete(basketId);
    }


}

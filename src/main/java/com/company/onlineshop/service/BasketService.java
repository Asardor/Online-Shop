package com.company.onlineshop.service;

import com.company.onlineshop.dto.BasketDto;
import com.company.onlineshop.dto.ResponseDto;
import com.company.onlineshop.model.Basket;
import com.company.onlineshop.repository.BasketRepository;
import com.company.onlineshop.service.mapper.BasketMapper;
import com.company.onlineshop.service.validate.BasketValidate;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BasketService {

    private final BasketMapper basketMapper;
    private final BasketValidate basketValidate;
    private final BasketRepository basketRepository;


    public ResponseDto<BasketDto> create(BasketDto dto) {
        try {
            Basket basket = this.basketMapper.toEntity(dto);
            basket.setCreatedAt(LocalDateTime.now());
            this.basketRepository.save(basket);
            return ResponseDto.<BasketDto>builder()
                    .success(true)
                    .message("Ok")
                    .date(this.basketMapper.toDtoNotProduct(basket))
                    .build();
        } catch (Exception e) {
            return ResponseDto.<BasketDto>builder()
                    .message(e.getMessage())
                    .code(-3)
                    .success(false)
                    .build();
        }

    }

    public ResponseDto<BasketDto> get(Integer entityId) {
        Optional<Basket> optional = this.basketRepository.findByBasketId(entityId);
        if (optional.isEmpty()) {
            return ResponseDto.<BasketDto>builder()
                    .message("Basket is not found!")
                    .code(-1)
                    .build();
        }

        return ResponseDto.<BasketDto>builder()
                .success(true)
                .message("OK")
                .date(this.basketMapper.toDto(optional.get()))
                .build();
    }

    public ResponseDto<BasketDto> update(BasketDto dto, Integer entityId) {
        Optional<Basket> optional = this.basketRepository.findByBasketId(entityId);
        if (optional.isEmpty()) {
            return ResponseDto.<BasketDto>builder()
                    .message("Basket is not found!")
                    .code(-1)
                    .build();
        }
        Basket basket = optional.get();
        this.basketMapper.update(dto, basket);
        this.basketRepository.save(basket);
        return ResponseDto.<BasketDto>builder()
                .success(true)
                .message("Basket successful updated and save database")
                .date(this.basketMapper.toDtoNotProduct(basket))
                .build();
    }

    public ResponseDto<BasketDto> delete(Integer entityId) {
        Optional<Basket> optional = this.basketRepository.findByBasketId(entityId);
        if (optional.isEmpty()) {
            return ResponseDto.<BasketDto>builder()
                    .message("Basket is not found!")
                    .code(-1)
                    .build();
        }
        this.basketRepository.delete(optional.get());
        return ResponseDto.<BasketDto>builder()
                .success(true)
                .message("Basket successful deleted!")
                .date(this.basketMapper.toDtoNotProduct(optional.get()))
                .build();
    }



}

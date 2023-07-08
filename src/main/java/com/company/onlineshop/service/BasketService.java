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
                    .data(this.basketMapper.toDtoNotProduct(basket))
                    .build();
        } catch (Exception e) {
            return ResponseDto.<BasketDto>builder()
                    .message(e.getMessage())
                    .success(false)
                    .build();
        }

    }

    public ResponseDto<BasketDto> get(Integer basketId) {
        return basketRepository.findByBasketId(basketId)
                .map(basket -> ResponseDto.<BasketDto>builder()
                        .message("Ok")
                        .success(true)
                        .data(basketMapper.toDto(basket))
                        .build())
                .orElse(ResponseDto.<BasketDto>builder()
                        .message("Basket is not found")
                        .success(false)
                        .build());
    }

    public ResponseDto<BasketDto> update(BasketDto dto, Integer basketId) {
        Optional<Basket> optional = this.basketRepository.findByBasketId(basketId);
        if (optional.isEmpty()) {
            return ResponseDto.<BasketDto>builder()
                    .message("BillingAddressId is not found!")
                    .build();
        }
        Basket basket = optional.get();
        this.basketMapper.update(dto, basket);
        this.basketRepository.save(basket);
        return ResponseDto.<BasketDto>builder()
                .success(true)
                .message("Billing Address successful updated and save database")
                .data(this.basketMapper.toDtoNotProduct(basket))
                .build();
    }

    public ResponseDto<BasketDto> delete(Integer billingAddressId) {
        try {
            return basketRepository.findByBasketId(billingAddressId)
                    .map(basket -> {
//                                basket.setDeletedAt(LocalDateTime.now());
                                basket.setStatus(false);
                                basketRepository.save(basket);
                                return ResponseDto.<BasketDto>builder()
                                        .success(true)
                                        .message("Successful updated")
                                        .data(this.basketMapper.toDtoNotProduct(basket))
                                        .build();
                            }
                    ).orElse(ResponseDto.<BasketDto>builder()
                            .success(false)
                            .message(String.format("This %s billingAddressId is not found", billingAddressId))
                            .build());
        } catch (Exception e) {
            return ResponseDto.<BasketDto>builder()
                    .success(false)
                    .message(e.getMessage())
                    .build();
        }
    }


}

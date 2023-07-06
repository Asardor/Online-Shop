package com.company.onlineshop.service;

import com.company.onlineshop.dto.ResponseDto;
import com.company.onlineshop.dto.ShippingAddressDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ShippingAddressService {
    public ResponseDto<ShippingAddressDto> create(ShippingAddressDto dto) {
        return null;
    }

    public ResponseDto<ShippingAddressDto> get(Integer shippingAddressId) {
        return null;
    }

    public ResponseDto<ShippingAddressDto> update(ShippingAddressDto dto, Integer shippingAddressId) {
        return null;
    }

    public ResponseDto<ShippingAddressDto> delete(Integer shippingAddressId) {
        return null;
    }
}

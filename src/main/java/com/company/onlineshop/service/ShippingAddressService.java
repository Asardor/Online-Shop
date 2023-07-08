package com.company.onlineshop.service;

import com.company.onlineshop.dto.ResponseDto;
import com.company.onlineshop.dto.ShippingAddressDto;
import com.company.onlineshop.model.ShippingAddress;
import com.company.onlineshop.repository.ShippingAddressRepository;
import com.company.onlineshop.service.mapper.ShippingAddressMapper;
import com.company.onlineshop.service.validate.ShippingAddressValidate;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class ShippingAddressService {


    private final ShippingAddressMapper shippingAddressMapper;
    private final ShippingAddressValidate shippingAddressValidate;
    private final ShippingAddressRepository shippingAddressRepository;

    public ResponseDto<ShippingAddressDto> create(ShippingAddressDto dto) {

        try {
            ShippingAddress address = this.shippingAddressMapper.toEntity(dto);
            address.setCreatedAt(LocalDateTime.now());
            address.setStatus(true);
            this.shippingAddressRepository.save(address);
            return ResponseDto.<ShippingAddressDto>builder()
                    .message("Ok")
                    .success(true)
                    .data(this.shippingAddressMapper.toDtoNot(address))
                    .build();
        } catch (Exception e) {
            return ResponseDto.<ShippingAddressDto>builder()
                    .success(false)
                    .message(e.getMessage())
                    .build();
        }

    }

    public ResponseDto<ShippingAddressDto> get(Integer shippingAddressId) {
        return this.shippingAddressRepository
                .findByShippingAddressIdAndDeletedAtIsNull(shippingAddressId)
                .map(shipping -> ResponseDto.<ShippingAddressDto>builder()
                        .success(true)
                        .message("Ok")
                        .data(this.shippingAddressMapper.toDto(shipping))
                        .build())
                .orElse(ResponseDto.<ShippingAddressDto>builder()
                        .success(false)
                        .message(String.format("This is %s shipping address is not found", shippingAddressId))
                        .build());
    }

    public ResponseDto<ShippingAddressDto> update(ShippingAddressDto dto, Integer shippingAddressId) {

        try {
            return shippingAddressRepository.findByShippingAddressIdAndDeletedAtIsNull(shippingAddressId)
                    .map(shippingAddress -> {
                                shippingAddress.setUpdatedAt(LocalDateTime.now());
                                shippingAddressMapper.update(dto,shippingAddress);
                                shippingAddressRepository.save(shippingAddress);
                                return ResponseDto.<ShippingAddressDto>builder()
                                        .success(true)
                                        .message("Successful updated")
                                        .data(this.shippingAddressMapper.toDtoNot(shippingAddress))
                                        .build();
                            }
                    ).orElse(ResponseDto.<ShippingAddressDto>builder()
                            .success(false)
                            .message(String.format("This is %s shipping address is not found", shippingAddressId))
                            .build());

        } catch (Exception e) {
            return ResponseDto.<ShippingAddressDto>builder()
                    .success(false)
                    .message(e.getMessage())
                    .build();
        }

    }

    public ResponseDto<ShippingAddressDto> delete(Integer shippingAddressId) {
        try {
            return shippingAddressRepository.findByShippingAddressIdAndDeletedAtIsNull(shippingAddressId)
                    .map(shippingAddress -> {
                                shippingAddress.setDeletedAt(LocalDateTime.now());
                                shippingAddress.setStatus(false);
                                shippingAddressRepository.save(shippingAddress);
                                return ResponseDto.<ShippingAddressDto>builder()
                                        .success(true)
                                        .message("Successful updated")
                                        .data(this.shippingAddressMapper.toDtoNot(shippingAddress))
                                        .build();
                            }
                    ).orElse(ResponseDto.<ShippingAddressDto>builder()
                            .success(false)
                            .message(String.format("This is %s shipping address is not found", shippingAddressId))
                            .build());

        } catch (Exception e) {
            return ResponseDto.<ShippingAddressDto>builder()
                    .success(false)
                    .message(e.getMessage())
                    .build();
        }
    }
}

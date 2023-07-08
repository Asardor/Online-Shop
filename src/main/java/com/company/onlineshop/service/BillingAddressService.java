package com.company.onlineshop.service;

import com.company.onlineshop.dto.BillingAddressDto;
import com.company.onlineshop.dto.ResponseDto;
import com.company.onlineshop.model.BillingAddress;
import com.company.onlineshop.repository.BillingAddressRepository;
import com.company.onlineshop.service.mapper.BillingAddressMapper;
import com.company.onlineshop.service.validate.BillingAddressValidate;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BillingAddressService {

    private final BillingAddressMapper billingAddressMapper;
    private final BillingAddressValidate billingAddressValidate;
    private final BillingAddressRepository billingAddressRepository;

    public ResponseDto<BillingAddressDto> create(BillingAddressDto dto) {
        try {
            BillingAddress billingAddress = this.billingAddressMapper.toEntity(dto);
            billingAddress.setCreatedAt(LocalDateTime.now());
            this.billingAddressRepository.save(billingAddress);
            return ResponseDto.<BillingAddressDto>builder()
                    .success(true)
                    .message("Ok")
                    .data(this.billingAddressMapper.toDtoNotCart(billingAddress))
                    .build();
        } catch (Exception e) {
            return ResponseDto.<BillingAddressDto>builder()
                    .message(e.getMessage())
                    .success(false)
                    .build();
        }
    }

    public ResponseDto<BillingAddressDto> get(Integer billingAddressId) {
        return this.billingAddressRepository.findByBillingAddressIdAndDeletedAtIsNull(billingAddressId)
                .map(b -> ResponseDto.<BillingAddressDto>builder()
                        .success(true)
                        .message("Ok")
                        .data(billingAddressMapper.toDto(b))
                        .build())
                .orElse(ResponseDto.<BillingAddressDto>
                                builder()
                        .success(false)
                        .message("Billing Address is not found")
                        .build());


    }

    public ResponseDto<BillingAddressDto> update(BillingAddressDto dto, Integer billingAddressId) {
        Optional<BillingAddress> optional = this.billingAddressRepository.findByBillingAddressIdAndDeletedAtIsNull(billingAddressId);
        if (optional.isEmpty()) {
            return ResponseDto.<BillingAddressDto>builder()
                    .message("BillingAddressId is not found!")
                    .build();
        }
        BillingAddress customer = optional.get();
        this.billingAddressMapper.update(dto, customer);
        this.billingAddressRepository.save(customer);
        return ResponseDto.<BillingAddressDto>builder()
                .success(true)
                .message("Billing Address successful updated and save database")
                .data(this.billingAddressMapper.toDtoNotCart(customer))
                .build();
    }
    public ResponseDto<BillingAddressDto> delete(Integer billingAddressId) {
        try {
            return billingAddressRepository.findByBillingAddressIdAndDeletedAtIsNull(billingAddressId)
                    .map(billingAddress -> {
                                billingAddress.setDeletedAt(LocalDateTime.now());
                                billingAddress.setStatus(false);
                                billingAddressRepository.save(billingAddress);
                                return ResponseDto.<BillingAddressDto>builder()
                                        .success(true)
                                        .message("Successful updated")
                                        .data(this.billingAddressMapper.toDtoNotCart(billingAddress))
                                        .build();
                            }
                    ).orElse(ResponseDto.<BillingAddressDto>builder()
                            .success(false)
                            .message(String.format("This %s billingAddressId is not found", billingAddressId))
                            .build());
        } catch (Exception e) {
            return ResponseDto.<BillingAddressDto>builder()
                    .success(false)
                    .message(e.getMessage())
                    .build();
        }
    }
}

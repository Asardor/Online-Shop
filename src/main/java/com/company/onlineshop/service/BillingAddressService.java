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
                    .massage("Ok")
                    .data(this.billingAddressMapper.toDtoNotCart(billingAddress))
                    .build();
        } catch (Exception e) {
            return ResponseDto.<BillingAddressDto>builder()
                    .massage(e.getMessage())
                    .success(false)
                    .build();
        }
    }

    public ResponseDto<BillingAddressDto> get(Integer billingAddressId) {
      /*/  try {
            Optional<BillingAddress> optional = this.billingAddressRepository.findByBillingAddressIdAndDeletedAtIsNull(billingAddressId);
            if (optional.isEmpty()) {
                return ResponseDto.<BillingAddressDto>builder()
                        .massage("Billing Address is not found!")
                        .build();
            }

            return ResponseDto.<BillingAddressDto>builder()
                    .success(true)
                    .massage("OK")
                    .data(this.billingAddressMapper.toDto(optional.get()))
                    .build();
        } catch (Exception e) {
            return ResponseDto.<BillingAddressDto>builder()
                    .massage(e.getMessage())
                    .success(false)
                    .build();
        }
        */
        return this.billingAddressRepository.findByBillingAddressIdAndDeletedAtIsNull(billingAddressId)
                .map(b ->ResponseDto.<BillingAddressDto>builder()
                        .success(true)
                        .massage("Ok")
                        .data(billingAddressMapper.toDto(b))
                        .build())
                .orElse(ResponseDto.<BillingAddressDto>
                        builder()
                        .success(false)
                        .massage("Billing Address is not found")
                        .build());




    }

    public ResponseDto<BillingAddressDto> update(BillingAddressDto dto, Integer billingAddressId) {
        try {
            Optional<BillingAddress> optional = this.billingAddressRepository.findByBillingAddressIdAndDeletedAtIsNull(billingAddressId);
            if (optional.isEmpty()) {
                return ResponseDto.<BillingAddressDto>builder()
                        .massage("Billing Address is not found!")
                        .build();
            }
            BillingAddress billingAddress= optional.get();
            billingAddress.setUpdatedAt(LocalDateTime.now());
            this.billingAddressMapper.update(dto, billingAddress);
            this.billingAddressRepository.save(billingAddress);
            return ResponseDto.<BillingAddressDto>builder()
                    .success(true)
                    .massage("OK")
                    .data(this.billingAddressMapper.toDtoNotCart(optional.get()))
                    .build();
        } catch (Exception e) {
            return ResponseDto.<BillingAddressDto>builder()
                    .massage(e.getMessage())
                    .success(false)
                    .build();
        }
    }

    public ResponseDto<BillingAddressDto> delete(Integer billingAddressId) {
        try {
            Optional<BillingAddress> optional = this.billingAddressRepository.findByBillingAddressIdAndDeletedAtIsNull(billingAddressId);
            if (optional.isEmpty()) {
                return ResponseDto.<BillingAddressDto>builder()
                        .massage("Billing Address is not found!")
                        .build();
            }
            BillingAddress billingAddress= optional.get();
            billingAddress.setDeletedAt(LocalDateTime.now());
            this.billingAddressRepository.save(billingAddress);
            return ResponseDto.<BillingAddressDto>builder()
                    .success(true)
                    .massage("OK")
                    .data(this.billingAddressMapper.toDtoNotCart(optional.get()))
                    .build();
        } catch (Exception e) {
            return ResponseDto.<BillingAddressDto>builder()
                    .massage(e.getMessage())
                    .success(false)
                    .build();
        }
    }
}

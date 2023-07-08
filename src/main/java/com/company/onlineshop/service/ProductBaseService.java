package com.company.onlineshop.service;

import com.company.onlineshop.dto.ErrorDto;
import com.company.onlineshop.dto.ProductBaseDto;
import com.company.onlineshop.dto.ResponseDto;
import com.company.onlineshop.model.ProductBase;
import com.company.onlineshop.repository.ProductBaseRepository;
import com.company.onlineshop.repository.ProductRepository;
import com.company.onlineshop.service.mapper.ProductBaseMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class ProductBaseService {
    private final ProductBaseMapper productBaseMapper;
    private final ProductBaseRepository productBaseRepository;

    public ResponseDto<ProductBaseDto> create(ProductBaseDto dto) {
        try {
            ProductBase productBase = productBaseMapper.toEntity(dto);
            productBase.setCreatedAt(LocalDateTime.now());
            productBase.setStatus(true);
            productBaseRepository.save(productBase);
            return ResponseDto.<ProductBaseDto>builder()
                    .success(true)
                    .massage("ProductBase successful create!")
                    .data(this.productBaseMapper.toDto(productBase))
                    .build();
        } catch (Exception e) {
            return ResponseDto.<ProductBaseDto>builder()
                    .success(false)
                    .massage(String.format("ProductBase while saving error!" + e.getMessage()))
                    .build();
        }
    }

    public ResponseDto<ProductBaseDto> get(Integer prodBaseId) {
        return this.productBaseRepository.findByProdIdAndDeletedAtIsNull(prodBaseId)
                .map(productBase -> ResponseDto.<ProductBaseDto>builder()
                        .success(true)
                        .massage("ProductBase successful get!")
                        .data(this.productBaseMapper.toDto(productBase))
                        .build())
                .orElse(ResponseDto.<ProductBaseDto>builder()
                        .success(false)
                        .massage(String.format("ProductBase with %s id is not found!", prodBaseId))
                        .build());
    }

    public ResponseDto<ProductBaseDto> update(Integer prodBaseId, ProductBaseDto dto) {
        try {
            return this.productBaseRepository.findByProdIdAndDeletedAtIsNull(prodBaseId)
                    .map(productBase -> {
                        productBase.setUpdatedAt(LocalDateTime.now());
                        productBaseMapper.update(productBase, dto);
                        productBaseRepository.save(productBase);
                        return ResponseDto.<ProductBaseDto>builder()
                                .massage("ProductBase successful update!")
                                .success(true)
                                .data(this.productBaseMapper.toDto(productBase))
                                .build();
                    })
                    .orElse(ResponseDto.<ProductBaseDto>builder()
                            .success(false)
                            .massage(String.format("ProductBase with %s id is not found!", prodBaseId))
                            .build());
        } catch (Exception e) {
            return ResponseDto.<ProductBaseDto>builder()
                    .massage(String.format("ProductBase while saving error"+e.getMessage()))
                    .build();
        }
    }

    public ResponseDto<ProductBaseDto> delete(Integer prodBaseId) {
        try {
            return this.productBaseRepository.findByProdIdAndDeletedAtIsNull(prodBaseId)
                    .map(productBase -> {
                        productBase.setUpdatedAt(LocalDateTime.now());
                       productBase.setStatus(false);
                        productBaseRepository.save(productBase);
                        return ResponseDto.<ProductBaseDto>builder()
                                .massage("ProductBase successful delete!")
                                .success(true)
                                .data(this.productBaseMapper.toDto(productBase))
                                .build();
                    })
                    .orElse(ResponseDto.<ProductBaseDto>builder()
                            .success(false)
                            .massage(String.format("ProductBase with %s id is not found!", prodBaseId))
                            .build());
        } catch (Exception e) {
            return ResponseDto.<ProductBaseDto>builder()
                    .massage(String.format("ProductBase while saving error"+e.getMessage()))
                    .build();
        }
    }
}

package com.company.onlineshop.service;

import com.company.onlineshop.dto.ProductDto;
import com.company.onlineshop.dto.ResponseDto;
import com.company.onlineshop.model.Product;
import com.company.onlineshop.repository.ProductBaseRepository;
import com.company.onlineshop.repository.ProductRepository;
import com.company.onlineshop.service.mapper.ProductMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductMapper productMapper;
    private final ProductRepository productRepository;

    public ResponseDto<ProductDto> create(ProductDto dto) {
        try {
            Product product = productMapper.toEntity(dto);
            product.setCratedAt(LocalDateTime.now());
            product.setStatus(true);
            productRepository.save(product);
            return ResponseDto.<ProductDto>builder()
                    .massage("Product successful create!")
                    .success(true)
                    .data(productMapper.toDto(product))
                    .build();
        } catch (Exception e) {
            return ResponseDto.<ProductDto>builder()
                    .massage(String.format("Product while saving error" + e.getMessage()))
                    .success(false)
                    .build();
        }
    }

    public ResponseDto<ProductDto> get(Integer productId) {
        return this.productRepository.findByProductIdAndDeletedAtIsNull(productId)
                .map(product -> ResponseDto.<ProductDto>builder()
                        .success(true)
                        .massage("Product successful get!")
                        .data(productMapper.toDto(product))
                        .build())
                .orElse(ResponseDto.<ProductDto>builder()
                        .massage(String.format("Product with %s id is not found", productId))
                        .build());
    }

    public ResponseDto<ProductDto> update(Integer productId, ProductDto dto) {
        try {
            return this.productRepository.findByProductIdAndDeletedAtIsNull(productId)
                    .map(product -> {
                        product.setUpdatedAt(LocalDateTime.now());
                        productMapper.update(product, dto);
                        productRepository.save(product);
                        return ResponseDto.<ProductDto>builder()
                                .success(true)
                                .massage("Product successful update!")
                                .data(productMapper.toDto(product))
                                .build();
                    })
                    .orElse(ResponseDto.<ProductDto>builder()
                            .massage(String.format("Product with %s id is not found", productId))
                            .build());
        } catch (Exception e) {
            return ResponseDto.<ProductDto>builder()
                    .massage(String.format("Product while saving error" + e.getMessage()))
                    .success(false)
                    .build();
        }
    }

    public ResponseDto<ProductDto> delete(Integer productId) {
        try {
            return this.productRepository.findByProductIdAndDeletedAtIsNull(productId)
                    .map(product -> {
                        product.setDeletedAt(LocalDateTime.now());
                        product.setStatus(false);
                        productRepository.save(product);
                        return ResponseDto.<ProductDto>builder()
                                .success(true)
                                .massage("Product successful delete!")
                                .data(productMapper.toDto(product))
                                .build();
                    })
                    .orElse(ResponseDto.<ProductDto>builder()
                            .massage(String.format("Product with %s id is not found", productId))
                            .build());
        } catch (Exception e) {
            return ResponseDto.<ProductDto>builder()
                    .massage(String.format("Product while saving error" + e.getMessage()))
                    .success(false)
                    .build();
        }
    }

    public ResponseDto<Page<ProductDto>> getSearch(Map<String, String> params) {
        int page = 0, size = 10;
        if (params.containsKey("size")) {
            size = Integer.parseInt(params.get("size"));
        }
        if (params.containsKey("page")) {
            page = Integer.parseInt(params.get("page"));
        }
        Page<ProductDto> products = this.productRepository.getProductBasicSearch(
                params.get("productId") == null ? null : Integer.parseInt(params.get("productId")),
                params.get("name"),
                params.get("receivedPrice") == null ? null : Double.parseDouble(params.get("receivedPrice")),
                params.get("sellingPrice") == null ? null : Double.parseDouble(params.get("sellingPrice")),
                params.get("prodMass") == null ? null : Double.parseDouble(params.get("prodMass")),
                params.get("amount") == null ? null : Double.parseDouble(params.get("amount")),
                PageRequest.of(page, size)
        ).map(productMapper::toDto);
        if (products.isEmpty()){
            return ResponseDto.<Page<ProductDto>>builder()
                    .success(false)
                    .massage("Product is not found!")
                    .build();
        }
        return ResponseDto.<Page<ProductDto>>builder()
                .massage("Product successful getSearch!")
                .success(true)
                .data(products)
                .build();
    }
}



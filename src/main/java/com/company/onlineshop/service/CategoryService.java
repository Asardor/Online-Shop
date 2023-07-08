package com.company.onlineshop.service;

import com.company.onlineshop.dto.CategoryDto;
import com.company.onlineshop.dto.ResponseDto;
import com.company.onlineshop.model.Category;
import com.company.onlineshop.repository.CategoryRepository;
import com.company.onlineshop.service.mapper.CategoryMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CategoryService {
     private final CategoryRepository categoryRepository;
     private final CategoryMapper categoryMapper;

    public ResponseDto<CategoryDto> create(CategoryDto dto) {
        try {
            Category category=this.categoryMapper.toEntity(dto);
            category.setCreatedAt(LocalDateTime.now());
            category.setStatus(true);
            categoryRepository.save(category);
            return ResponseDto.<CategoryDto>builder()
                    .massage("Category successful create!")
                    .success(true)
                    .data(this.categoryMapper.toDto(category))
                    .build();
        }catch (Exception e){
            return ResponseDto.<CategoryDto>builder()
                    .success(false)
                    .massage("Category while saving error" + e.getMessage())
                    .build();
        }
    }

    public ResponseDto<CategoryDto> get(Integer categoryId) {
        return this.categoryRepository.findByCategoryIdAndDeletedAtIsNull(categoryId)
                .map(category -> ResponseDto.<CategoryDto>builder()
                        .success(true)
                        .massage("Category successful get!")
                        .data(this.categoryMapper.toDto(category))
                        .build())
                .orElse(ResponseDto.<CategoryDto>builder()
                        .success(false)
                        .massage(String.format("Category with %s id is not found!", categoryId))
                        .build());
    }

    public ResponseDto<CategoryDto> update(CategoryDto dto, Integer categoryId) {
        try {
            return this.categoryRepository.findByCategoryIdAndDeletedAtIsNull(categoryId)
                    .map(category -> {
                        category.setUpdatedAt(LocalDateTime.now());
                        categoryMapper.update(category, dto);
                        this.categoryRepository.save(category);
                        return ResponseDto.<CategoryDto>builder()
                                .success(true)
                                .massage("Category successful update!")
                                .data(categoryMapper.toDto(category))
                                .build();
                    })
                    .orElse(ResponseDto.<CategoryDto>builder()
                            .success(false)
                            .massage(String.format("Category with %s id is not found!", categoryId))
                            .build());
        }catch (Exception e){
            return ResponseDto.<CategoryDto>builder()
                    .success(false)
                    .massage(String.format("Category with %s id is not found!", categoryId))
                    .build();
        }
    }
    public ResponseDto<CategoryDto> delete(Integer categoryId) {
        try {
            return this.categoryRepository.findByCategoryIdAndDeletedAtIsNull(categoryId)
                    .map(category -> {
                        category.setDeletedAt(LocalDateTime.now());
                        category.setStatus(false);
                        this.categoryRepository.save(category);
                        return ResponseDto.<CategoryDto>builder()
                                .success(true)
                                .massage("Category successful delete!")
                                .data(categoryMapper.toDto(category))
                                .build();
                    })
                    .orElse(ResponseDto.<CategoryDto>builder()
                            .success(false)
                            .massage(String.format("Category with %s id is not found!", categoryId))
                            .build());
        }catch (Exception e){
            return ResponseDto.<CategoryDto>builder()
                    .success(false)
                    .massage(String.format("Category with %s id is not found!", categoryId))
                    .build();
        }
    }

    public ResponseDto<List<CategoryDto>> getSearch() {
        return ResponseDto.<List<CategoryDto>>builder()
                .success(true)
                .massage("Category successful getAll!")
                .data(categoryRepository.findAll().stream().map(categoryMapper::toDto).toList())
                .build();
    }
}

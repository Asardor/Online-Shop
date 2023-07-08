package com.company.onlineshop.controller;

import com.company.onlineshop.dto.CategoryDto;
import com.company.onlineshop.dto.ResponseDto;
import com.company.onlineshop.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("category")
@RequiredArgsConstructor
public class CategoryController {
    private final CategoryService categoryService;

    @PostMapping("/create")
    public ResponseDto<CategoryDto> create(@RequestBody CategoryDto dto) {
        return this.categoryService.create(dto);
    }

    @GetMapping("/get/{id}")
    public ResponseDto<CategoryDto> get(@PathVariable("id") Integer categoryId) {
        return this.categoryService.get(categoryId);
    }

    @PutMapping("/update/{id}")
    public ResponseDto<CategoryDto> update(@PathVariable("id") Integer categoryId, CategoryDto dto) {
        return this.categoryService.update(dto, categoryId);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseDto<CategoryDto> delete(@PathVariable("id") Integer categoryId) {
        return this.categoryService.delete(categoryId);
    }

    @GetMapping("/get-all")
    public ResponseDto<List<CategoryDto>> getSearch() {
        return this.categoryService.getSearch();
    }

}

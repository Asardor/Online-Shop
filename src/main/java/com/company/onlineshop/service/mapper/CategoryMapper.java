package com.company.onlineshop.service.mapper;

import com.company.onlineshop.dto.CategoryDto;
import com.company.onlineshop.model.Category;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public abstract class CategoryMapper {

    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "deletedAt", ignore = true)
   public abstract Category toEntity(CategoryDto dto);

    public abstract CategoryDto toDto(Category category);

    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "deletedAt", ignore = true)
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    public abstract void update(@MappingTarget Category category, CategoryDto dto);
}

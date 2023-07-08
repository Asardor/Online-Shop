package com.company.onlineshop.service.mapper;

import com.company.onlineshop.dto.ProductBaseDto;
import com.company.onlineshop.model.ProductBase;
import org.mapstruct.*;
import org.springframework.scheduling.annotation.Scheduled;

@Mapper(componentModel = "spring")
public abstract class ProductBaseMapper {

    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "deletedAt", ignore = true)
    public abstract ProductBase toEntity(ProductBaseDto dto);

    public abstract ProductBaseDto toDto(ProductBase productBase);

    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "deletedAt", ignore = true)
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    public abstract void update(@MappingTarget ProductBase productBase, ProductBaseDto dto);
}

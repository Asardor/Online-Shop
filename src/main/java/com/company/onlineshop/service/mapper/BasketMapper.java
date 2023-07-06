package com.company.onlineshop.service.mapper;

import com.company.onlineshop.dto.BasketDto;
import com.company.onlineshop.model.Basket;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public abstract class BasketMapper {

    public abstract BasketDto toDto(Basket basket);

//    @Mapping(target = "products", ignore = true)
    public abstract BasketDto toDtoNotProduct(Basket basket);

    @Mapping(target = "basketId", ignore = true)
//    @Mapping(target = "products", ignore = true)
    public abstract Basket toEntity(BasketDto dto);

    @Mapping(target = "basketId", ignore = true)
//    @Mapping(target = "products", ignore = true)
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    public abstract Basket update(BasketDto dto, @MappingTarget Basket basket);

}

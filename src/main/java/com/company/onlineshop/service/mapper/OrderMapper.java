package com.company.onlineshop.service.mapper;

import com.company.onlineshop.dto.CustomerDto;
import com.company.onlineshop.dto.OrderDto;
import com.company.onlineshop.model.Customer;
import com.company.onlineshop.model.Order;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public abstract class OrderMapper {

    public abstract OrderDto toDto(Order order);

    @Mapping(target = "baskets", ignore = true)
    public abstract OrderDto toDtoNotBasket(Order order);


    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "deletedAt", ignore = true)
    @Mapping(target = "orderId", ignore = true)
    @Mapping(target = "baskets", ignore = true)
    public abstract Order toEntity(OrderDto dto);

    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "deletedAt", ignore = true)
    @Mapping(target = "orderId", ignore = true)
    @Mapping(target = "baskets", ignore = true)
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    public abstract Order update(OrderDto dto, @MappingTarget Order order);

}

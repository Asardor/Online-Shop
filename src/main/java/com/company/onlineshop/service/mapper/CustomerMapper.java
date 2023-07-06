package com.company.onlineshop.service.mapper;

import com.company.onlineshop.dto.CustomerDto;
import com.company.onlineshop.model.Customer;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public abstract class CustomerMapper {

    public abstract CustomerDto toDto(Customer customer);

    @Mapping(target = "basket", ignore = true)
    @Mapping(target = "orders", ignore = true)
    public abstract CustomerDto toDtoNotBasketAndOrders(Customer customer);

    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "deletedAt", ignore = true)
    @Mapping(target = "customerId", ignore = true)
    @Mapping(target = "basket", ignore = true)
    @Mapping(target = "orders", ignore = true)
    public abstract Customer  toEntity(CustomerDto dto);

    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "deletedAt", ignore = true)
    @Mapping(target = "customerId", ignore = true)
    @Mapping(target = "basket", ignore = true)
    @Mapping(target = "orders", ignore = true)
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    public abstract Customer  update(CustomerDto dto, @MappingTarget Customer customer);

}

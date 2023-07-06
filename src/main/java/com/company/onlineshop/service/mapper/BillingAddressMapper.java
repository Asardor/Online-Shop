package com.company.onlineshop.service.mapper;

import com.company.onlineshop.dto.BillingAddressDto;
import com.company.onlineshop.model.BillingAddress;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring")
public abstract class BillingAddressMapper {

    public abstract BillingAddressDto toDto(BillingAddress billingAddress);
    public abstract BillingAddressDto toDtoNotCart(BillingAddress billingAddress);
    public abstract BillingAddress toEntity(BillingAddressDto dto);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    public abstract BillingAddress update(BillingAddressDto dto, @MappingTarget BillingAddress billingAddress);
}

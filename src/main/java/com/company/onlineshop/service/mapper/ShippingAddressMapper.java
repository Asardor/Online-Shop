package com.company.onlineshop.service.mapper;

import com.company.onlineshop.dto.ShippingAddressDto;
import com.company.onlineshop.model.ShippingAddress;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public abstract class ShippingAddressMapper {


    public abstract ShippingAddressDto toDto(ShippingAddress shippingAddress);
//    public abstract ShippingAddressDto toDtoNot(ShippingAddress shippingAddress);


    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "deletedAt", ignore = true)
    @Mapping(target = "shippingAddressId", ignore = true)
    public abstract ShippingAddress toEntity(ShippingAddressDto dto);

    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "deletedAt", ignore = true)
    @Mapping(target = "shippingAddressId", ignore = true)
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    public abstract ShippingAddress update(ShippingAddressDto dto, @MappingTarget ShippingAddress shippingAddress);
}

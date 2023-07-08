package com.company.onlineshop.service.mapper;

import com.company.onlineshop.dto.ReportsDto;
import com.company.onlineshop.model.Reports;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public abstract class ReportsMapper {

    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "deletedAt", ignore = true)
   public abstract Reports toEntity(ReportsDto dto);

    public abstract ReportsDto toDto(Reports reports);

    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "deletedAt", ignore = true)
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    public abstract void update(@MappingTarget Reports reports, ReportsDto dto);
}

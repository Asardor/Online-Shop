package com.company.onlineshop.service.mapper;

import com.company.onlineshop.dto.ImageDto;
import com.company.onlineshop.model.Image;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ImageMapper {
     ImageDto toDto(Image build);
}

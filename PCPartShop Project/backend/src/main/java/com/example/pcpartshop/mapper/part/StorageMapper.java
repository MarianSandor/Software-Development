package com.example.pcpartshop.mapper.part;

import com.example.pcpartshop.dto.part.PSUDto;
import com.example.pcpartshop.dto.part.StorageDto;
import com.example.pcpartshop.model.part.PSU;
import com.example.pcpartshop.model.part.Storage;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface StorageMapper {
    StorageDto toDto(Storage storage);

    Storage fromDto(StorageDto storageDto);
}

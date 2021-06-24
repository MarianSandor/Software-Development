package com.example.pcpartshop.mapper;

import com.example.pcpartshop.dto.ConfigurationDto;
import com.example.pcpartshop.dto.part.minimal.MinimalPartDto;
import com.example.pcpartshop.model.Configuration;
import com.example.pcpartshop.model.part.*;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Mapper(componentModel = "spring")
public interface ConfigurationMapper {

    @Mapping(source = "cpu", target = "cpu", qualifiedByName = "getCPU")
    @Mapping(source = "gpu", target = "gpu", qualifiedByName = "getGPU")
    @Mapping(source = "memory", target = "memory", qualifiedByName = "getMemory")
    @Mapping(source = "motherboard", target = "motherboard", qualifiedByName = "getMotherboard")
    @Mapping(source = "storage", target = "storage", qualifiedByName = "getStorage")
    @Mapping(source = "psu", target = "psu", qualifiedByName = "getPsu")
    @Mapping(source = "pcCase", target = "pcCase", qualifiedByName = "getPcCase")
    @Mapping(source = "dateCreated", target = "dateCreated", qualifiedByName = "getDateCreated")
    ConfigurationDto toDto(Configuration configuration);

    @Named("getCPU")
    static MinimalPartDto getCPU(CPU cpu) {
        return MinimalPartDto.builder()
                .id(cpu.getId())
                .model(cpu.getModel())
                .brand(cpu.getBrand())
                .build();
    }

    @Named("getGPU")
    static MinimalPartDto getGPU(GPU gpu) {
        return MinimalPartDto.builder()
                .id(gpu.getId())
                .model(gpu.getModel())
                .brand(gpu.getBrand())
                .build();
    }

    @Named("getMemory")
    static MinimalPartDto getMemory(Memory memory) {
        return MinimalPartDto.builder()
                .id(memory.getId())
                .model(memory.getModel())
                .brand(memory.getBrand())
                .build();
    }

    @Named("getMotherboard")
    static MinimalPartDto getMotherboard(Motherboard motherboard) {
        return MinimalPartDto.builder()
                .id(motherboard.getId())
                .model(motherboard.getModel())
                .brand(motherboard.getBrand())
                .build();
    }

    @Named("getStorage")
    static MinimalPartDto getStorage(Storage storage) {
        return MinimalPartDto.builder()
                .id(storage.getId())
                .model(storage.getModel())
                .brand(storage.getBrand())
                .build();
    }

    @Named("getPsu")
    static MinimalPartDto getPsu(PSU psu) {
        return MinimalPartDto.builder()
                .id(psu.getId())
                .model(psu.getModel())
                .brand(psu.getBrand())
                .build();
    }

    @Named("getPcCase")
    static MinimalPartDto getPcCase(PcCase pcCase) {
        return MinimalPartDto.builder()
                .id(pcCase.getId())
                .model(pcCase.getModel())
                .brand(pcCase.getBrand())
                .build();
    }

    @Named("getDateCreated")
    static String getDateCreated(LocalDateTime dateCreated) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        return dateCreated.format(formatter);
    }
}

package com.example.pcpartshop.service;

import com.example.pcpartshop.dto.ConfigurationCreationDto;
import com.example.pcpartshop.dto.ConfigurationDto;
import com.example.pcpartshop.mapper.ConfigurationMapper;
import com.example.pcpartshop.model.Configuration;
import com.example.pcpartshop.model.User;
import com.example.pcpartshop.model.part.*;
import com.example.pcpartshop.repository.ConfigurationRepository;
import com.example.pcpartshop.repository.UserRepository;
import com.example.pcpartshop.repository.part.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ConfigurationService {
    private final ConfigurationRepository configurationRepository;
    private final UserRepository userRepository;
    private final CPURepository cpuRepository;
    private final GPURepository gpuRepository;
    private final MemoryRepository memoryRepository;
    private final MotherboardRepository motherboardRepository;
    private final PcCaseRepository pcCaseRepository;
    private final PSURepository psuRepository;
    private final StorageRepository storageRepository;

    private final ConfigurationMapper configurationMapper;

    private Configuration findById(Long id) {
        return configurationRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Patient not found: " + id));
    }

    public List<ConfigurationDto> findAll() {
        return configurationRepository.findAll().stream()
                .sorted(Comparator.comparing(Configuration::getDateCreated).reversed())
                .map(configurationMapper::toDto)
                .collect(Collectors.toList());
    }

    public List<ConfigurationDto> configurationForUser(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("User not found: " + userId));

        return configurationRepository.findConfigurationByUser(user).stream()
                .sorted(Comparator.comparing(Configuration::getDateCreated).reversed())
                .map(configurationMapper::toDto)
                .collect(Collectors.toList());
    }

    public ConfigurationDto create(ConfigurationCreationDto configurationCreationDto) {
        User user = userRepository.findById(configurationCreationDto.getUserId())
                .orElseThrow(() -> new EntityNotFoundException("User not found: " + configurationCreationDto.getUserId()));
        CPU cpu = cpuRepository.findById(configurationCreationDto.getCpuId())
                .orElseThrow(() -> new EntityNotFoundException("CPU not found: " + configurationCreationDto.getCpuId()));
        GPU gpu = gpuRepository.findById(configurationCreationDto.getGpuId())
                .orElseThrow(() -> new EntityNotFoundException("GPU not found: " + configurationCreationDto.getGpuId()));
        Memory memory = memoryRepository.findById(configurationCreationDto.getMemoryId())
                .orElseThrow(() -> new EntityNotFoundException("Memory not found: " + configurationCreationDto.getMemoryId()));
        Motherboard motherboard = motherboardRepository.findById(configurationCreationDto.getMotherboardId())
                .orElseThrow(() -> new EntityNotFoundException("Motherboard not found: " + configurationCreationDto.getMotherboardId()));
        PcCase pcCase = pcCaseRepository.findById(configurationCreationDto.getPcCaseId())
                .orElseThrow(() -> new EntityNotFoundException("PcCase not found: " + configurationCreationDto.getPcCaseId()));
        PSU psu = psuRepository.findById(configurationCreationDto.getPsuId())
                .orElseThrow(() -> new EntityNotFoundException("PSU not found: " + configurationCreationDto.getPsuId()));
        Storage storage = storageRepository.findById(configurationCreationDto.getStorageId())
                .orElseThrow(() -> new EntityNotFoundException("Storage not found: " + configurationCreationDto.getStorageId()));
        double total = List.of(cpu, gpu, memory, motherboard, storage, psu, pcCase).stream()
                        .map(Part::getPrice)
                        .reduce(0.0, Double::sum);

        Configuration configuration = Configuration.builder()
                .user(user)
                .cpu(cpu)
                .gpu(gpu)
                .memory(memory)
                .motherboard(motherboard)
                .pcCase(pcCase)
                .psu(psu)
                .storage(storage)
                .total(total)
                .description(configurationCreationDto.getDescription())
                .dateCreated(LocalDateTime.now())
                .build();

        return configurationMapper.toDto(configurationRepository.save(configuration));
    }

    public ConfigurationDto get(Long id) {
        return configurationMapper.toDto(findById(id));
    }

    public void delete(Long id) {
        configurationRepository.deleteById(id);
    }
}

package com.example.pcpartshop.service.part;

import com.example.pcpartshop.dto.part.GPUDto;
import com.example.pcpartshop.dto.part.MemoryDto;
import com.example.pcpartshop.mapper.part.MemoryMapper;
import com.example.pcpartshop.model.part.GPU;
import com.example.pcpartshop.model.part.Memory;
import com.example.pcpartshop.repository.part.MemoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

import static com.example.pcpartshop.repository.specification.PartSpecification.*;

@Service
@RequiredArgsConstructor
public class MemoryService extends PartService{
    private final MemoryRepository memoryRepository;
    private final MemoryMapper memoryMapper;

    private Memory findById(Long id) {
        return memoryRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Memory not found: " + id));
    }

    public List<MemoryDto> findAll() {
        return memoryRepository.findAll().stream()
                .map(memoryMapper::toDto)
                .collect(Collectors.toList());
    }

    public List<MemoryDto> search(String query) {
        return memoryRepository.findAll(brandLikeMemory(query).or(modelLikeMemory(query))).stream()
                .map(memoryMapper::toDto)
                .collect(Collectors.toList());
    }

    public MemoryDto create(MemoryDto memoryDto) {
        return memoryMapper.toDto(memoryRepository.save(
                memoryMapper.fromDto(memoryDto)
        ));
    }

    public MemoryDto edit(Long id, MemoryDto memoryDto) {
        Memory currMemory = findById(id);

        setFromDto(currMemory, memoryDto);

        currMemory.setFrequency(memoryDto.getFrequency());
        currMemory.setType(memoryDto.getType());
        currMemory.setSize(memoryDto.getSize());

        return memoryMapper.toDto(memoryRepository.save(currMemory));
    }

    public MemoryDto updateQuantity(Long id, int quantity) {
        Memory currMemory = findById(id);

        currMemory.setQuantity(quantity);

        return memoryMapper.toDto(memoryRepository.save(currMemory));
    }


    public MemoryDto sell(Long id, int quantity) {
        Memory currMemory = findById(id);
        int currQuantity = currMemory.getQuantity();

        currMemory.setQuantity(currQuantity - quantity);

        return memoryMapper.toDto(memoryRepository.save(currMemory));
    }

    public MemoryDto get(Long id) {
        return memoryMapper.toDto(findById(id));
    }

    public void delete(Long id) {
        memoryRepository.deleteById(id);
    }
}

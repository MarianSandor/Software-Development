package com.example.pcpartshop.service.part;

import com.example.pcpartshop.dto.part.CPUDto;
import com.example.pcpartshop.mapper.part.CPUMapper;
import com.example.pcpartshop.model.part.CPU;
import com.example.pcpartshop.repository.part.CPURepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

import static com.example.pcpartshop.repository.specification.PartSpecification.brandLikeCPU;
import static com.example.pcpartshop.repository.specification.PartSpecification.modelLikeCPU;

@Service
@RequiredArgsConstructor
public class CPUService extends PartService{
    private final CPURepository cpuRepository;
    private final CPUMapper cpuMapper;

    private CPU findById(Long id) {
        return cpuRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("CPU not found: " + id));
    }

    public List<CPUDto> findAll() {
        return cpuRepository.findAll().stream()
                .map(cpuMapper::toDto)
                .collect(Collectors.toList());
    }

    public List<CPUDto> search(String query) {
        return cpuRepository.findAll(brandLikeCPU(query).or(modelLikeCPU(query))).stream()
                .map(cpuMapper::toDto)
                .collect(Collectors.toList());
    }

    public CPUDto create(CPUDto cpuDto) {
        return cpuMapper.toDto(cpuRepository.save(
                cpuMapper.fromDto(cpuDto)
        ));
    }

    public CPUDto edit(Long id, CPUDto cpuDto) {
        CPU currCPU = findById(id);

        setFromDto(currCPU, cpuDto);

        currCPU.setFrequency(cpuDto.getFrequency());
        currCPU.setIntegratedGraphics(cpuDto.getIntegratedGraphics());
        currCPU.setCores(cpuDto.getCores());

        return cpuMapper.toDto(cpuRepository.save(currCPU));
    }

    public CPUDto updateQuantity(Long id, int quantity) {
        CPU currCPU = findById(id);

        currCPU.setQuantity(quantity);

        return cpuMapper.toDto(cpuRepository.save(currCPU));
    }

    public CPUDto sell(Long id, int quantity) {
        CPU currCPU = findById(id);
        int currQuantity = currCPU.getQuantity();

        currCPU.setQuantity(currQuantity - quantity);

        return cpuMapper.toDto(cpuRepository.save(currCPU));
    }

    public CPUDto get(Long id) {
        return cpuMapper.toDto(findById(id));
    }

    public void delete(Long id) {
        cpuRepository.deleteById(id);
    }
}

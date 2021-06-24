package com.example.pcpartshop.service.part;

import com.example.pcpartshop.dto.part.CPUDto;
import com.example.pcpartshop.dto.part.GPUDto;
import com.example.pcpartshop.mapper.part.GPUMapper;
import com.example.pcpartshop.model.part.CPU;
import com.example.pcpartshop.model.part.GPU;
import com.example.pcpartshop.repository.part.GPURepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

import static com.example.pcpartshop.repository.specification.PartSpecification.*;

@Service
@RequiredArgsConstructor
public class GPUService extends PartService{
    private final GPURepository gpuRepository;
    private final GPUMapper gpuMapper;

    private GPU findById(Long id) {
        return gpuRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("GPU not found: " + id));
    }

    public List<GPUDto> findAll() {
        return gpuRepository.findAll().stream()
                .map(gpuMapper::toDto)
                .collect(Collectors.toList());
    }

    public List<GPUDto> search(String query) {
        return gpuRepository.findAll(brandLikeGPU(query).or(modelLikeGPU(query))).stream()
                .map(gpuMapper::toDto)
                .collect(Collectors.toList());
    }

    public GPUDto create(GPUDto gpuDto) {
        return gpuMapper.toDto(gpuRepository.save(
                gpuMapper.fromDto(gpuDto)
        ));
    }

    public GPUDto edit(Long id, GPUDto gpuDto) {
        GPU currGPU = findById(id);

        setFromDto(currGPU, gpuDto);

        currGPU.setFrequency(gpuDto.getFrequency());
        currGPU.setVram(gpuDto.getVram());

        return gpuMapper.toDto(gpuRepository.save(currGPU));
    }

    public GPUDto updateQuantity(Long id, int quantity) {
        GPU currGPU = findById(id);

        currGPU.setQuantity(quantity);

        return gpuMapper.toDto(gpuRepository.save(currGPU));
    }

    public GPUDto sell(Long id, int quantity) {
        GPU currGPU = findById(id);
        int currQuantity = currGPU.getQuantity();

        currGPU.setQuantity(currQuantity - quantity);

        return gpuMapper.toDto(gpuRepository.save(currGPU));
    }

    public GPUDto get(Long id) {
        return gpuMapper.toDto(findById(id));
    }

    public void delete(Long id) {
        gpuRepository.deleteById(id);
    }
}

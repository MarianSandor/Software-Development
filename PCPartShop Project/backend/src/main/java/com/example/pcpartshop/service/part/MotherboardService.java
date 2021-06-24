package com.example.pcpartshop.service.part;

import com.example.pcpartshop.dto.part.MemoryDto;
import com.example.pcpartshop.dto.part.MotherboardDto;
import com.example.pcpartshop.mapper.part.MotherboardMapper;
import com.example.pcpartshop.model.part.Memory;
import com.example.pcpartshop.model.part.Motherboard;
import com.example.pcpartshop.repository.part.MotherboardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

import static com.example.pcpartshop.repository.specification.PartSpecification.*;

@Service
@RequiredArgsConstructor
public class MotherboardService extends PartService{
    private final MotherboardRepository motherboardRepository;
    private final MotherboardMapper motherboardMapper;

    private Motherboard findById(Long id) {
        return motherboardRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Motherboard not found: " + id));
    }

    public List<MotherboardDto> findAll() {
        return motherboardRepository.findAll().stream()
                .map(motherboardMapper::toDto)
                .collect(Collectors.toList());
    }

    public List<MotherboardDto> search(String query) {
        return motherboardRepository.findAll(brandLikeMotherboard(query).or(modelLikeMotherboard(query))).stream()
                .map(motherboardMapper::toDto)
                .collect(Collectors.toList());
    }

    public MotherboardDto create(MotherboardDto motherboardDto) {
        return motherboardMapper.toDto(motherboardRepository.save(
                motherboardMapper.fromDto(motherboardDto)
        ));
    }

    public MotherboardDto edit(Long id, MotherboardDto motherboardDto) {
        Motherboard currMotherboard = findById(id);

        setFromDto(currMotherboard, motherboardDto);

        currMotherboard.setFormFactor(motherboardDto.getFormFactor());
        currMotherboard.setSocket(motherboardDto.getSocket());

        return motherboardMapper.toDto(motherboardRepository.save(currMotherboard));
    }

    public MotherboardDto updateQuantity(Long id, int quantity) {
        Motherboard currMotherboard = findById(id);

        currMotherboard.setQuantity(quantity);

        return motherboardMapper.toDto(motherboardRepository.save(currMotherboard));
    }

    public MotherboardDto sell(Long id, int quantity) {
        Motherboard currMotherboard = findById(id);
        int currQuantity = currMotherboard.getQuantity();

        currMotherboard.setQuantity(currQuantity - quantity);

        return motherboardMapper.toDto(motherboardRepository.save(currMotherboard));
    }

    public MotherboardDto get(Long id) {
        return motherboardMapper.toDto(findById(id));
    }

    public void delete(Long id) {
        motherboardRepository.deleteById(id);
    }
}

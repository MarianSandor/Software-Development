package com.example.pcpartshop.service.part;

import com.example.pcpartshop.dto.part.MotherboardDto;
import com.example.pcpartshop.dto.part.PcCaseDto;
import com.example.pcpartshop.mapper.part.PcCaseMapper;
import com.example.pcpartshop.model.part.Motherboard;
import com.example.pcpartshop.model.part.PcCase;
import com.example.pcpartshop.repository.part.PcCaseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

import static com.example.pcpartshop.repository.specification.PartSpecification.*;

@Service
@RequiredArgsConstructor
public class PcCaseService extends PartService {
    private final PcCaseRepository pcCaseRepository;
    private final PcCaseMapper pcCaseMapper;

    private PcCase findById(Long id) {
        return pcCaseRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("PcCase not found: " + id));
    }

    public List<PcCaseDto> findAll() {
        return pcCaseRepository.findAll().stream()
                .map(pcCaseMapper::toDto)
                .collect(Collectors.toList());
    }

    public List<PcCaseDto> search(String query) {
        return pcCaseRepository.findAll(brandLikePcCase(query).or(modelLikePcCase(query))).stream()
                .map(pcCaseMapper::toDto)
                .collect(Collectors.toList());
    }

    public PcCaseDto create(PcCaseDto pcCaseDto) {
        return pcCaseMapper.toDto(pcCaseRepository.save(
                pcCaseMapper.fromDto(pcCaseDto)
        ));
    }

    public PcCaseDto edit(Long id, PcCaseDto pcCaseDto) {
        PcCase currPcCase = findById(id);

        setFromDto(currPcCase, pcCaseDto);

        currPcCase.setFormFactor(pcCaseDto.getFormFactor());

        return pcCaseMapper.toDto(pcCaseRepository.save(currPcCase));
    }

    public PcCaseDto updateQuantity(Long id, int quantity) {
        PcCase currPcCase = findById(id);

        currPcCase.setQuantity(quantity);

        return pcCaseMapper.toDto(pcCaseRepository.save(currPcCase));
    }

    public PcCaseDto sell(Long id, int quantity) {
        PcCase currPcCase = findById(id);
        int currQuantity = currPcCase.getQuantity();

        currPcCase.setQuantity(currQuantity - quantity);

        return pcCaseMapper.toDto(pcCaseRepository.save(currPcCase));
    }

    public PcCaseDto get(Long id) {
        return pcCaseMapper.toDto(findById(id));
    }

    public void delete(Long id) {
        pcCaseRepository.deleteById(id);
    }
}

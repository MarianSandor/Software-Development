package com.example.pcpartshop.service.part;

import com.example.pcpartshop.dto.part.PSUDto;
import com.example.pcpartshop.dto.part.PcCaseDto;
import com.example.pcpartshop.mapper.part.PSUMapper;
import com.example.pcpartshop.model.part.PSU;
import com.example.pcpartshop.model.part.PcCase;
import com.example.pcpartshop.repository.part.PSURepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

import static com.example.pcpartshop.repository.specification.PartSpecification.*;

@Service
@RequiredArgsConstructor
public class PSUService extends PartService{
    private final PSURepository psuRepository;
    private final PSUMapper psuMapper;

    private PSU findById(Long id) {
        return psuRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("PSU not found: " + id));
    }

    public List<PSUDto> findAll() {
        return psuRepository.findAll().stream()
                .map(psuMapper::toDto)
                .collect(Collectors.toList());
    }

    public List<PSUDto> search(String query) {
        return psuRepository.findAll(brandLikePSU(query).or(modelLikePSU(query))).stream()
                .map(psuMapper::toDto)
                .collect(Collectors.toList());
    }

    public PSUDto create(PSUDto psuDto) {
        return psuMapper.toDto(psuRepository.save(
                psuMapper.fromDto(psuDto)
        ));
    }

    public PSUDto edit(Long id, PSUDto psuDto) {
        PSU currPSU = findById(id);

        setFromDto(currPSU, psuDto);

        currPSU.setFormFactor(psuDto.getFormFactor());
        currPSU.setEfficiency(psuDto.getEfficiency());
        currPSU.setWattage(psuDto.getWattage());

        return psuMapper.toDto(psuRepository.save(currPSU));
    }

    public PSUDto updateQuantity(Long id, int quantity) {
        PSU currPSU = findById(id);

        currPSU.setQuantity(quantity);

        return psuMapper.toDto(psuRepository.save(currPSU));
    }

    public PSUDto sell(Long id, int quantity) {
        PSU currPSU = findById(id);
        int currQuantity = currPSU.getQuantity();

        currPSU.setQuantity(currQuantity - quantity);

        return psuMapper.toDto(psuRepository.save(currPSU));
    }

    public PSUDto get(Long id) {
        return psuMapper.toDto(findById(id));
    }

    public void delete(Long id) {
        psuRepository.deleteById(id);
    }
}

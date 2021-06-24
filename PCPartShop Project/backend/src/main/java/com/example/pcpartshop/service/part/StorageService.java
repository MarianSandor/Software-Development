package com.example.pcpartshop.service.part;

import com.example.pcpartshop.dto.part.PSUDto;
import com.example.pcpartshop.dto.part.StorageDto;
import com.example.pcpartshop.mapper.part.StorageMapper;
import com.example.pcpartshop.model.part.PSU;
import com.example.pcpartshop.model.part.Storage;
import com.example.pcpartshop.repository.part.StorageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

import static com.example.pcpartshop.repository.specification.PartSpecification.*;

@Service
@RequiredArgsConstructor
public class StorageService extends PartService{
    private final StorageRepository storageRepository;
    private final StorageMapper storageMapper;

    private Storage findById(Long id) {
        return storageRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Storage not found: " + id));
    }

    public List<StorageDto> findAll() {
        return storageRepository.findAll().stream()
                .map(storageMapper::toDto)
                .collect(Collectors.toList());
    }

    public List<StorageDto> search(String query) {
        return storageRepository.findAll(brandLikeStorage(query).or(modelLikeStorage(query))).stream()
                .map(storageMapper::toDto)
                .collect(Collectors.toList());
    }

    public StorageDto create(StorageDto storageDto) {
        return storageMapper.toDto(storageRepository.save(
                storageMapper.fromDto(storageDto)
        ));
    }

    public StorageDto edit(Long id, StorageDto storageDto) {
        Storage currStorage = findById(id);

        setFromDto(currStorage, storageDto);

        currStorage.setCapacity(storageDto.getCapacity());
        currStorage.setType(storageDto.getType());

        return storageMapper.toDto(storageRepository.save(currStorage));
    }

    public StorageDto updateQuantity(Long id, int quantity) {
        Storage currStorage = findById(id);

        currStorage.setQuantity(quantity);

        return storageMapper.toDto(storageRepository.save(currStorage));
    }

    public StorageDto sell(Long id, int quantity) {
        Storage currStorage = findById(id);
        int currQuantity = currStorage.getQuantity();

        currStorage.setQuantity(currQuantity - quantity);

        return storageMapper.toDto(storageRepository.save(currStorage));
    }

    public StorageDto get(Long id) {
        return storageMapper.toDto(findById(id));
    }

    public void delete(Long id) {
        storageRepository.deleteById(id);
    }
}

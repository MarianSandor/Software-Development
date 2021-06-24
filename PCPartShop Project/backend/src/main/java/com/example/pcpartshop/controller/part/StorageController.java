package com.example.pcpartshop.controller.part;

import com.example.pcpartshop.dto.OrderDto;
import com.example.pcpartshop.dto.part.CPUDto;
import com.example.pcpartshop.dto.part.StorageDto;
import com.example.pcpartshop.service.EmailService;
import com.example.pcpartshop.service.part.StorageService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.example.pcpartshop.UrlMapping.*;
import static com.example.pcpartshop.UrlMapping.ENTITY;

@RestController
@RequestMapping(value = STORAGE)
@RequiredArgsConstructor
public class StorageController {
    private final StorageService storageService;
    private final EmailService emailService;

    @GetMapping
    public List<StorageDto> all() {
        return storageService.findAll();
    }

    @GetMapping(SEARCH + QUERY)
    public List<StorageDto> allBy(@PathVariable String query){
        return storageService.search(query);
    }

    @GetMapping(ENTITY)
    public StorageDto get(@PathVariable Long id) {
        return storageService.get(id);
    }

    @PostMapping
    public StorageDto create(@RequestBody StorageDto storage) {
        return storageService.create(storage);
    }

    @PatchMapping(ENTITY)
    public StorageDto updateQuantity(@PathVariable Long id, @RequestBody int quantity) {
        return storageService.updateQuantity(id, quantity);
    }

    @PatchMapping(ENTITY + ORDER)
    public StorageDto order(@PathVariable Long id, @RequestBody OrderDto<StorageDto> order) {
        emailService.sendConfirmation(order.getEmail(), order.getProduct().toString());
        return storageService.sell(id, order.getQuantity());
    }

    @PutMapping(ENTITY)
    public StorageDto edit(@PathVariable Long id, @RequestBody StorageDto storage) {
        return storageService.edit(id, storage);
    }

    @DeleteMapping(ENTITY)
    public void delete(@PathVariable Long id) {
        storageService.delete(id);
    }
}

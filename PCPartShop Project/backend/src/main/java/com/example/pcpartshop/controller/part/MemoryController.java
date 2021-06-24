package com.example.pcpartshop.controller.part;

import com.example.pcpartshop.dto.OrderDto;
import com.example.pcpartshop.dto.part.CPUDto;
import com.example.pcpartshop.dto.part.MemoryDto;
import com.example.pcpartshop.service.EmailService;
import com.example.pcpartshop.service.part.MemoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.example.pcpartshop.UrlMapping.*;
import static com.example.pcpartshop.UrlMapping.ENTITY;

@RestController
@RequestMapping(value = MEMORY)
@RequiredArgsConstructor
public class MemoryController {
    private final MemoryService memoryService;
    private final EmailService emailService;

    @GetMapping
    public List<MemoryDto> all() {
        return memoryService.findAll();
    }

    @GetMapping(SEARCH + QUERY)
    public List<MemoryDto> allBy(@PathVariable String query){
        return memoryService.search(query);
    }

    @GetMapping(ENTITY)
    public MemoryDto get(@PathVariable Long id) {
        return memoryService.get(id);
    }

    @PostMapping
    public MemoryDto create(@RequestBody MemoryDto memory) {
        return memoryService.create(memory);
    }

    @PatchMapping(ENTITY)
    public MemoryDto updateQuantity(@PathVariable Long id, @RequestBody int quantity) {
        return memoryService.updateQuantity(id, quantity);
    }

    @PatchMapping(ENTITY + ORDER)
    public MemoryDto order(@PathVariable Long id, @RequestBody OrderDto<MemoryDto> order) {
        emailService.sendConfirmation(order.getEmail(), order.getProduct().toString());
        return memoryService.sell(id, order.getQuantity());
    }

    @PutMapping(ENTITY)
    public MemoryDto edit(@PathVariable Long id, @RequestBody MemoryDto memory) {
        return memoryService.edit(id, memory);
    }

    @DeleteMapping(ENTITY)
    public void delete(@PathVariable Long id) {
        memoryService.delete(id);
    }
}

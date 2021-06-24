package com.example.pcpartshop.controller.part;

import com.example.pcpartshop.dto.OrderDto;
import com.example.pcpartshop.dto.part.CPUDto;
import com.example.pcpartshop.dto.part.MotherboardDto;
import com.example.pcpartshop.service.EmailService;
import com.example.pcpartshop.service.part.MotherboardService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.example.pcpartshop.UrlMapping.*;
import static com.example.pcpartshop.UrlMapping.ENTITY;

@RestController
@RequestMapping(value = MOTHERBOARD)
@RequiredArgsConstructor
public class MotherboardController {
    private final MotherboardService motherboardService;
    private final EmailService emailService;

    @GetMapping
    public List<MotherboardDto> all() {
        return motherboardService.findAll();
    }

    @GetMapping(SEARCH + QUERY)
    public List<MotherboardDto> allBy(@PathVariable String query){
        return motherboardService.search(query);
    }

    @GetMapping(ENTITY)
    public MotherboardDto get(@PathVariable Long id) {
        return motherboardService.get(id);
    }

    @PostMapping
    public MotherboardDto create(@RequestBody MotherboardDto motherboard) {
        return motherboardService.create(motherboard);
    }

    @PatchMapping(ENTITY)
    public MotherboardDto updateQuantity(@PathVariable Long id, @RequestBody int quantity) {
        return motherboardService.updateQuantity(id, quantity);
    }

    @PatchMapping(ENTITY + ORDER)
    public MotherboardDto order(@PathVariable Long id, @RequestBody OrderDto<MotherboardDto> order) {
        emailService.sendConfirmation(order.getEmail(), order.getProduct().toString());
        return motherboardService.sell(id, order.getQuantity());
    }

    @PutMapping(ENTITY)
    public MotherboardDto edit(@PathVariable Long id, @RequestBody MotherboardDto motherboard) {
        return motherboardService.edit(id, motherboard);
    }

    @DeleteMapping(ENTITY)
    public void delete(@PathVariable Long id) {
        motherboardService.delete(id);
    }
}

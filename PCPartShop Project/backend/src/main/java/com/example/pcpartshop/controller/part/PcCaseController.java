package com.example.pcpartshop.controller.part;

import com.example.pcpartshop.dto.OrderDto;
import com.example.pcpartshop.dto.part.CPUDto;
import com.example.pcpartshop.dto.part.PcCaseDto;
import com.example.pcpartshop.service.EmailService;
import com.example.pcpartshop.service.part.PcCaseService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.example.pcpartshop.UrlMapping.*;
import static com.example.pcpartshop.UrlMapping.ENTITY;

@RestController
@RequestMapping(value = PC_CASE)
@RequiredArgsConstructor
public class PcCaseController {
    private final PcCaseService pcCaseService;
    private final EmailService emailService;

    @GetMapping
    public List<PcCaseDto> all() {
        return pcCaseService.findAll();
    }

    @GetMapping(SEARCH + QUERY)
    public List<PcCaseDto> allBy(@PathVariable String query){
        return pcCaseService.search(query);
    }

    @GetMapping(ENTITY)
    public PcCaseDto get(@PathVariable Long id) {
        return pcCaseService.get(id);
    }

    @PostMapping
    public PcCaseDto create(@RequestBody PcCaseDto pcCase) {
        return pcCaseService.create(pcCase);
    }

    @PatchMapping(ENTITY)
    public PcCaseDto updateQuantity(@PathVariable Long id, @RequestBody int quantity) {
        return pcCaseService.updateQuantity(id, quantity);
    }

    @PatchMapping(ENTITY + ORDER)
    public PcCaseDto order(@PathVariable Long id, @RequestBody OrderDto<PcCaseDto> order) {
        emailService.sendConfirmation(order.getEmail(), order.getProduct().toString());
        return pcCaseService.sell(id, order.getQuantity());
    }

    @PutMapping(ENTITY)
    public PcCaseDto edit(@PathVariable Long id, @RequestBody PcCaseDto pcCase) {
        return pcCaseService.edit(id, pcCase);
    }

    @DeleteMapping(ENTITY)
    public void delete(@PathVariable Long id) {
        pcCaseService.delete(id);
    }
}

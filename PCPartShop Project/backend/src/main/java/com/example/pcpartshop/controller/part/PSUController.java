package com.example.pcpartshop.controller.part;

import com.example.pcpartshop.dto.OrderDto;
import com.example.pcpartshop.dto.part.CPUDto;
import com.example.pcpartshop.dto.part.PSUDto;
import com.example.pcpartshop.service.EmailService;
import com.example.pcpartshop.service.part.PSUService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.example.pcpartshop.UrlMapping.*;
import static com.example.pcpartshop.UrlMapping.ENTITY;

@RestController
@RequestMapping(value = PSU)
@RequiredArgsConstructor
public class PSUController {
    private final PSUService psuService;
    private final EmailService emailService;

    @GetMapping
    public List<PSUDto> all() {
        return psuService.findAll();
    }

    @GetMapping(SEARCH + QUERY)
    public List<PSUDto> allBy(@PathVariable String query){
        return psuService.search(query);
    }

    @GetMapping(ENTITY)
    public PSUDto get(@PathVariable Long id) {
        return psuService.get(id);
    }

    @PostMapping
    public PSUDto create(@RequestBody PSUDto psu) {
        return psuService.create(psu);
    }

    @PatchMapping(ENTITY)
    public PSUDto updateQuantity(@PathVariable Long id, @RequestBody int quantity) {
        return psuService.updateQuantity(id, quantity);
    }

    @PatchMapping(ENTITY + ORDER)
    public PSUDto order(@PathVariable Long id, @RequestBody OrderDto<PSUDto> order) {
        emailService.sendConfirmation(order.getEmail(), order.getProduct().toString());
        return psuService.sell(id, order.getQuantity());
    }

    @PutMapping(ENTITY)
    public PSUDto edit(@PathVariable Long id, @RequestBody PSUDto psu) {
        return psuService.edit(id, psu);
    }

    @DeleteMapping(ENTITY)
    public void delete(@PathVariable Long id) {
        psuService.delete(id);
    }
}

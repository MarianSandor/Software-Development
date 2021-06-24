package com.example.pcpartshop.controller.part;

import com.example.pcpartshop.dto.OrderDto;
import com.example.pcpartshop.dto.part.CPUDto;
import com.example.pcpartshop.dto.part.GPUDto;
import com.example.pcpartshop.service.EmailService;
import com.example.pcpartshop.service.part.GPUService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.example.pcpartshop.UrlMapping.*;
import static com.example.pcpartshop.UrlMapping.ENTITY;

@RestController
@RequestMapping(value = GPU)
@RequiredArgsConstructor
public class GPUController {
    private final GPUService gpuService;
    private final EmailService emailService;

    @GetMapping
    public List<GPUDto> all() {
        return gpuService.findAll();
    }

    @GetMapping(SEARCH + QUERY)
    public List<GPUDto> allBy(@PathVariable String query){
        return gpuService.search(query);
    }

    @GetMapping(ENTITY)
    public GPUDto get(@PathVariable Long id) {
        return gpuService.get(id);
    }

    @PostMapping
    public GPUDto create(@RequestBody GPUDto cpu) {
        return gpuService.create(cpu);
    }

    @PatchMapping(ENTITY)
    public GPUDto updateQuantity(@PathVariable Long id, @RequestBody int quantity) {
        return gpuService.updateQuantity(id, quantity);
    }

    @PatchMapping(ENTITY + ORDER)
    public GPUDto order(@PathVariable Long id, @RequestBody OrderDto<GPUDto> order) {
        emailService.sendConfirmation(order.getEmail(), order.getProduct().toString());
        return gpuService.sell(id, order.getQuantity());
    }

    @PutMapping(ENTITY)
    public GPUDto edit(@PathVariable Long id, @RequestBody GPUDto gpu) {
        return gpuService.edit(id, gpu);
    }

    @DeleteMapping(ENTITY)
    public void delete(@PathVariable Long id) {
        gpuService.delete(id);
    }
}

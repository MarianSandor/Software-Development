package com.example.pcpartshop.controller.part;

import com.example.pcpartshop.dto.OrderDto;
import com.example.pcpartshop.dto.part.CPUDto;
import com.example.pcpartshop.model.part.CPU;
import com.example.pcpartshop.service.EmailService;
import com.example.pcpartshop.service.part.CPUService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.example.pcpartshop.UrlMapping.*;

@RestController
@RequestMapping(value = CPU)
@RequiredArgsConstructor
public class CPUController {
    private final CPUService cpuService;
    private final EmailService emailService;

    @GetMapping
    public List<CPUDto> all() {
        return cpuService.findAll();
    }

    @GetMapping(SEARCH + QUERY)
    public List<CPUDto> allBy(@PathVariable String query){
        return cpuService.search(query);
    }

    @GetMapping(ENTITY)
    public CPUDto get(@PathVariable Long id) {
        return cpuService.get(id);
    }

    @PostMapping
    public CPUDto create(@RequestBody CPUDto cpu) {
        return cpuService.create(cpu);
    }

    @PatchMapping(ENTITY)
    public CPUDto updateQuantity(@PathVariable Long id, @RequestBody int quantity) {
        return cpuService.updateQuantity(id, quantity);
    }

    @PatchMapping(ENTITY + ORDER)
    public CPUDto order(@PathVariable Long id, @RequestBody OrderDto<CPUDto> order) {
        emailService.sendConfirmation(order.getEmail(), order.getProduct().toString());
        return cpuService.sell(id, order.getQuantity());
    }

    @PutMapping(ENTITY)
    public CPUDto edit(@PathVariable Long id, @RequestBody CPUDto cpu) {
        return cpuService.edit(id, cpu);
    }

    @DeleteMapping(ENTITY)
    public void delete(@PathVariable Long id) {
        cpuService.delete(id);
    }
}

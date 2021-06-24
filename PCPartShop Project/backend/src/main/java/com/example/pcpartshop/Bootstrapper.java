package com.example.pcpartshop;

import com.example.pcpartshop.dto.request.SignupRequest;
import com.example.pcpartshop.model.Configuration;
import com.example.pcpartshop.model.ERole;
import com.example.pcpartshop.model.Role;
import com.example.pcpartshop.model.User;
import com.example.pcpartshop.model.part.*;
import com.example.pcpartshop.repository.ConfigurationRepository;
import com.example.pcpartshop.repository.RoleRepository;
import com.example.pcpartshop.repository.UserRepository;
import com.example.pcpartshop.repository.part.*;
import com.example.pcpartshop.service.AuthService;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Component
@RequiredArgsConstructor
public class Bootstrapper implements ApplicationListener<ApplicationReadyEvent> {

    private final RoleRepository roleRepository;
    private final UserRepository userRepository;
    private final CPURepository cpuRepository;
    private final GPURepository gpuRepository;
    private final MemoryRepository memoryRepository;
    private final MotherboardRepository motherboardRepository;
    private final StorageRepository storageRepository;
    private final PSURepository psuRepository;
    private final PcCaseRepository pcCaseRepository;
    private final ConfigurationRepository configurationRepository;

    private final AuthService authService;

    @Value("${app.bootstrap}")
    private Boolean bootstrap;

    @SneakyThrows
    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
        if (bootstrap) {
            Process p =Runtime.getRuntime().exec("python bootstrapper.py");
            p.waitFor();

            userRepository.deleteAll();
            roleRepository.deleteAll();

            for (ERole value : ERole.values()) {
                roleRepository.save(
                        Role.builder()
                                .name(value)
                                .build()
                );
            }
            authService.register(SignupRequest.builder()
                    .email("marian@email.com")
                    .username("marian")
                    .password("123")
                    .roles(Set.of("ADMIN"))
                    .build());
            authService.register(SignupRequest.builder()
                    .email("marianS@email.com")
                    .username("marianE")
                    .password("321")
                    .roles(Set.of("EMPLOYEE"))
                    .build());
            authService.register(SignupRequest.builder()
                    .email("marianD@email.com")
                    .username("marianC")
                    .password("321")
                    .roles(Set.of("CUSTOMER"))
                    .build());

            User user = userRepository.findById(3L)
                    .orElseThrow(() -> new EntityNotFoundException("User not found: "));
            CPU cpu = cpuRepository.findById(1L)
                    .orElseThrow(() -> new EntityNotFoundException("User not found: "));
            GPU gpu = gpuRepository.findById(1L)
                    .orElseThrow(() -> new EntityNotFoundException("User not found: "));
            Memory memory = memoryRepository.findById(1L)
                    .orElseThrow(() -> new EntityNotFoundException("User not found: "));
            Motherboard motherboard = motherboardRepository.findById(1L)
                    .orElseThrow(() -> new EntityNotFoundException("User not found: "));
            PcCase pcCase = pcCaseRepository.findById(1L)
                    .orElseThrow(() -> new EntityNotFoundException("User not found: "));
            PSU psu = psuRepository.findById(1L)
                    .orElseThrow(() -> new EntityNotFoundException("User not found: "));
            Storage storage = storageRepository.findById(1L)
                    .orElseThrow(() -> new EntityNotFoundException("User not found: "));
            double total = List.of(cpu, gpu, memory, motherboard, storage, psu, pcCase).stream()
                    .map(Part::getPrice)
                    .reduce(0.0, Double::sum);

            Configuration configuration = Configuration.builder()
                    .user(user)
                    .cpu(cpu)
                    .gpu(gpu)
                    .memory(memory)
                    .motherboard(motherboard)
                    .pcCase(pcCase)
                    .psu(psu)
                    .storage(storage)
                    .total(total)
                    .description("Cea mai tare configuratie!")
                    .dateCreated(LocalDateTime.now())
                    .build();

            configurationRepository.save(configuration);
        }
    }
}
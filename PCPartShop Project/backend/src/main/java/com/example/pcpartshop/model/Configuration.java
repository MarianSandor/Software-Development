package com.example.pcpartshop.model;

import com.example.pcpartshop.model.part.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@RequiredArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class Configuration {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String description;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "cpu_id")
    private CPU cpu;

    @ManyToOne
    @JoinColumn(name = "gpu_id")
    private GPU gpu;

    @ManyToOne
    @JoinColumn(name = "motherboard_id")
    private Motherboard motherboard;

    @ManyToOne
    @JoinColumn(name = "memory_id")
    private Memory memory;

    @ManyToOne
    @JoinColumn(name = "storage_id")
    private Storage storage;

    @ManyToOne
    @JoinColumn(name = "psu_id")
    private PSU psu;

    @ManyToOne
    @JoinColumn(name = "case_id")
    private PcCase pcCase;

    @Column
    private double total;

    @Column
    private LocalDateTime dateCreated;
}

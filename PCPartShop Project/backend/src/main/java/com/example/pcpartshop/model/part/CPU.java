package com.example.pcpartshop.model.part;

import lombok.*;

import javax.persistence.*;

@EqualsAndHashCode(callSuper = true)
@Entity
@RequiredArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class CPU extends Part{

    @Column
    private int cores;

    @Column
    private double frequency;

    @Column
    private String integratedGraphics;
}

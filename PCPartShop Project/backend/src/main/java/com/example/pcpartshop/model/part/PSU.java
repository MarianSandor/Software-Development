package com.example.pcpartshop.model.part;

import lombok.*;

import javax.persistence.*;

@EqualsAndHashCode(callSuper = true)
@Entity
@RequiredArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class PSU extends Part{

    @Column
    private String formFactor;

    @Column
    private String efficiency;

    @Column
    private int wattage;
}

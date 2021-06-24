package com.example.pcpartshop.model.part;

import lombok.*;

import javax.persistence.*;

@EqualsAndHashCode(callSuper = true)
@Entity
@RequiredArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class Memory extends Part{

    @Column
    private int size;

    @Column
    private String type;

    @Column
    private int frequency;
}

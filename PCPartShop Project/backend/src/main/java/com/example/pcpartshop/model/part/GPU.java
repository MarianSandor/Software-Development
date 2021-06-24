package com.example.pcpartshop.model.part;

import lombok.*;

import javax.persistence.*;

@EqualsAndHashCode(callSuper = true)
@Entity
@RequiredArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class GPU extends Part{

    @Column
    private int vram;

    @Column
    private int frequency;
}

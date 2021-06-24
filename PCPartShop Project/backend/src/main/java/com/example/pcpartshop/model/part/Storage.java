package com.example.pcpartshop.model.part;

import lombok.*;

import javax.persistence.*;

@EqualsAndHashCode(callSuper = true)
@Entity
@RequiredArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class Storage extends Part{

    @Column
    private int capacity;

    @Column
    private String type;
}

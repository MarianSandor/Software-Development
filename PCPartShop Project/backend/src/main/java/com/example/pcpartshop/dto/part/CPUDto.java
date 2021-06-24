package com.example.pcpartshop.dto.part;

import lombok.*;


@EqualsAndHashCode(callSuper = true)
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CPUDto extends PartDto {

    private int cores;
    private double frequency;
    private String integratedGraphics;

    @Override
    public String toString() {
        return "CPU {" +
                "brand='" + brand + '\'' +
                ", model='" + model + '\'' +
                ", cores=" + cores +
                ", frequency=" + frequency +
                ", integratedGraphics='" + integratedGraphics + '\'' +
                ", price=" + price +
                '}';
    }
}

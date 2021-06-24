package com.example.pcpartshop.dto.part;

import lombok.*;


@EqualsAndHashCode(callSuper = true)
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MotherboardDto extends PartDto{

    private String socket;
    private String formFactor;

    @Override
    public String toString() {
        return "Motherboard {" +
                "brand='" + brand + '\'' +
                ", model='" + model + '\'' +
                ", socket='" + socket + '\'' +
                ", formFactor='" + formFactor + '\'' +
                ", price=" + price +
                '}';
    }
}

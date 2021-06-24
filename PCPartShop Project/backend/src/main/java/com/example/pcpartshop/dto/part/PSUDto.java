package com.example.pcpartshop.dto.part;

import lombok.*;

@EqualsAndHashCode(callSuper = true)
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PSUDto extends PartDto{

    private String formFactor;
    private String efficiency;
    private int wattage;

    @Override
    public String toString() {
        return "PSU {" +
                "brand='" + brand + '\'' +
                ", model='" + model + '\'' +
                ", formFactor='" + formFactor + '\'' +
                ", efficiency='" + efficiency + '\'' +
                ", wattage=" + wattage +
                ", price=" + price +
                '}';
    }
}

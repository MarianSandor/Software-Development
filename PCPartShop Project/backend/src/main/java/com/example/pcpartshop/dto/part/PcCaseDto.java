package com.example.pcpartshop.dto.part;

import lombok.*;

@EqualsAndHashCode(callSuper = true)
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PcCaseDto extends PartDto{

    private String formFactor;

    @Override
    public String toString() {
        return "PcCase {" +
                "brand='" + brand + '\'' +
                ", model='" + model + '\'' +
                ", formFactor='" + formFactor + '\'' +
                ", price=" + price +
                '}';
    }
}

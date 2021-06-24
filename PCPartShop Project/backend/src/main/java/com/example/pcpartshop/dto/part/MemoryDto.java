package com.example.pcpartshop.dto.part;

import lombok.*;


@EqualsAndHashCode(callSuper = true)
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MemoryDto extends PartDto {

    private int size;
    private String type;
    private int frequency;

    @Override
    public String toString() {
        return "Memory {" +
                "brand='" + brand + '\'' +
                ", model='" + model + '\'' +
                ", size=" + size +
                ", type='" + type + '\'' +
                ", frequency=" + frequency +
                ", price=" + price +
                '}';
    }
}

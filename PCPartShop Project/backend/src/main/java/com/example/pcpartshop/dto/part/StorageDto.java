package com.example.pcpartshop.dto.part;

import lombok.*;

@EqualsAndHashCode(callSuper = true)
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class StorageDto extends PartDto{

    private int capacity;
    private String type;

    @Override
    public String toString() {
        return "Storage {" +
                "brand='" + brand + '\'' +
                ", model='" + model + '\'' +
                ", capacity=" + capacity +
                ", type='" + type + '\'' +
                ", price=" + price +
                '}';
    }
}

package com.example.pcpartshop.dto.part;

import lombok.*;


@EqualsAndHashCode(callSuper = true)
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GPUDto extends PartDto {

    private int vram;
    private int frequency;

    @Override
    public String toString() {
        return "GPU {" +
                "brand='" + brand + '\'' +
                ", model='" + model + '\'' +
                ", vram=" + vram +
                ", frequency=" + frequency +
                ", price=" + price +
                '}';
    }
}

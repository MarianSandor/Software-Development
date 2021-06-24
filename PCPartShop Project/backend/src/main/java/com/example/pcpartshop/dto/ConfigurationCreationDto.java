package com.example.pcpartshop.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ConfigurationCreationDto {

    private String description;
    private Long userId;
    private Long cpuId;
    private Long gpuId;
    private Long motherboardId;
    private Long memoryId;
    private Long storageId;
    private Long psuId;
    private Long pcCaseId;
}

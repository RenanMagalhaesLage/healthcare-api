package com.healthcareApi.domain.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class MedicalCenterResponseDTO {
    private Long id;
    private String name;
    private AddressResponseDTO address;
    private String phone;
}

package com.healthcareApi.domain.dto.request;

public record MedicalCenterRequestDTO(Long medicalCenterId, String name, AddressRequestDTO address, String phone) {
}

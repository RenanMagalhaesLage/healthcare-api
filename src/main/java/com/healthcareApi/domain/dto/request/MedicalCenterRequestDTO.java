package com.healthcareApi.domain.dto.request;

public record MedicalCenterRequestDTO(String name, AddressRequestDTO address, String phone) {
}

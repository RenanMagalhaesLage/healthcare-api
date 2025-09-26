package com.healthcareApi.domain.dto.request;

public record AddressRequestDTO(Long addressId, String street, String number, String complement, String neighborhood, String city, String state, String zipCode, String country) {
}

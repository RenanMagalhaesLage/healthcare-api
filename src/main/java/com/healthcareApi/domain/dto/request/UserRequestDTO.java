package com.healthcareApi.domain.dto.request;

import java.time.LocalDate;

public record UserRequestDTO(String name, String lastname, String email, LocalDate birthday, AddressRequestDTO address, String phone, Integer gender) {
}

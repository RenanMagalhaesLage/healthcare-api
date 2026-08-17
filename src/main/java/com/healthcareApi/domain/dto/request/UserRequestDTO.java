package com.healthcareApi.domain.dto.request;

import com.healthcareApi.enums.GenderEnum;

import java.time.LocalDate;

public record UserRequestDTO(Long userId, String name, String lastname, String email, LocalDate birthday, AddressRequestDTO address, String phone, GenderEnum gender) {
}

package com.healthcareApi.domain.dto.response;

import com.healthcareApi.enums.GenderEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class UserResponseDTO {
    private Long id;
    private String name;
    private String lastname;
    private String email;
    private LocalDate birthday;
    private AddressResponseDTO address;
    private String phone;
    private GenderEnum gender;
}

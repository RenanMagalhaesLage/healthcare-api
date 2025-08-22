package com.healthcareApi.controller;

import com.healthcareApi.domain.dto.response.EnumResponseDTO;
import com.healthcareApi.enums.GenderEnum;
import com.healthcareApi.enums.ProfessionalTypeEnum;
import com.healthcareApi.enums.SpecialtyEnum;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.IntStream;

@RestController
@RequestMapping("/enums")
@Tag(name = "Enums", description = "Endpoints for managing enums")
public class EnumsController {
    @GetMapping("/genders")
    public ResponseEntity<List<EnumResponseDTO>> getAllGenders() {
        List<EnumResponseDTO> enumResponseDtoList = IntStream.range(0, GenderEnum.values().length)
                .mapToObj(i -> new EnumResponseDTO(i, GenderEnum.values()[i].name()))
                .toList();

        return ResponseEntity.ok(enumResponseDtoList);
    }

    @GetMapping("/professional-types")
    public ResponseEntity<List<EnumResponseDTO>> getAllProfessionalTypes() {
        List<EnumResponseDTO> enumResponseDtoList = IntStream.range(0, ProfessionalTypeEnum.values().length)
                .mapToObj(i -> new EnumResponseDTO(i, ProfessionalTypeEnum.values()[i].name()))
                .toList();
        return ResponseEntity.ok(enumResponseDtoList);
    }

    @GetMapping("/specialties")
    public ResponseEntity<List<EnumResponseDTO>> getAllSpecialties() {
        List<EnumResponseDTO> enumResponseDtoList = IntStream.range(0, SpecialtyEnum.values().length)
                .mapToObj(i -> new EnumResponseDTO(i, SpecialtyEnum.values()[i].name()))
                .toList();
        return ResponseEntity.ok(enumResponseDtoList);
    }
}

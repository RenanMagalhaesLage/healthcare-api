package com.healthcareApi.domain.dto.response;

import java.time.DayOfWeek;
import java.time.LocalTime;

public record AppointmentAvailabilityResponseDTO(HealthProfessionalResponseDTO healthProfessional, DayOfWeek dayOfWeek, LocalTime startTime, LocalTime endTime) {
}

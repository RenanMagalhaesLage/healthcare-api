package com.healthcareApi.domain.dto.request;

import java.time.DayOfWeek;
import java.time.LocalTime;

public record AppointmentAvailabilityRequestDTO(Long appointmentAvailabilityId, HealthProfessionalRequestDTO healthProfessional, DayOfWeek dayOfWeek, LocalTime startTime, LocalTime endTime) {
}

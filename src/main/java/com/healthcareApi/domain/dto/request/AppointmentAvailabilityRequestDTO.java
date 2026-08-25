package com.healthcareApi.domain.dto.request;

import java.time.DayOfWeek;
import java.time.LocalTime;

public record AppointmentAvailabilityRequestDTO(Long appointmentAvailabilityId, Long healthProfessionalId, Long medicalCenterId, DayOfWeek dayOfWeek, LocalTime startTime, LocalTime endTime) {
}

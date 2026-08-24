package com.healthcareApi.repository;

import com.healthcareApi.domain.entity.AppointmentAvailabilityEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AppointmentAvailabilityRepository extends JpaRepository<AppointmentAvailabilityEntity, Long> {
}

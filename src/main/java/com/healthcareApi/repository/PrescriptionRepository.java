package com.healthcareApi.repository;

import com.healthcareApi.domain.entity.AppointmentEntity;
import com.healthcareApi.domain.entity.PrescriptionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PrescriptionRepository extends JpaRepository<PrescriptionEntity, Long> {
    List<PrescriptionEntity> findByAppointment(AppointmentEntity appointment);
}

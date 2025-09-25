package com.healthcareApi.repository;

import com.healthcareApi.domain.entity.AppointmentEntity;
import com.healthcareApi.domain.entity.HealthProfessionalEntity;
import com.healthcareApi.domain.entity.MedicalCenterEntity;
import com.healthcareApi.domain.entity.PatientEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AppointmentRepository extends JpaRepository<AppointmentEntity, Long> {
    List<AppointmentEntity> findByPatient(PatientEntity patient);
    List<AppointmentEntity> findByHealthProfessional(HealthProfessionalEntity healthProfessional);
    List<AppointmentEntity> findByMedicalCenter(MedicalCenterEntity medicalCenter);
}

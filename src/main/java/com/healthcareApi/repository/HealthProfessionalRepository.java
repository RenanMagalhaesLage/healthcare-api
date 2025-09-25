package com.healthcareApi.repository;

import com.healthcareApi.domain.entity.HealthProfessionalEntity;
import com.healthcareApi.enums.SpecialtyEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HealthProfessionalRepository extends JpaRepository<HealthProfessionalEntity, Long> {
    List<HealthProfessionalEntity> findBySpecialty(SpecialtyEnum specialty);
}

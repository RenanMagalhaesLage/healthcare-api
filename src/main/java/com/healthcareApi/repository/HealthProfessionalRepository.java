package com.healthcareApi.repository;

import com.healthcareApi.domain.entity.HealthProfessionalEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HealthProfessionalRepository extends JpaRepository<HealthProfessionalEntity, Long> {
}

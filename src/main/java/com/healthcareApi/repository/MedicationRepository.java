package com.healthcareApi.repository;

import com.healthcareApi.domain.entity.MedicationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MedicationRepository extends JpaRepository<MedicationEntity, Long> {
}

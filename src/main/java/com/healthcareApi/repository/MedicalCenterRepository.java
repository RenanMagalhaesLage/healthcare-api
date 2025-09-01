package com.healthcareApi.repository;

import com.healthcareApi.domain.entity.MedicalCenterEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MedicalCenterRepository extends JpaRepository<MedicalCenterEntity, Long> {
}

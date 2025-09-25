package com.healthcareApi.repository;

import com.healthcareApi.domain.entity.MedicalCenterEntity;
import com.healthcareApi.domain.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {
    List<UserEntity> findByMedicalCenter(MedicalCenterEntity medicalCenterEntity);
}

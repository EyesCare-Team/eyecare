package com.project_EyeCare.EyeCare.Repository;

import com.project_EyeCare.EyeCare.entity.HospitalEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface HospitalRepository extends JpaRepository<HospitalEntity, Long> {
    Optional<HospitalEntity> findByNameAndLatAndLng(String name, double lat, double lng);
}

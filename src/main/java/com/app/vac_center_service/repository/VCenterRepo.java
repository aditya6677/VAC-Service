package com.app.vac_center_service.repository;

import com.app.vac_center_service.entity.VCenterEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VCenterRepo extends JpaRepository<VCenterEntity, Long> {
}

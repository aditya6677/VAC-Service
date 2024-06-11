package com.app.vac_center_service.custom;

import com.app.vac_center_service.entity.CitizenEntity;
import com.app.vac_center_service.entity.VCenterEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomResponse {
    private VCenterEntity vCenterEntity;
    private List<CitizenEntity> citizenEntity;
}

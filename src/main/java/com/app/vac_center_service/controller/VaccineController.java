package com.app.vac_center_service.controller;

import com.app.vac_center_service.entity.VCenterEntity;
import com.app.vac_center_service.repository.VCenterRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/vac")
public class VaccineController {

    private VCenterRepo vCenterRepo;

    @Autowired
    public VaccineController(VCenterRepo vCenterRepo) {
        this.vCenterRepo = vCenterRepo;
    }

    @GetMapping("/list")
    public ResponseEntity<List<VCenterEntity>> getAllVaccines() {
        return new ResponseEntity<>(vCenterRepo.findAll(), HttpStatus.OK);
    }

}

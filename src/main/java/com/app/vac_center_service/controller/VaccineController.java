package com.app.vac_center_service.controller;


import com.app.vac_center_service.custom.CustomResponse;

import com.app.vac_center_service.entity.CitizenEntity;
import com.app.vac_center_service.entity.VCenterEntity;
import com.app.vac_center_service.repository.VCenterRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
@RequestMapping("/vac")
public class VaccineController {

    private VCenterRepo vCenterRepo;
    private RestTemplate restTemplate;

    @Autowired
    public VaccineController(VCenterRepo vCenterRepo, RestTemplate restTemplate) {
        this.vCenterRepo = vCenterRepo;
        this.restTemplate = restTemplate;
    }

    @GetMapping("/list")
    public ResponseEntity<List<VCenterEntity>> getAllVaccines() {
        return new ResponseEntity<>(vCenterRepo.findAll(), HttpStatus.OK);
    }

    @GetMapping("/list/{id}")
    public ResponseEntity<CustomResponse> getVaccineById(@PathVariable Long id) {
        CustomResponse customResponse = new CustomResponse();
        VCenterEntity vCenterEntity = vCenterRepo.findById(id).get();
        customResponse.setVCenterEntity(vCenterEntity);
        List<CitizenEntity> citizenEntityList = restTemplate.getForObject("http://localhost:8081/citizen/vid/" + id, List.class);
        customResponse.setCitizenEntity(citizenEntityList);
        return new ResponseEntity<>(customResponse, HttpStatus.OK);
    }

}

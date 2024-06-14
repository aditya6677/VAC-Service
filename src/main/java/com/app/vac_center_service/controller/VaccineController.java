package com.app.vac_center_service.controller;


import com.app.vac_center_service.custom.CustomResponse;

import com.app.vac_center_service.entity.CitizenEntity;
import com.app.vac_center_service.entity.VCenterEntity;
import com.app.vac_center_service.repository.VCenterRepo;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

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
    public ResponseEntity<Map<String, Set<VCenterEntity>>> getAllVaccines() {
        Map<String, Set<VCenterEntity>> map = vCenterRepo.findAll().stream().collect(Collectors.groupingBy(VCenterEntity::getAddress, Collectors.toSet()));
        return new ResponseEntity<>(map, HttpStatus.OK);
        //System.out.println(map);
        //return new ResponseEntity<>(vCenterRepo.findAll(), HttpStatus.OK);
    }

    @GetMapping("/list/{id}")
    @HystrixCommand(fallbackMethod = "getVaccineByIdFallback")
    public ResponseEntity<CustomResponse> getVaccineById(@PathVariable Long id) {
        CustomResponse customResponse = new CustomResponse();
        VCenterEntity vCenterEntity = vCenterRepo.findById(id).get();
        customResponse.setVCenterEntity(vCenterEntity);
        List<CitizenEntity> citizenEntityList = restTemplate.getForObject("http://CITIZEN-SERVICE/citizen/vid/" + id, List.class);
        customResponse.setCitizenEntity(citizenEntityList);
        return new ResponseEntity<>(customResponse, HttpStatus.OK);
    }

    public ResponseEntity<CustomResponse> getVaccineByIdFallback(@PathVariable Long id){
        CustomResponse customResponse = new CustomResponse();
        VCenterEntity vCenterEntity = vCenterRepo.findById(id).get();
        customResponse.setVCenterEntity(vCenterEntity);
        return new ResponseEntity<>(customResponse, HttpStatus.OK);
    }

}

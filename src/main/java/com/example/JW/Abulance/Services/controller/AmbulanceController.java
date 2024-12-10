package com.example.JW.Abulance.Services.controller;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.JW.Abulance.Services.entity.Ambulance;
import com.example.JW.Abulance.Services.service.implementations.AmbulanceServiceImpl;

@RestController
@RequestMapping("/api/v1/ambulances")
public class AmbulanceController {
    private final AmbulanceServiceImpl ambulance_service;

    public AmbulanceController(AmbulanceServiceImpl ambulance_service) {
        this.ambulance_service = ambulance_service;
    }

    // GET all ambulances
    @GetMapping("/")
    public ResponseEntity<List<Ambulance>> getAllAmbulances() {
        List<Ambulance> all_ambulances = ambulance_service.getAllAmbulances();
        
        return new ResponseEntity<>(all_ambulances, HttpStatus.OK);
    }

    // GET all ambulances in a paginated format
    @GetMapping("/paginated/")
    public ResponseEntity<Page<Ambulance>> getAllAmbulancesPaginated(
        @RequestParam(defaultValue = "0") int page,
        @RequestParam(defaultValue = "10") int size
    ) {
        Page<Ambulance> all_ambulances_paginated = ambulance_service.getAllAmulancesPaginated(page, size);

        return new ResponseEntity<>(all_ambulances_paginated, HttpStatus.OK);
    }

    // POST an ambulance
    @PostMapping("/")
    public ResponseEntity<Ambulance> postNewAmbulance(@RequestBody Ambulance new_ambulance) {
        Ambulance added_ambulance = ambulance_service.postNewAmbulance(new_ambulance);
        
        return new ResponseEntity<Ambulance>(added_ambulance, HttpStatus.CREATED);
    }

    // PUT an ambulance
    @PutMapping("/{plate_number}")
    public ResponseEntity<Ambulance> editAmbulance (
        @PathVariable String plate_number,    
        Ambulance ambulance_edit
    ) {
        return new ResponseEntity<>(
            ambulance_service.putExistingAmbulance(plate_number, ambulance_edit),
            HttpStatus.OK
        );
    }

    // DELETE an ambulance
    @DeleteMapping("/{plate_number}")
    public ResponseEntity<?> deleteAmbulance(@PathVariable String plate_number) {
        ambulance_service.deleteAmbulance(plate_number);
            
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

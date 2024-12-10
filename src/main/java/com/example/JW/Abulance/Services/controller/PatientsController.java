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

import com.example.JW.Abulance.Services.entity.Patient;
import com.example.JW.Abulance.Services.service.implementations.PatientServiceImpl;

@RestController
@RequestMapping("/api/v1/patients")
public class PatientsController {
    private final PatientServiceImpl patients_service;

    public PatientsController(PatientServiceImpl patients_service) {
        this.patients_service = patients_service;
    }

    @GetMapping("/")
    public ResponseEntity<List<Patient>> getAllPatients() {
        List<Patient> all_patients = patients_service.getAllPatients();

        return new ResponseEntity<List<Patient>>(all_patients, HttpStatus.OK);
    }

    // GET all patients records in a paginated format
    @GetMapping("/paginated/")
    public ResponseEntity<Page<Patient>> getAllPatientRecordsPaginated(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        Page<Patient> all_patients_records_paginated = patients_service.getAllPatientsPaginated(page, size);

        return new ResponseEntity<>(all_patients_records_paginated, HttpStatus.OK);
    }

    // POST a patients record
    @PostMapping("/")
    public ResponseEntity<Patient> postNewPatient(@RequestBody Patient new_record) {
        Patient added_record = patients_service.postNewPatient(new_record);

        return new ResponseEntity<Patient>(added_record, HttpStatus.CREATED);
    }

    // PUT a patients record
    @PutMapping("/{id}")
    public ResponseEntity<Patient> editPatient(
            @PathVariable("id") int patients_id,
            Patient patients_edit) {
        return new ResponseEntity<Patient>(
                patients_service.putExistingPatient(patients_id, patients_edit),
                HttpStatus.OK);
    }

    // DELETE a patients record
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletePatient(@PathVariable("id") int patients_id) {
        patients_service.deletePatient(patients_id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

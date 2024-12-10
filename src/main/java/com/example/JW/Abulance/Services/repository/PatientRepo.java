package com.example.JW.Abulance.Services.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.JW.Abulance.Services.entity.Patient;

public interface PatientRepo extends JpaRepository<Patient, Integer> {
}

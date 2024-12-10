package com.example.JW.Abulance.Services.service;

import java.util.List;

import com.example.JW.Abulance.Services.entity.Patient;

public interface PatientService {

    List<Patient> getAllPatients();

    Patient postNewPatient(Patient new_patient);

    Patient putExistingPatient(int patient_id, Patient patient_to_edit);

    void deletePatient(int patient_id);

}

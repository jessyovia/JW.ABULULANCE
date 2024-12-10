package com.example.JW.Abulance.Services.service.implementations;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.JW.Abulance.Services.entity.Patient;
import com.example.JW.Abulance.Services.exceptions.EntityNotExistingException;
import com.example.JW.Abulance.Services.repository.PatientRepo;
import com.example.JW.Abulance.Services.service.PatientService;

@Service
public class PatientServiceImpl implements PatientService {
    private final PatientRepo patient_repo;

    public PatientServiceImpl(PatientRepo patient_repo) {
        this.patient_repo = patient_repo;
    }

    @Override
    public List<Patient> getAllPatients() {
        return patient_repo.findAll();
    }

    public Page<Patient> getAllPatientsPaginated(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);

        return patient_repo.findAll(pageable);
    }

    @Override
    public Patient postNewPatient(Patient new_patient) {
        return patient_repo.save(new_patient);
    }

    @Override
    public Patient putExistingPatient(int patient_id, Patient patient_to_edit) {
        // ensures patient ID exists
        if (!patient_repo.existsById(patient_id))
            throw new EntityNotExistingException("Patients with id " + patient_id + "doesnt exist\n Associated Entity:" + this.getClass().getSimpleName());

        Patient edit = new Patient();

        edit.setCondition_description(patient_to_edit.getCondition_description());
        edit.setGender(patient_to_edit.getGender());
        edit.setPatient_name(patient_to_edit.getPatient_name());
        edit.setUpdated_at(LocalDateTime.now());

        return patient_repo.save(edit);
    }

    @Override
    public void deletePatient(int patient_id) {
        Patient searched_patient = patient_repo
                .findById(patient_id)
                .orElseThrow(() -> new EntityNotExistingException("Patient with id " + patient_id + "doesnt exist\n Associated Entity:" + this.getClass().getSimpleName()));

        patient_repo.delete(searched_patient);
    }
}

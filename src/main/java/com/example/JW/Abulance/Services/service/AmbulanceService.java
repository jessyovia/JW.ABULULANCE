package com.example.JW.Abulance.Services.service;

import java.util.List;

import com.example.JW.Abulance.Services.entity.Ambulance;

public interface AmbulanceService {
    List<Ambulance> getAllAmbulances();

    Ambulance postNewAmbulance(Ambulance new_ambulance);

    Ambulance putExistingAmbulance(String license_plate_number, Ambulance ambulance_to_edit);

    void deleteAmbulance(String license_plate_number);
}

package com.example.JW.Abulance.Services.service.implementations;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.JW.Abulance.Services.entity.Ambulance;
import com.example.JW.Abulance.Services.exceptions.EntityNotExistingException;
import com.example.JW.Abulance.Services.exceptions.UniqueConstraintException;
import com.example.JW.Abulance.Services.repository.AmbulanceRepo;
import com.example.JW.Abulance.Services.service.AmbulanceService;

@Service
public class AmbulanceServiceImpl implements AmbulanceService {
    private final AmbulanceRepo ambulance_repo;

    public AmbulanceServiceImpl(AmbulanceRepo ambulance_repo) {
        this.ambulance_repo = ambulance_repo;
    }

    public List<Ambulance> getAllAmbulances() {
        return ambulance_repo.findAll();
    }

    public Page<Ambulance> getAllAmulancesPaginated(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);

        return ambulance_repo.findAll(pageable);
    }

    @Override
    public Ambulance postNewAmbulance(Ambulance new_ambulance) {
        String licence_plate_number = new_ambulance.getLicence_plate_number();

        if (ambulance_repo.findById(licence_plate_number) == null)
            throw new UniqueConstraintException(
                    "License Plate Number Exists Already! \n Associated Entity:" + this.getClass().getSimpleName());

        return ambulance_repo.save(new_ambulance);
    }

    public List<Ambulance> postNewAmbulances(List<Ambulance> new_ambulances) {
        List<String> ambulances = ambulance_repo
                .findAll()
                .stream()
                .map(Ambulance::getLicence_plate_number)
                .collect(Collectors.toList());

        for (Ambulance ambulance : new_ambulances) {
            String license_num = ambulance.getLicence_plate_number();

            if (ambulances.contains(license_num))
                throw new UniqueConstraintException(
                        "Ambulance with license plate number, " + license_num
                                + " exists already [Bulk Insert Failed!] \n Associated Entity:"
                                + this.getClass().getSimpleName());
        }

        return ambulance_repo.saveAll(new_ambulances);
    }

    @Override
    public Ambulance putExistingAmbulance(String license_plate_number, Ambulance ambulance_to_edit) {
        Ambulance searched_ambulance = ambulance_repo
                .findById(license_plate_number)
                .orElseThrow(() -> new EntityNotExistingException(
                        "Ambulance with id " + license_plate_number + "doesnt exist \n Associated Entity:"
                                + this.getClass().getSimpleName()));

        Ambulance edited = new Ambulance();

        edited.setLicence_plate_number(searched_ambulance.getLicence_plate_number());
        edited.setVehicle_capacity(searched_ambulance.getVehicle_capacity());
        edited.setVehicle_type(searched_ambulance.getVehicle_type());
        edited.setCurrent_location(searched_ambulance.getCurrent_location());
        edited.setAvailability(searched_ambulance.getAvailability());
        edited.setUpdated_at(LocalDateTime.now());

        return ambulance_repo.save(edited);
    }

    @Override
    public void deleteAmbulance(String license_plate_number) {
        Ambulance searched_ambulance = ambulance_repo
                .findById(license_plate_number)
                .orElseThrow(() -> new EntityNotExistingException(
                        "Ambulance with id " + license_plate_number + "doesnt exist \n Associated Entity:"
                                + this.getClass().getSimpleName()));

        ambulance_repo.delete(searched_ambulance);
    }
}

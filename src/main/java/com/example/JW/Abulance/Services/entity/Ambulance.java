package com.example.JW.Abulance.Services.entity;

import java.time.LocalDateTime;
import java.util.List;

import com.example.JW.Abulance.Services.entity.enums.AmbulanceType;
import com.example.JW.Abulance.Services.entity.enums.AvailabilityStatus;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Ambulance {
    @Id
    @Column(unique = true)
    private String licence_plate_number;

    private AmbulanceType vehicle_type;

    private int vehicle_capacity;

    @Column
    private String current_location;

    @Enumerated(EnumType.STRING)
    private AvailabilityStatus.Ambulance availability;

    private LocalDateTime updated_at;

    @ManyToMany(mappedBy = "ambulances")
    private List<Dispatch> dispatch;
}

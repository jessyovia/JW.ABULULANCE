package com.example.JW.Abulance.Services.entity;

import java.time.LocalDateTime;

import com.example.JW.Abulance.Services.entity.enums.AvailabilityStatus;
import com.example.JW.Abulance.Services.entity.enums.EmployeeRole;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Employee {
    @Id
    @Column(unique = true)
    private String id;

    private String name;

    @Enumerated(EnumType.STRING)
    private EmployeeRole role;

    private String contact_information;

    @OneToOne(fetch = FetchType.LAZY)
    private Ambulance assigned_ambulance;

    @Enumerated(EnumType.STRING)
    private AvailabilityStatus.Employee status;

    private LocalDateTime updated_at;
}

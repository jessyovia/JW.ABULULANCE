package com.example.JW.Abulance.Services.entity;

import com.example.JW.Abulance.Services.entity.enums.IncidentSeverity;
import com.example.JW.Abulance.Services.entity.enums.IncidentType;
import com.example.JW.Abulance.Services.entity.enums.RequestStatus;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Request {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int request_id;

    private String caller_name;

    private String caller_phone;

    private String incident_location;

    @Enumerated(EnumType.STRING)
    private IncidentType incident_type;

    @Enumerated(EnumType.STRING)
    private IncidentSeverity severity;

    @Enumerated(EnumType.STRING)
    private RequestStatus status;
}

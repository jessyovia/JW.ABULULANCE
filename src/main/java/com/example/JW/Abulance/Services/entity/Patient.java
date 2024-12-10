package com.example.JW.Abulance.Services.entity;

import java.time.LocalDateTime;

import com.example.JW.Abulance.Services.entity.enums.Gender;

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
public class Patient  {
@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int patient_id;

    private String patient_name;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    private String condition_description;

    private LocalDateTime date_joined = LocalDateTime.now();

    private LocalDateTime updated_at;
}

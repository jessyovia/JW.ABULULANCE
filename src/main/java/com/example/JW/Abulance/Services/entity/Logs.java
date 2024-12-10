package com.example.JW.Abulance.Services.entity;

import java.time.LocalDateTime;

import com.example.JW.Abulance.Services.entity.enums.RequestOutcome;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Logs {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    // patient_id
    @ManyToOne
    @JoinColumn(nullable = false)
    private Patient patient;

    private Dispatch dispatch;

    @Enumerated(EnumType.STRING)
    private RequestOutcome outcome;

    private final LocalDateTime entry_time = LocalDateTime.now();

    private LocalDateTime updated_at;
}

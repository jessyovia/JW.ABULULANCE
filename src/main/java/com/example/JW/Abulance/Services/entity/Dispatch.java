package com.example.JW.Abulance.Services.entity;
import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Dispatch {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int dispatch_id;

    // request_id
    @ManyToOne
    @JoinColumn(name = "request_id", nullable = false)
    private Request request;

    // ambulance_id
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    @JoinTable(name = "dispatch_ambulances", 
        joinColumns = @JoinColumn(name = "dispatch_id", referencedColumnName = "dispatch_id"),
        inverseJoinColumns = @JoinColumn(name = "ambulance_plate_number", referencedColumnName = "licence_plate_number")
    )
    private List<Ambulance> ambulances;

    // employee_id
    @ManyToMany(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    @JoinTable(name = "dispatch_employees", 
        joinColumns = @JoinColumn(name = "dispatch_id", referencedColumnName = "dispatch_id"),
        inverseJoinColumns = @JoinColumn(name = "employee_id", referencedColumnName = "id")
    )
    private List<Employee> employee;

    private final LocalDateTime dispatch_time = LocalDateTime.now();

    private LocalDateTime resolve_time;
}

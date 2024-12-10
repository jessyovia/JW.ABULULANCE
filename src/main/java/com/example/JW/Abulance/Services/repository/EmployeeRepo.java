package com.example.JW.Abulance.Services.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.JW.Abulance.Services.entity.Employee;

public interface EmployeeRepo extends JpaRepository<Employee, String> {
}

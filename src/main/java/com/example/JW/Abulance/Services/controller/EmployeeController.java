package com.example.JW.Abulance.Services.controller;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.JW.Abulance.Services.entity.Employee;
import com.example.JW.Abulance.Services.service.implementations.EmployeeServiceImpl;

@RestController
@RequestMapping("/api/v1/employees")
public class EmployeeController {
    private final EmployeeServiceImpl employee_service;

    public EmployeeController(EmployeeServiceImpl employee_service) {
        this.employee_service = employee_service;
    }

    // GET all employees
    @GetMapping("/")
    public ResponseEntity<List<Employee>> getAllEmployee() {
        List<Employee> all_staffs = employee_service.getAllEmployees();

        return new ResponseEntity<>(all_staffs, HttpStatus.OK);
    }

    // GET all employees in a paginated format
    @GetMapping("/paginated/")
    public ResponseEntity<Page<Employee>> getAllEmployeePaginated(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        Page<Employee> all_staff_paginated = employee_service.getAllEmployeePaginated(page, size);

        return new ResponseEntity<>(all_staff_paginated, HttpStatus.OK);
    }

    // POST an employee
    @PostMapping("/")
    public ResponseEntity<Employee> postNewEmployee(@RequestBody Employee new_employee) {
        Employee added_employee = employee_service.postNewEmployee(new_employee);

        return new ResponseEntity<Employee>(added_employee, HttpStatus.CREATED);
    }

    // PUT an employee
    @PutMapping("/{id}")
    public ResponseEntity<Employee> editEmployee(
            @PathVariable("id") String employee_id,
            Employee employee_edit) {
        return new ResponseEntity<>(
                employee_service.putExistingEmployee(employee_id, employee_edit),
                HttpStatus.OK);
    }

    // DELETE an employee
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteEmployee(@PathVariable("id") String employee_id) {
        employee_service.deleteEmployee(employee_id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

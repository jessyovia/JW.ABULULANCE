package com.example.JW.Abulance.Services.service;

import java.util.List;

import com.example.JW.Abulance.Services.entity.Employee;

public interface EmployeeService {
    List<Employee> getAllEmployees();

    Employee postNewEmployee(Employee new_Employee);

    List<Employee> postMultipleEmployee(List<Employee> Employee_members);

    Employee putExistingEmployee(String Employee_id, Employee Employee_details_to_edit);

    void deleteEmployee(String Employee_id);
}

package com.example.JW.Abulance.Services.service.implementations;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import com.example.JW.Abulance.Services.entity.Employee;
import com.example.JW.Abulance.Services.exceptions.EntityNotExistingException;
import com.example.JW.Abulance.Services.exceptions.UniqueConstraintException;
import com.example.JW.Abulance.Services.repository.EmployeeRepo;
import com.example.JW.Abulance.Services.service.EmployeeService;

public class EmployeeServiceImpl implements EmployeeService {
    private final EmployeeRepo employee_repo;

    public EmployeeServiceImpl(EmployeeRepo employee_repo) {
        this.employee_repo = employee_repo;
    }

    @Override
    public List<Employee> getAllEmployees() {
        return employee_repo.findAll();
    }

    public Page<Employee> getAllEmployeePaginated(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);

        return employee_repo.findAll(pageable);
    }

    @Override
    public Employee postNewEmployee(Employee new_staff) {
        // to add new Employee, ensure Employee ID doesnt exist prior
        String staff_id = new_staff.getId();

        if (employee_repo.findById(staff_id) == null)
            throw new UniqueConstraintException(
                    "Employee Exists Already!\n Associated Entity:" + this.getClass().getSimpleName());

        return employee_repo.save(new_staff);
    }

    @Override
    public List<Employee> postMultipleEmployee(List<Employee> employees) {
        // check for any existing Employee in bulk insert and terminate bulk insert
        // operation if exists
        for (Employee employee : employees) {
            String staff_id = employee.getId();

            if (employee_repo.existsById(staff_id))
                throw new UniqueConstraintException(
                        "Employee with id, " + staff_id + " exists already [Bulk Insert Failed!]\n Associated Entity:"
                                + this.getClass().getSimpleName());
        }

        return employee_repo.saveAll(employees);
    }

    @Override
    public Employee putExistingEmployee(String staff_id, Employee staff_details_to_edit) {
        // ensures Employee ID exists
        if (!employee_repo.existsById(staff_id))
            throw new EntityNotExistingException("Employee with id " + staff_id + "doesnt exist\n Associated Entity:"
                    + this.getClass().getSimpleName());

        Employee edited_staff = new Employee(
                staff_details_to_edit.getId(),
                staff_details_to_edit.getName(),
                staff_details_to_edit.getRole(),
                staff_details_to_edit.getContact_information(),
                staff_details_to_edit.getAssigned_ambulance(),
                staff_details_to_edit.getStatus(),
                LocalDateTime.now());

        return employee_repo.save(edited_staff);
    }

    @Override
    public void deleteEmployee(String staff_id) {
        Employee searched_staff = employee_repo
                .findById(staff_id)
                .orElseThrow(() -> new EntityNotExistingException("Employee with id " + staff_id + "doesnt exist\n Associated Entity:" + this.getClass().getSimpleName()));

        employee_repo.delete(searched_staff);
    }
}

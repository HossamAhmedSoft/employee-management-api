package com.TroyBackEnd.employee_managment.abstracts;

import com.TroyBackEnd.employee_managment.Entity.Employee;
import com.TroyBackEnd.employee_managment.dtos.EmployeeCreate;
import com.TroyBackEnd.employee_managment.dtos.EmployeeUpdate;
import jakarta.validation.Valid;

import java.util.List;
import java.util.UUID;

public interface EmployeeService {
    Employee updateOne(UUID employeeId, EmployeeUpdate employeeUpdate);

    Employee findEmplyeeById(UUID employeeId);

    List<Employee> findAll();

    void DeleteOne(UUID employeeId);

    Employee createOne(@Valid EmployeeCreate employee);
}

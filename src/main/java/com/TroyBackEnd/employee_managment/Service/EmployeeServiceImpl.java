package com.TroyBackEnd.employee_managment.Service;

import com.TroyBackEnd.employee_managment.Entity.Department;
import com.TroyBackEnd.employee_managment.Entity.Employee;
import com.TroyBackEnd.employee_managment.Shared.CustomResponseException;
import com.TroyBackEnd.employee_managment.abstracts.EmployeeService;
import com.TroyBackEnd.employee_managment.dtos.EmployeeCreate;
import com.TroyBackEnd.employee_managment.dtos.EmployeeUpdate;
import com.TroyBackEnd.employee_managment.repositpories.DepartmentRepo;
import com.TroyBackEnd.employee_managment.repositpories.EmployeeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    @Autowired
    EmployeeRepo employeeRepo;
    @Autowired
    DepartmentRepo departmentRepo;


    public Employee updateOne(UUID employeeId, EmployeeUpdate employeeUpdate) {
        Employee existingEmployee = employeeRepo.findById(employeeId).orElseThrow(() ->
                CustomResponseException.ResourceNotFound
                        ("Employee with id " + employeeId + "not found"));

        existingEmployee.setFirstname(employeeUpdate.firstname());
        existingEmployee.setLastname(employeeUpdate.lastname());
        existingEmployee.setPhonenumber(employeeUpdate.phonenumber());
        existingEmployee.setPosition(employeeUpdate.position());
        employeeRepo.save(existingEmployee);
        return existingEmployee;
    }

    public Employee findEmplyeeById(UUID employeeId) {

        return employeeRepo.findById(employeeId).orElseThrow(() ->
                CustomResponseException.ResourceNotFound
                        ("Employee with id " + employeeId + "not found"));
    }

    public List<Employee> findAll() {
        return employeeRepo.findAll();
    }

    public void DeleteOne(UUID employeeId) {
        Optional<Employee> employee = employeeRepo.findById(employeeId);
        employee.ifPresent(value -> employeeRepo.deleteById(value.getId()));
    }

    public Employee createOne(EmployeeCreate employeeCreate) {
        Employee employee = new Employee();
        Department department = departmentRepo.findById(employeeCreate.departmentId()).orElseThrow(() ->
                CustomResponseException.ResourceNotFound
                        ("DepartmentId with id " + employeeCreate.departmentId() + "not found"));
        employee.setFirstname(employeeCreate.firstname());
        employee.setLastname(employeeCreate.lastname());
        employee.setPosition(employeeCreate.position());
        employee.setHireDate(employeeCreate.hireDate());
        employee.setPhonenumber(employeeCreate.phonenumber());
        employee.setEmail(employeeCreate.email());
        employee.setDepertment(department);
        employeeRepo.save(employee);
        return employee;
    }

}

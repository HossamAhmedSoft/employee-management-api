package com.TroyBackEnd.employee_managment.abstracts;

import com.TroyBackEnd.employee_managment.Entity.Department;
import com.TroyBackEnd.employee_managment.dtos.DepartmentCreate;
import com.TroyBackEnd.employee_managment.dtos.DepartmentUpdate;
import jakarta.validation.Valid;

import java.util.List;
import java.util.UUID;

public interface DepartmentService {
    Department findDepartmentById(UUID derpartmentId);

    Department updateOne(UUID departmentId, DepartmentUpdate departmentUpdate);

    List<Department> findAll();

    void DeleteOne(UUID derpartmentId);

    Department createOne(@Valid DepartmentCreate departmentCreate);

}

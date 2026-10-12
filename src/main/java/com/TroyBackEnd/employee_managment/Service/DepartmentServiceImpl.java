package com.TroyBackEnd.employee_managment.Service;

import com.TroyBackEnd.employee_managment.Entity.Department;
import com.TroyBackEnd.employee_managment.Shared.CustomResponseException;
import com.TroyBackEnd.employee_managment.abstracts.DepartmentService;
import com.TroyBackEnd.employee_managment.dtos.DepartmentCreate;
import com.TroyBackEnd.employee_managment.dtos.DepartmentUpdate;
import com.TroyBackEnd.employee_managment.repositpories.DepartmentRepo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class DepartmentServiceImpl implements DepartmentService {
    private final DepartmentRepo departmentRepo;

    public DepartmentServiceImpl(DepartmentRepo departmentRepo) {
        this.departmentRepo = departmentRepo;
    }


    public Department findDepartmentById(UUID derpartmentId) {
        Department department = departmentRepo.findById(derpartmentId).orElseThrow(() ->
                CustomResponseException.ResourceNotFound
                        ("Department with id " + derpartmentId + "not found"));
        return department;
    }

    public Department updateOne(UUID departmentId, DepartmentUpdate departmentUpdate) {
        Department existingDepartment = departmentRepo.findById(departmentId).orElseThrow(() ->
                CustomResponseException.ResourceNotFound
                        ("Department with id " + departmentId + "not found"));

        existingDepartment.setName(departmentUpdate.Name());
        departmentRepo.save(existingDepartment);
        return existingDepartment;
    }

    @Override
    public List<Department> findAll() {
        return departmentRepo.findAll();
    }

    @Override
    public void DeleteOne(UUID derpartmentId) {
        Optional<Department> department = departmentRepo.findById(derpartmentId);
        department.ifPresent(value -> departmentRepo.deleteById(value.getId()));
    }

    @Override
    public Department createOne(DepartmentCreate departmentCreate) {
        Department department = new Department();
        department.setName(departmentCreate.Name());
        departmentRepo.save(department);
        return department;
    }

}

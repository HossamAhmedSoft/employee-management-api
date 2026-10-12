package com.TroyBackEnd.employee_managment.Controller;


import com.TroyBackEnd.employee_managment.Entity.Department;
import com.TroyBackEnd.employee_managment.Shared.GlobalResponse;
import com.TroyBackEnd.employee_managment.abstracts.DepartmentService;
import com.TroyBackEnd.employee_managment.dtos.DepartmentCreate;
import com.TroyBackEnd.employee_managment.dtos.DepartmentUpdate;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/departments")
public class DepartmentController {

    private final DepartmentService departmentService;

    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @GetMapping
    public ResponseEntity<GlobalResponse<List<Department>>> findAll() {
        List<Department> departments = departmentService.findAll();
        return new ResponseEntity<>(new GlobalResponse<>(departments), HttpStatus.OK);

    }

    @GetMapping("/{departmentId}")
    public ResponseEntity<GlobalResponse<Department>> findEmplyeeById(@PathVariable UUID departmentId) {
        Department employee = departmentService.findDepartmentById(departmentId);
        return new ResponseEntity<>(new GlobalResponse<>(employee), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<GlobalResponse<Department>> createOne(@RequestBody @Valid DepartmentCreate departmentCreate) {
        Department newDepartment = departmentService.createOne(departmentCreate);
        return new ResponseEntity<>(new GlobalResponse<>(newDepartment), HttpStatus.CREATED);
    }

    @DeleteMapping("/{departmentId}")
    public ResponseEntity<Void> DeleteOne(@PathVariable UUID departmentId) {
        departmentService.DeleteOne(departmentId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/{departmentId}")
    public ResponseEntity<GlobalResponse<Department>> updateOne(@PathVariable UUID departmentId,
                                                                @RequestBody @Valid DepartmentUpdate departmentUpdate) {
        Department updatedEmployee = departmentService.updateOne(departmentId, departmentUpdate);
        return new ResponseEntity<>(new GlobalResponse<>(updatedEmployee), HttpStatus.OK);

    }
}

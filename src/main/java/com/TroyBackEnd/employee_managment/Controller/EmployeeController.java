package com.TroyBackEnd.employee_managment.Controller;


import com.TroyBackEnd.employee_managment.Entity.Employee;
import com.TroyBackEnd.employee_managment.Entity.LeaveRequest;
import com.TroyBackEnd.employee_managment.Shared.GlobalResponse;
import com.TroyBackEnd.employee_managment.abstracts.EmployeeService;
import com.TroyBackEnd.employee_managment.abstracts.LeaveRequestService;
import com.TroyBackEnd.employee_managment.dtos.EmployeeCreate;
import com.TroyBackEnd.employee_managment.dtos.EmployeeUpdate;
import com.TroyBackEnd.employee_managment.dtos.LeaveRequestCreate;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/employees")
public class EmployeeController {
    private final EmployeeService employeeService;
    final private LeaveRequestService leaveRequestService;

    public EmployeeController(EmployeeService employeeService, LeaveRequestService leaveRequestService) {
        this.employeeService = employeeService;
        this.leaveRequestService = leaveRequestService;
    }

    @GetMapping
    public ResponseEntity<GlobalResponse<List<Employee>>> findAll() {
        List<Employee> employees = employeeService.findAll();
        return new ResponseEntity<>(new GlobalResponse<>(employees), HttpStatus.OK);

    }

    @GetMapping("/{employeeId}")
    public ResponseEntity<GlobalResponse<Employee>> findEmplyeeById(@PathVariable UUID employeeId) {
        Employee employee = employeeService.findEmplyeeById(employeeId);
        return new ResponseEntity<>(new GlobalResponse<>(employee), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<GlobalResponse<Employee>> createOne(@RequestBody @Valid EmployeeCreate employee) {
        Employee newEmployee = employeeService.createOne(employee);
        return new ResponseEntity<>(new GlobalResponse<>(newEmployee), HttpStatus.CREATED);
    }

    @DeleteMapping("/{employeeId}")
    public ResponseEntity<Void> DeleteOne(@PathVariable UUID employeeId) {
        employeeService.DeleteOne(employeeId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/{employeeId}")
    public ResponseEntity<GlobalResponse<Employee>> updateOne(@PathVariable UUID employeeId,
                                                              @RequestBody @Valid EmployeeUpdate employeeUpdated) {
        Employee updatedEmployee = employeeService.updateOne(employeeId, employeeUpdated);
        return new ResponseEntity<>(new GlobalResponse<>(updatedEmployee), HttpStatus.OK);

    }


    @PostMapping("/{employeeId}/leave-requests")
    public ResponseEntity<GlobalResponse<LeaveRequest>> leaveRequest(@RequestBody @Valid LeaveRequestCreate leaveRequestCreate, @PathVariable UUID employeeId) {
        LeaveRequest newleaveRequest = leaveRequestService.createOne(leaveRequestCreate, employeeId);

        return new ResponseEntity<>(new GlobalResponse<>(newleaveRequest), HttpStatus.CREATED);
    }


    @GetMapping("/{employeeId}/leave-requests")
    public ResponseEntity<GlobalResponse<List<LeaveRequest>>> findAllLeavaRequestByEmployeeId(
            @PathVariable UUID employeeId
    ) {
        List<LeaveRequest> leaveRequests = leaveRequestService.findAllByEmployeeId(employeeId);
        return new ResponseEntity<>(new GlobalResponse<>(leaveRequests), HttpStatus.OK);

    }
}

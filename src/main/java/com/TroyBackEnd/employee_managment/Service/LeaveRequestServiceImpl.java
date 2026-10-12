package com.TroyBackEnd.employee_managment.Service;

import com.TroyBackEnd.employee_managment.Entity.Employee;
import com.TroyBackEnd.employee_managment.Entity.LeaveRequest;
import com.TroyBackEnd.employee_managment.Shared.CustomResponseException;
import com.TroyBackEnd.employee_managment.abstracts.LeaveRequestService;
import com.TroyBackEnd.employee_managment.dtos.LeaveRequestCreate;
import com.TroyBackEnd.employee_managment.repositpories.EmployeeRepo;
import com.TroyBackEnd.employee_managment.repositpories.LeaveRequestRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class LeaveRequestServiceImpl implements LeaveRequestService {
    @Autowired
    EmployeeRepo employeeRepo;
    @Autowired
    LeaveRequestRepo leaveRequestRepo;

    public LeaveRequest createOne(LeaveRequestCreate leaveRequestCreate, UUID employeeId) {
        LeaveRequest leaveRequest = new LeaveRequest();

        Employee employee = employeeRepo.findById(employeeId).orElseThrow(() ->
                CustomResponseException.ResourceNotFound
                        ("Employee with id " + employeeId + "not found"));


        leaveRequest.setStatus("PENDING");
        leaveRequest.setReason(leaveRequestCreate.reason());
        leaveRequest.setStartDate(leaveRequestCreate.startDate());
        leaveRequest.setEndDate(leaveRequestCreate.endDate());
        leaveRequest.setEmployee(employee);
        leaveRequestRepo.save(leaveRequest);
        return leaveRequest;
    }

    public List<LeaveRequest> findAllByEmployeeId(UUID employeeId) {

        return leaveRequestRepo.findAllByEmployeeId(employeeId);
    }
}

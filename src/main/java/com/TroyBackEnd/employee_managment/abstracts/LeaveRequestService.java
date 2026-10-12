package com.TroyBackEnd.employee_managment.abstracts;

import com.TroyBackEnd.employee_managment.Entity.LeaveRequest;
import com.TroyBackEnd.employee_managment.dtos.LeaveRequestCreate;
import jakarta.validation.Valid;

import java.util.List;
import java.util.UUID;

public interface LeaveRequestService {
    LeaveRequest createOne(@Valid LeaveRequestCreate leaveRequestCreate, UUID employeeId);

    List<LeaveRequest> findAllByEmployeeId(UUID employeeId);
}

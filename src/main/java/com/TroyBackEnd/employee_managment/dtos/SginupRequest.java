package com.TroyBackEnd.employee_managment.dtos;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.UUID;

public record SginupRequest(
        @NotNull(message = "username is Required")
        @Size(min = 2, max = 50, message = "min is 2 charcteors and max is 50 charcteors")
        String username,
        @NotNull(message = "username is Required")
        @Size(min = 2, max = 50, message = "min is 2 charcteors and max is 50 charcteors")
        String password,
        @NotNull(message = "employee id is Required")
        UUID employeeId

) {
}

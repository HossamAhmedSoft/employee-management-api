package com.TroyBackEnd.employee_managment.dtos;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record DepartmentCreate(
        @NotNull(message = "Name is Required")
        @Size(min = 2, max = 50, message = "min is 2 charcteors and max is 50 charcteors")
        String Name
) {
}

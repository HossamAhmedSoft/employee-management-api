package com.TroyBackEnd.employee_managment.dtos;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record EmployeeUpdate(
        @NotNull(message = "FirstName is Required")
        @Size(min = 2, max = 50, message = "min is 2 charcteors and max is 50 charcteors")
        String firstname,

        @NotNull(message = "LastName is Required")
        @Size(min = 2, max = 50, message = "min is 2 charcteors and max is 50 charcteors")
        String lastname,

        @NotNull(message = "phonenumber is Required")
        @Pattern(regexp = "^\\+?[0-9]{10,15}$", message = "invaild phonenumber Format")
        String phonenumber,

        @NotNull(message = "position is Required")
        @Size(min = 2, max = 50, message = "min is 2 charcteors and max is 50 charcteors")
        String position) {
}

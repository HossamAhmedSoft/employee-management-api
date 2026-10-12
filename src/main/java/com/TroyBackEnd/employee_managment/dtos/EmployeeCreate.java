package com.TroyBackEnd.employee_managment.dtos;

import jakarta.validation.constraints.*;

import java.time.LocalDate;
import java.util.UUID;

public record EmployeeCreate(
        @NotNull(message = "FirstName is Required")
        @Size(min = 2, max = 50, message = "min is 2 charcteors and max is 50 charcteors")
        String firstname,

        @NotNull(message = "LastName is Required")
        @Size(min = 2, max = 50, message = "min is 2 charcteors and max is 50 charcteors")
        String lastname,

        @Email(message = "invaild Email Format")
        String email,

        @NotNull(message = "phonenumber is Required")
        @Pattern(regexp = "^\\+?[0-9]{10,15}$", message = "invaild phonenumber Format")
        String phonenumber,

        @NotNull(message = "hireDate is Required")
        @PastOrPresent(message = "Hire data cannot be in the futuer")
        LocalDate hireDate,

        @NotNull(message = "position is Required")
        @Size(min = 2, max = 50, message = "min is 2 charcteors and max is 50 charcteors")
        String position,

        @NotNull(message = "DepartmentId is Required")
        UUID departmentId
) {


}

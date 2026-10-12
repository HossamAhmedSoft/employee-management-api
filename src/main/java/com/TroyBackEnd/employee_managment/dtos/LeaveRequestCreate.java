package com.TroyBackEnd.employee_managment.dtos;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public record LeaveRequestCreate(
        @NotNull(message = "Reason is Required")
        @Size(min = 2, max = 100, message = "min is 2 charcteors and max is 100 charcteors")
        String reason,

        @NotNull(message = "Start Date is Required")
        @FutureOrPresent(message = "Start Date Should be now or in the future")
        LocalDate startDate,

        @NotNull(message = "end Date is Required")
        @FutureOrPresent(message = "end Date Should be now or in the future")
        LocalDate endDate
) {
}

package com.TroyBackEnd.employee_managment.Shared;

import lombok.Getter;

import java.util.List;

@Getter
public class GlobalResponse<T> {
    public final static String SUCCESS = "success";
    public final static String ERROR = "error";
    private final List<ErrorItem> errors;
    private final String status;
    private final T data;

    public GlobalResponse(List<ErrorItem> errors) {
        this.status = ERROR;
        this.data = null;
        this.errors = errors;


    }

    public GlobalResponse(T data) {
        this.status = SUCCESS;
        this.data = data;
        this.errors = null;


    }

    public record ErrorItem(String message) {

    }
}
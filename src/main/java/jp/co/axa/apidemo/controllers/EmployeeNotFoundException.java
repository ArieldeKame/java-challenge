package jp.co.axa.apidemo.controllers;

public class EmployeeNotFoundException extends RuntimeException {
    public EmployeeNotFoundException() {
    }

    public EmployeeNotFoundException(Long employeeId ) {
        super("Employee: " + employeeId + " not found.");
    }
}

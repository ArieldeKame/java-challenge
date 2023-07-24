package jp.co.axa.apidemo.controllers;

public class EmployeeNotFoundException extends RuntimeException {
    public EmployeeNotFoundException() {
    }

    // handle employee not found case
    public EmployeeNotFoundException(Long employeeId ) {
        super("Employee: " + employeeId + " not found.");
    }
}

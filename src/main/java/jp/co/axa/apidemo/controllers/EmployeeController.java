package jp.co.axa.apidemo.controllers;

import jp.co.axa.apidemo.entities.Employee;
import jp.co.axa.apidemo.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;

    public void setEmployeeService(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    // get all registered employees
    @GetMapping("/employees")
    public List<Employee> getEmployees() {
        return employeeService.retrieveEmployees();
    }

    // get a specific employee by ID
    @GetMapping("/employees/{employeeId}")
    public Employee getEmployee(@PathVariable(name="employeeId")Long employeeId) {
        return employeeService.getEmployee(employeeId);
    }

    // register a new employee
    @PostMapping("/employees")
    public Employee addEmployee(@RequestBody Employee employee){
        employee = employeeService.addEmployee(employee);
        System.out.println("Employee Saved Successfully");
        return employee;
    }

    // delete a registered employee
    @DeleteMapping("/employees/{employeeId}")
    public void deleteEmployee(@PathVariable(name="employeeId")Long employeeId){
        Employee emp = employeeService.getEmployee(employeeId);
        if(emp != null) {
            employeeService.deleteEmployee(employeeId);
            System.out.println("Employee Deleted Successfully");
        }
    }

    // update a registered employee
    @PutMapping("/employees/{employeeId}")
    public Employee updateEmployee(@RequestBody Employee employee,
                               @PathVariable(name="employeeId")Long employeeId){
        Employee emp = employeeService.getEmployee(employeeId);
        if(emp != null) {
            emp.setName(employee.getName());
            emp.setDepartment(employee.getDepartment());
            emp.setSalary(employee.getSalary());
            return employeeService.updateEmployee(emp);
        }

        return null;
    }
}

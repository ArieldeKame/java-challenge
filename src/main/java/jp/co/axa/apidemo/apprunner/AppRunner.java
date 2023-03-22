package jp.co.axa.apidemo.apprunner;

import jp.co.axa.apidemo.entities.Employee;
import jp.co.axa.apidemo.repositories.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AppRunner implements CommandLineRunner {

    private static final int NUMBER_OF_EMPLOYEES = 1000;
    private final EmployeeRepository employeeRepository;

    @Override
    public void run(String... args) {
        for (int i = 1; i <= NUMBER_OF_EMPLOYEES; i++) {
            employeeRepository.save(new Employee("employee_name" + i, "employee_department" + i, 1000 + i));
        }
    }

}
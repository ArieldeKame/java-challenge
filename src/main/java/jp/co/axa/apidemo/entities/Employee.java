package jp.co.axa.apidemo.entities;

import io.swagger.annotations.ApiParam;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

@Entity
@Table(name="EMPLOYEE")
public class Employee {
    public Employee() {}

    // create a new employee using provided data
    public Employee(Long id, String name, String department, Integer salary) {
        this.id = id;
        this.name = name;
        this.department = department;
        this.salary = salary;
    }

    @Getter
    @Setter
    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE,generator="id_gen")
    @SequenceGenerator(name="id_gen",sequenceName="id_seq_gen",initialValue=1,allocationSize=1)
    @ApiParam(hidden = true)
    private Long id;

    @Getter
    @Setter
    @Column(name="NAME")
    @Size(min = 1, max = 50)
    private String name;

    @Getter
    @Setter
    @Column(name="SALARY")
    @Min(0)
    private Integer salary;

    @Getter
    @Setter
    @Size(min = 1, max = 50)
    @Column(name="DEPARTMENT")
    private String department;
}

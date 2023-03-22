package jp.co.axa.apidemo.entities;

import io.swagger.annotations.ApiParam;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name="EMPLOYEE")
public class Employee {

    public Employee() {}

    public Employee(String name, String department, Integer salary) {
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
    private String name;

    @Getter
    @Setter
    @Column(name="SALARY")
    private Integer salary;

    @Getter
    @Setter
    @Column(name="DEPARTMENT")
    private String department;

}

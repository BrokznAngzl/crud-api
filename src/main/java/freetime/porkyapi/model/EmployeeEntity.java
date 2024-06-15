package freetime.porkyapi.model;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigInteger;

@Entity
@Data
//@Table(name = "employee")
public class EmployeeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private BigInteger employeeID;

//    @Column(name = "")
    private String employeeName;
    private String departmentID;
    private String telephone;
    private String address;
}

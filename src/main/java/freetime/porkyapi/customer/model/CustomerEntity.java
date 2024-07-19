package freetime.porkyapi.customer.model;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigInteger;

@Entity
@Data
@Table(name = "customer")
public class CustomerEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "customerid")
    private BigInteger customerID;

    @Column(name = "customername")
    private String customerName;
    @Column(name = "email")
    private String email;
    @Column(name = "phone")
    private String phone;
}

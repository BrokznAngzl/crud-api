package freetime.porkyapi.farm.model;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigInteger;

@Entity
@Data
@Table(name = "farm")
public class FarmEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private BigInteger farmID;

    @Column(name = "farmname")
    private String farmName;
    private String location;
}

package freetime.porkyapi.housing.model;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigInteger;

@Entity
@Data
@Table(name = "housing")
public class HousingEntity {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private BigInteger housingID;

    @Column(name = "housingname")
    private String housingName;
    @Column(name = "stallquanity")
    private Integer stallQuanity;
}

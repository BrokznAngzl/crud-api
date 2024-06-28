package freetime.porkyapi.farm.model;

import freetime.porkyapi.housing.model.HousingEntity;
import jakarta.persistence.*;
import lombok.Data;

import java.math.BigInteger;
import java.util.List;

@Entity
@Data
@Table(name = "farm")
public class FarmEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "farmid")
    private BigInteger farmID;

    @Column(name = "farmname")
    private String farmName;
    @Column(name = "location")
    private String location;
}

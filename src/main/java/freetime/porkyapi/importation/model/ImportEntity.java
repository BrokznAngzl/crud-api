package freetime.porkyapi.importation.model;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;

@Entity
@Data
@Table(name = "import")
public class ImportEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private BigInteger importID;

    @Column(name = "importcode")
    private String importCode;
    @Column(name = "date")
    private String date;
    @Column(name = "quanity")
    private BigDecimal quanity;
    @Column(name = "avgweight")
    private BigDecimal avgWeight;
    @Column(name = "breeds")
    private BigInteger breeds;
    @Column(name = "housingid")
    private BigInteger housingID;
}

package freetime.porkyapi.casedetail.model;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigInteger;

@Entity
@Data
@Table(name = "casecause")
public class CaseDetailEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "causeid")
    private BigInteger causeID;

    @Column(name = "cause")
    private String cause;
}

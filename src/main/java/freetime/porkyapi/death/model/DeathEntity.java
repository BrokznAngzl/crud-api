package freetime.porkyapi.death.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.math.BigInteger;

@Getter
@Setter
@Entity
@Table(name = "death")
public class DeathEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "deathid", nullable = false)
    private BigInteger deathID;

    @Column(name = "date", nullable = false, length = 10)
    private String date;

    @Column(name = "cause", nullable = false)
    private BigInteger cause;

    @Column(name = "quantity", nullable = false)
    private BigDecimal quantity;

    @Column(name = "importid", nullable = false)
    private BigInteger importid;


}
package freetime.porkyapi.export.model;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.math.BigInteger;

@Entity
@Data
@Table(name = "export")
public class ExportEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "exportid")
    private BigInteger exportID;

    @Column(name = "costomerid")
    private BigInteger customerID;

    @Column(name = "date", nullable = false, length = 10)
    private String date;

    @Column(name = "exportcode")
    private String exportCode;

    @Column(name = "quantity")
    private BigDecimal quantity;

    @Column(name = "avgweight")
    private BigDecimal avgweight;

    @Column(name = "importid")
    private BigInteger importID;

}
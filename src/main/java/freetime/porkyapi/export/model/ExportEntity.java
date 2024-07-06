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
    @Column(name = "exportid", nullable = false)
    private BigInteger exportID;

    @Column(name = "customerid", nullable = false)
    private BigInteger customerID;

    @Column(name = "date", nullable = false, length = 10)
    private String date;

    @Column(name = "exportcode", nullable = false)
    private String exportCode;

    @Column(name = "quantity", nullable = false)
    private BigDecimal quantity;

    @Column(name = "avgweight", nullable = false)
    private BigDecimal avgweight;

    @Column(name = "importid", nullable = false)
    private BigInteger importID;

}
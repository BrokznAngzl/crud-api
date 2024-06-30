package freetime.porkyapi.importion.model;

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

    @Column(name = "date")
    private Date date;
    @Column(name = "quanity")
    private BigDecimal quanity;
    @Column(name = "avgweight")
    private BigDecimal avgWeight;
    @Column(name = "breeds")
    private int breeds;
    @Column(name = "housingid")
    private BigInteger housingID;

//    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "farmid", referencedColumnName = "farmid", insertable = false, updatable = false)
//    private FarmEntity farm;
//
//    @Override
//    public String toString() {
//        return "{" +
//                "\"housingID\": " + housingID +
//                ", \"housingName\": \"" + housingName + '\"' +
//                ", \"stallQuanity\": " + stallQuanity +
//                ", \"farmName\": \"" + farm.getFarmName() + '\"' +
//                "}";
//    }
}

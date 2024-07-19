package freetime.porkyapi.housing.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import freetime.porkyapi.farm.model.FarmEntity;
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
    @Column(name = "farmid")
    private BigInteger farmID;

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

package freetime.porkyapi.importation.model;

import lombok.Data;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;

@Data
public class ImportResponseModel {
    private BigInteger importID;
    private Date date;
    private BigDecimal quanity;
    private BigDecimal avgWeight;
    private BigInteger breeds;
    private BigInteger housingID;

    public ImportResponseModel(BigInteger importID, Date date, BigDecimal quanity,
                               BigDecimal avgWeight, BigInteger breeds, BigInteger housingID) {
        this.importID = importID;
        this.date = date;
        this.quanity = quanity;
        this.avgWeight = avgWeight;
        this.breeds = breeds;
        this.housingID = housingID;
    }
}

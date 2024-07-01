package freetime.porkyapi.importation.model;

import lombok.Data;

import java.math.BigDecimal;
import java.math.BigInteger;

@Data
public class ImportRequestModel {
    private BigInteger importID;
    private String startDate;
    private String endDate;
    private BigInteger breedsID;
    private BigInteger housingID;
    private BigDecimal avgWeight;
    private BigDecimal quanity;
}

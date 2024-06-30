package freetime.porkyapi.importation.model;

import lombok.Data;

import java.math.BigDecimal;
import java.math.BigInteger;

@Data
public class ImportResponseModel {
    private BigInteger importID;
    private String date;
    private String breedsName;
    private String housingName;
    private BigDecimal avgWeight;
    private BigDecimal quanity;
}

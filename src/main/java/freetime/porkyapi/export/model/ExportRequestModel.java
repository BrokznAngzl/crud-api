package freetime.porkyapi.export.model;

import lombok.Data;

import java.math.BigDecimal;
import java.math.BigInteger;

@Data
public class ExportRequestModel {
    private String startDate;
    private String endDate;
    private String exportCode;
    private BigDecimal quantity;
    private BigInteger importID;
    private BigInteger customerID;

}
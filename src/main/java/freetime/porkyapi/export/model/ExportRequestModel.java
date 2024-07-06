package freetime.porkyapi.export.model;

import lombok.Data;

import java.math.BigDecimal;
import java.math.BigInteger;

@Data
public class ExportRequestModel {
    private BigInteger exportID;
    private String startDate;
    private String endDate;
    private String exportCode;
    private BigDecimal quantity;
    private BigDecimal avgweight;
    private BigInteger importID;

}
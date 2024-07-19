package freetime.porkyapi.export.model;

import lombok.Data;

import java.math.BigDecimal;
import java.math.BigInteger;

@Data
public class ExportResponseModel {
    private BigInteger exportID;
    private String date;
    private String exportCode;
    private BigDecimal quantity;
    private BigDecimal avgweight;
    private String importCode;
    private String customerName;

}
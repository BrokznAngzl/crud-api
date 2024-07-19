package freetime.porkyapi.report.adgreport.model;

import lombok.Data;

import java.math.BigInteger;

@Data
public class ADGRptRequestModel {
    private BigInteger exportID;
    private String startDate;
    private String endDate;
}

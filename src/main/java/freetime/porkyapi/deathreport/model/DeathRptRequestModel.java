package freetime.porkyapi.deathreport.model;

import lombok.Data;

import java.math.BigInteger;

@Data
public class DeathRptRequestModel {
    private BigInteger importCode;
    private String startDate;
    private String endDate;
}

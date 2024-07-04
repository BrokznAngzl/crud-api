package freetime.porkyapi.deathreport.model;

import lombok.Data;

@Data
public class DeathRptRequestModel {
    private String importCode;
    private String startDate;
    private String endDate;
    private String importQuantity;
    private String totalDeath;
    private String percentage;
}

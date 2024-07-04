package freetime.porkyapi.deathreport.model;

import lombok.Data;

@Data
public class DeathRptResponseModel {
    private String importCode;
    private String date;
    private String importQuantity;
    private int totalDeath;
    private String percentage;
}

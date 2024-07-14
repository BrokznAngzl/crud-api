package freetime.porkyapi.report.adgreport.model;

import lombok.Data;

@Data
public class ADGRptResponseModel {
    private String exportCode;
    private String grainWeight;
    private String day;
    private String adgRate;
}

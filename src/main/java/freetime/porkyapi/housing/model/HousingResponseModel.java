package freetime.porkyapi.housing.model;

import lombok.Data;

import java.math.BigInteger;

@Data
public class HousingResponseModel {

    private BigInteger housingID;
    private String housingName;
    private Integer stallQuanity;
    private String farmName;

//    public HousingResponseModel(BigInteger housingID, String housingName, Integer stallQuanity, String farmName) {
//        this.housingID = housingID;
//        this.housingName = housingName;
//        this.stallQuanity = stallQuanity;
//        this.farmName = farmName;
//    }
}

package freetime.porkyapi.death.model;

import lombok.Data;

import java.math.BigDecimal;
import java.math.BigInteger;

@Data
public class DeathResponseModel {
    private BigInteger deathID;
    private String date;
    private String cause;
    private BigDecimal quantity;
    private String importCode;
}
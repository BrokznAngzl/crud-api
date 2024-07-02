package freetime.porkyapi.death.model;

import lombok.Data;

import java.math.BigDecimal;
import java.math.BigInteger;

@Data
public class DeathRequestModel {
    private BigInteger deathID;
    private String date;
    private BigInteger cause;
    private BigDecimal quantity;
    private BigInteger importCode;
}
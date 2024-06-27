package freetime.porkyapi.validator;

import freetime.porkyapi.farm.model.FarmEntity;

import java.math.BigInteger;

public class Validator {
    public static Boolean validateID(BigInteger id) {
        return id != null && id.compareTo(BigInteger.ZERO) > 0;
    }

    public static Boolean validateFarm(FarmEntity farm) {
        return farm != null;
    }

    public static Boolean validateFarm(FarmEntity farm, Boolean id) {
        return farm != null && validateID(farm.getFarmID());
    }
}

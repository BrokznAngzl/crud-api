package freetime.porkyapi.validator;

import freetime.porkyapi.breeds.model.BreedsEntity;
import freetime.porkyapi.customer.model.CustomerEntity;
import freetime.porkyapi.farm.model.FarmEntity;
import freetime.porkyapi.housing.model.HousingEntity;
import freetime.porkyapi.importation.model.ImportEntity;

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

    public static Boolean validateHousing(HousingEntity housing) {
        return housing != null;
    }

    public static Boolean validateCustomer(CustomerEntity customer) {
        return customer != null;
    }

    public static Boolean validateBreeds(BreedsEntity breeds) {
        return breeds != null;
    }

    public static Boolean validateImport(ImportEntity importation) {
        return importation != null;
    }
}

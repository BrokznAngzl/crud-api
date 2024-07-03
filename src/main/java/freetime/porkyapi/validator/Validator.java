package freetime.porkyapi.validator;

import freetime.porkyapi.breeds.model.BreedsEntity;
import freetime.porkyapi.casedetail.model.CaseDetailEntity;
import freetime.porkyapi.customer.model.CustomerEntity;
import freetime.porkyapi.death.model.DeathEntity;
import freetime.porkyapi.death.model.DeathRequestModel;
import freetime.porkyapi.farm.model.FarmEntity;
import freetime.porkyapi.housing.model.HousingEntity;
import freetime.porkyapi.importation.model.ImportEntity;
import freetime.porkyapi.importation.model.ImportRequestModel;
import freetime.porkyapi.util.DateUtil;

import java.math.BigDecimal;
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

    public static Boolean validateCase(CaseDetailEntity caseDetail) {
        return caseDetail != null;
    }

    public static Boolean validateCustomer(CustomerEntity customer) {
        return customer != null;
    }

    public static Boolean validateBreeds(BreedsEntity breeds) {
        return breeds != null;
    }

    public static Boolean validateDeath(DeathEntity death) {
        if (death != null) {
            if (death.getQuantity() != null && !(death.getQuantity().compareTo(BigDecimal.ZERO) < 0)) {
                return !DateUtil.afterToday(death.getDate(), String.valueOf(DateUtil.ISO_LOCAL_DATE));
            }
            return false;
        }
        return false;
    }

    public static Boolean validateDeath(DeathRequestModel death) {
        if (death != null) {
            if ((death.getStartDate() != null && !death.getStartDate().isEmpty()) &&
                    (death.getEndDate() != null && !death.getEndDate().isEmpty())) {
                return DateUtil.dateChecker(death.getStartDate(), death.getEndDate(),
                        String.valueOf(DateUtil.ISO_LOCAL_DATE));
            }
            return true;
        }
        return false;
    }

    public static Boolean validateImport(ImportEntity importation) {
        if (importation != null) {
            if (!(importation.getQuanity().compareTo(BigDecimal.ZERO) < 0) &&
                    !(importation.getAvgWeight().compareTo(BigDecimal.ZERO) < 0)) {
                return !DateUtil.afterToday(importation.getDate(), String.valueOf(DateUtil.ISO_LOCAL_DATE));
            }
            return false;
        }
        return false;
    }

    public static Boolean validateImport(ImportRequestModel importation) {
        if (importation != null) {
                if ((importation.getStartDate() != null && !importation.getStartDate().isEmpty()) &&
                        (importation.getEndDate() != null && !importation.getEndDate().isEmpty())) {
                    return DateUtil.dateChecker(importation.getStartDate(), importation.getEndDate(),
                            String.valueOf(DateUtil.ISO_LOCAL_DATE));
                }
                return true;
        }
        return false;
    }
}
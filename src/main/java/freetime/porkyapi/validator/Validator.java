package freetime.porkyapi.validator;

import freetime.porkyapi.breeds.model.BreedsEntity;
import freetime.porkyapi.casedetail.model.CaseDetailEntity;
import freetime.porkyapi.customer.model.CustomerEntity;
import freetime.porkyapi.farm.model.FarmEntity;
import freetime.porkyapi.housing.model.HousingEntity;
import freetime.porkyapi.importation.model.ImportEntity;
import freetime.porkyapi.importation.model.ImportRequestModel;
import freetime.porkyapi.util.DateUtil;

import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;

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

    public static Boolean validateImport(ImportEntity importation, String controller) {
        if (importation != null) {

            if (controller.equals("create") || controller.equals("update")) {
                return !DateUtil.afterToday(importation.getDate(), String.valueOf(DateUtil.ISO_LOCAL_DATE));
            }
        }
        return false;
    }

    public static Boolean validateImport(ImportRequestModel importation, String controller) {
        if (importation != null) {

            if (controller.equals("find")) {
                if ( (importation.getStartDate() != null && !importation.getStartDate().isEmpty()) &&
                        (importation.getEndDate() != null && !importation.getEndDate().isEmpty()) ) {
                    return DateUtil.dateChecker(importation.getStartDate(), importation.getEndDate(),
                            String.valueOf(DateUtil.ISO_LOCAL_DATE));
                }
                else {
                    return true;
                }

            } else {
                return false;
            }
        }
        return false;
    }
}
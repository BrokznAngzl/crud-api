package freetime.porkyapi.report.adgreport.service;


import freetime.porkyapi.report.adgreport.dao.ADGRptDAO;
import freetime.porkyapi.report.adgreport.model.ADGRptRequestModel;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Log4j2
@Service
public class ADGRptService {

    @Autowired
    private ADGRptDAO ADGRptDAO;

    public ResponseEntity<?> getAllADGReport(ADGRptRequestModel requestModel) {
        try {
            List<?> result = new ArrayList<>();
            result = ADGRptDAO.findAverageDailyGrain(requestModel);
            log.info("get all Average Daily Grain report successfully.");
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            log.error(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }


}

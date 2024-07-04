package freetime.porkyapi.deathreport.service;


import freetime.porkyapi.deathreport.dao.DeathRptDAO;
import freetime.porkyapi.deathreport.model.DeathRptRequestModel;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Log4j2
@Service
public class DeathRptService {

    @Autowired
    private DeathRptDAO deathRptDAO;

    public ResponseEntity<?> getAllDeath(DeathRptRequestModel death) {
        try {
            List<?> result = new ArrayList<>();
            result = deathRptDAO.findDeathReport(death);
            log.info("get all death report successfully.");
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            log.error(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }


}

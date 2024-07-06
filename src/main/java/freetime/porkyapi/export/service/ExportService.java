package freetime.porkyapi.export.service;


import freetime.porkyapi.export.dao.ExportDAO;
import freetime.porkyapi.export.model.ExportEntity;
import freetime.porkyapi.export.model.ExportRequestModel;
import freetime.porkyapi.export.repository.ExportRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Log4j2
@Service
public class ExportService {
    @Autowired
    private ExportRepository exportRepository;
    @Autowired
    private ExportDAO exportDAO;

    public ResponseEntity<?> saveExport(ExportEntity export, HttpStatus status, boolean isNewRecord) {
//        try {
//            ExportEntity importation = exportRepository.findExportEntityByExportID(export.getExportid());
//            BigDecimal exportSum = exportDAO.getExportSum(export.getExportid());
//            BigDecimal importQuantity = importation.getQuanity();
//
//            BigDecimal currentTotal = isNewRecord ? importQuantity.subtract(exportSum)
//                    : importQuantity.subtract(exportSum.subtract(getPrevQuantity(export.getExportID())));
//
//            if ((export.getQuantity().compareTo(currentTotal) <= 0)) {
//                exportRepository.save(export);
//                log.info("save {} successfully.", export);
//                return ResponseEntity.status(status).build();
//            } else {
//                log.warn("could not save {}", export);
//                return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
//            }
//        } catch (Exception e) {
//            log.error(e.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
//        }
    }

    public ResponseEntity<?> getAllExport(ExportRequestModel export) {
        try {
            List<?> result = new ArrayList<>();
            result = exportDAO.getAllExport(export);
            ;
            log.info("get all export successfully.");
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            log.error(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    public ResponseEntity<?> getExportById(BigInteger id) {
        try {
            Optional<ExportEntity> result = exportRepository.findById(id);
            log.info("get export by id {} successfully.", id);
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            log.error(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    public ResponseEntity<?> getExportByCode(String code) {
        try {
            ExportEntity result = exportRepository.findExportEntityByExportCode(code);
            log.info("get export by code {} successfully.", code);
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            log.error(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    public ResponseEntity<?> deleteExport(BigInteger id) {
        try {
            exportRepository.deleteById(id);
            log.info("delete export id{} successfully.", id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } catch (Exception e) {
            log.error(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }


}

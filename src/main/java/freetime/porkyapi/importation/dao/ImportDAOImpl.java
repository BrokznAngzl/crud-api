package freetime.porkyapi.importation.dao;

import freetime.porkyapi.importation.model.ImportRequestModel;
import freetime.porkyapi.importation.model.ImportResponseModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ImportDAOImpl implements ImportDAO {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<?> getImportWithHousingName() {
        String query = "SELECT i.importid, i.date, i.avgweight, i.quanity, b.breedsname, h.housingname \n" +
                "FROM import i\n" +
                "         LEFT JOIN housing h ON i.housingid = h.housingid \n" +
                "         LEFT JOIN breeds b ON i.breeds = b.breedsid \n" +
                "WHERE 1=1";

        return jdbcTemplate.query(query, new BeanPropertyRowMapper<>(ImportResponseModel.class));
    }

    @Override
    public List<?> getImportWithHousingName(ImportRequestModel importation) {
        StringBuilder sql = new StringBuilder(
                "SELECT i.importid, i.date, i.avgweight, i.quanity, b.breedsname, h.housingname\n" +
                        "FROM import i\n" +
                        "         LEFT JOIN housing h ON i.housingid = h.housingid\n" +
                        "         LEFT JOIN breeds b ON i.breeds = b.breedsid\n" +
                        "WHERE 1 = 1\n"
        );

        List<Object> params = new ArrayList<>();
        if (importation != null) {
            if ((importation.getStartDate() != null && !importation.getStartDate().isEmpty()) &&
                    (importation.getEndDate() != null && !importation.getEndDate().isEmpty())) {
                sql.append("AND i.date BETWEEN ? AND ? \n");
                params.add(importation.getStartDate());
                params.add(importation.getEndDate());
            } else if (importation.getStartDate() != null && !importation.getStartDate().isEmpty()) {
                sql.append("AND i.date >= ? \n");
                params.add(importation.getStartDate());
            } else if (importation.getEndDate() != null && !importation.getEndDate().isEmpty()) {
                sql.append("AND i.date <= ? \n");
                params.add(importation.getEndDate());
            }

            if (importation.getBreedsID() != null) {
                sql.append("AND i.breeds = ? \n");
                params.add(importation.getBreedsID());
            }
            if (importation.getHousingID() != null) {
                sql.append("AND i.housingid = ? \n");
                params.add(importation.getHousingID());
            }
            if (importation.getAvgWeight() != null) {
                sql.append("AND i.avgweight = ? \n");
                params.add(importation.getAvgWeight());
            }
            if (importation.getQuanity() != null) {
                sql.append("AND i.quanity = ? \n");
                params.add(importation.getQuanity());
            }


        }

        return jdbcTemplate.query(sql.toString(), params.toArray(), new BeanPropertyRowMapper<>(ImportResponseModel.class));
    }
}

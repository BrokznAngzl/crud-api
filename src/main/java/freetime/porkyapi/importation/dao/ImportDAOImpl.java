package freetime.porkyapi.importation.dao;

import freetime.porkyapi.consts.SQLAssistant;
import freetime.porkyapi.importation.model.ImportEntity;
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
    public List<?> getImportWithHousingName(ImportEntity importation) {
        StringBuilder sql = new StringBuilder(
                "SELECT h.housingid, h.housingname, h.stallquanity, f.farmname \n" +
                        "FROM housing h \n" +
                        "LEFT JOIN farm f ON h.farmid = f.farmid \n" +
                        "WHERE 1=1 \n");

        List<Object> params = new ArrayList<>();
        if (importation != null) {
            if (importation.getDate() != null && !importation.getDate().isEmpty()) {
//                pls check date
                sql.append("AND LOWER(housingname) LIKE LOWER(?) \n");
                params.add(SQLAssistant.likeAll(importation.getDate()));
            }
        }

        return jdbcTemplate.query(sql.toString(), params.toArray(), new BeanPropertyRowMapper<>(ImportResponseModel.class));
    }
}

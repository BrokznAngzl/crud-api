package freetime.porkyapi.death.dao;

import freetime.porkyapi.death.model.DeathRequestModel;
import freetime.porkyapi.death.model.DeathResponseModel;
import freetime.porkyapi.importation.model.ImportRequestModel;
import freetime.porkyapi.importation.model.ImportResponseModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DeathDAOImpl implements DeathDAO {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<?> getAllDeath() {
        String query =
                "SELECT d.deathid, d.date, c.cause, d.quantity, i.importcode\n" +
                        "FROM death d\n" +
                        "LEFT JOIN casecause c on c.causeid = d.cause\n" +
                        "LEFT JOIN public.import i on i.importid = d.importid\n" +
                        "WHERE 1=1\n";

        return jdbcTemplate.query(query, new BeanPropertyRowMapper<>(DeathResponseModel.class));
    }

    @Override
    public List<?> getAllDeathWhere(DeathRequestModel death) {
        StringBuilder sql = new StringBuilder(
                "SELECT d.deathid, d.date, c.cause, d.quantity, i.importcode\n" +
                        "FROM death d\n" +
                        "LEFT JOIN casecause c on c.causeid = d.cause\n" +
                        "LEFT JOIN public.import i on i.importid = d.importid\n" +
                        "WHERE 1=1"
        );

        List<Object> params = new ArrayList<>();
//        if (importation != null) {
//            if ((importation.getStartDate() != null && !importation.getStartDate().isEmpty()) &&
//                    (importation.getEndDate() != null && !importation.getEndDate().isEmpty())) {
//                sql.append("AND i.date BETWEEN ? AND ? \n");
//                params.add(importation.getStartDate());
//                params.add(importation.getEndDate());
//            } else if (importation.getStartDate() != null && !importation.getStartDate().isEmpty()) {
//                sql.append("AND i.date >= ? \n");
//                params.add(importation.getStartDate());
//            } else if (importation.getEndDate() != null && !importation.getEndDate().isEmpty()) {
//                sql.append("AND i.date <= ? \n");
//                params.add(importation.getEndDate());
//            }
//
//            if (importation.getBreedsID() != null) {
//                sql.append("AND i.breeds = ? \n");
//                params.add(importation.getBreedsID());
//            }
//            if (importation.getHousingID() != null) {
//                sql.append("AND i.housingid = ? \n");
//                params.add(importation.getHousingID());
//            }
//            if (importation.getAvgWeight() != null) {
//                sql.append("AND i.avgweight = ? \n");
//                params.add(importation.getAvgWeight());
//            }
//            if (importation.getQuanity() != null) {
//                sql.append("AND i.quanity = ? \n");
//                params.add(importation.getQuanity());
//            }
//        }

        return jdbcTemplate.query(sql.toString(), params.toArray(), new BeanPropertyRowMapper<>(ImportResponseModel.class));
    }
}

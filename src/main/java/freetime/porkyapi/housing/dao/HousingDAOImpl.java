package freetime.porkyapi.housing.dao;

import freetime.porkyapi.consts.SQLAssistant;
import freetime.porkyapi.housing.model.HousingEntity;
import freetime.porkyapi.housing.model.HousingResponseModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class HousingDAOImpl implements HousingDAO {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<HousingResponseModel> getHousingWithFarmName() {
        String query = "SELECT h.housingid, h.housingname, h.stallquanity, f.farmname \n" +
                "FROM housing h\n" +
                "LEFT JOIN farm f ON h.farmid = f.farmid;\n";

        return jdbcTemplate.query(query, new BeanPropertyRowMapper<>(HousingResponseModel.class));
    }

    @Override
    public List<HousingResponseModel> getHousingWithFarmName(HousingEntity housing) {
        StringBuilder sql = new StringBuilder(
                "SELECT h.housingid, h.housingname, h.stallquanity, f.farmname \n" +
                        "FROM housing h \n" +
                        "LEFT JOIN farm f ON h.farmid = f.farmid \n" +
                        "WHERE 1=1 \n");

        List<Object> params = new ArrayList<>();
        if (housing != null) {
            if (housing.getHousingName() != null && !housing.getHousingName().isEmpty()) {
                sql.append("AND LOWER(housingname) LIKE LOWER(?) \n");
                params.add(SQLAssistant.likeAll(housing.getHousingName()));
            }
            if (housing.getStallQuanity() != null) {
                sql.append("AND h.stallquanity = ? \n");
                params.add(housing.getStallQuanity());
            }
            if (housing.getFarmID() != null) {
                sql.append("AND f.farmid = ? \n");
                params.add(housing.getFarmID());
            }
        }

        return jdbcTemplate.query(sql.toString(), params.toArray(), new BeanPropertyRowMapper<>(HousingResponseModel.class));
    }
}

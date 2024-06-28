package freetime.porkyapi.housing.dao;

import freetime.porkyapi.housing.model.HousingEntity;
import freetime.porkyapi.housing.model.HousingResponseModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

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
}

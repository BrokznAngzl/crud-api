package freetime.porkyapi.controller.farm.dao;

import freetime.porkyapi.model.FarmEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class FarmDAOImpl implements FarmDAO {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<FarmEntity> findFarm(FarmEntity farm) {
        StringBuilder sql = new StringBuilder("SELECT * FROM farm WHERE 1=1");

        List<Object> params = new ArrayList<>();
        if (farm != null) {
            if (farm.getFarmName() != null && !farm.getFarmName().isEmpty()) {
                sql.append(" AND farmname = ?");
                params.add(farm.getFarmName());
            }
            if (farm.getLocation() != null && !farm.getLocation().isEmpty()) {
                sql.append(" AND location = ?");
                params.add(farm.getLocation());
            }
        }

        return jdbcTemplate.query(sql.toString(), params.toArray(), new BeanPropertyRowMapper<>(FarmEntity.class));
    }
}

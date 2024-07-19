package freetime.porkyapi.farm.dao;

import freetime.porkyapi.consts.SQLAssistant;
import freetime.porkyapi.farm.model.FarmEntity;
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
                sql.append(" AND LOWER(farmname) LIKE LOWER(?)");
                params.add(SQLAssistant.likeAll(farm.getFarmName()));
            }
            if (farm.getLocation() != null && !farm.getLocation().isEmpty()) {
                sql.append(" AND LOWER(location) LIKE LOWER(?)");
                params.add(SQLAssistant.likeAll(farm.getLocation()));
            }
        }

        return jdbcTemplate.query(sql.toString(), params.toArray(), new BeanPropertyRowMapper<>(FarmEntity.class));
    }
}
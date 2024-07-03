package freetime.porkyapi.death.dao;

import freetime.porkyapi.death.model.DeathRequestModel;
import freetime.porkyapi.death.model.DeathResponseModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.BigInteger;
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
                        "WHERE 1=1\n"
        );

        List<Object> params = new ArrayList<>();
        if (death != null) {
            if ((death.getStartDate() != null && !death.getStartDate().isEmpty()) &&
                    (death.getEndDate() != null && !death.getEndDate().isEmpty())) {
                sql.append("AND d.date BETWEEN ? AND ? \n");
                params.add(death.getStartDate());
                params.add(death.getEndDate());
            } else if (death.getStartDate() != null && !death.getStartDate().isEmpty()) {
                sql.append("AND d.date >= ? \n");
                params.add(death.getStartDate());
            } else if (death.getEndDate() != null && !death.getEndDate().isEmpty()) {
                sql.append("AND d.date <= ? \n");
                params.add(death.getEndDate());
            }

            if (death.getCause() != null) {
                sql.append("AND c.causeid = ? \n");
                params.add(death.getCause());
            }
            if (death.getImportCode() != null) {
                sql.append("AND i.importid = ? \n");
                params.add(death.getImportCode());
            }
            if (death.getQuantity() != null) {
                sql.append("AND d.quantity = ? \n");
                params.add(death.getQuantity());
            }
        }

        return jdbcTemplate.query(sql.toString(), params.toArray(), new BeanPropertyRowMapper<>(DeathResponseModel.class));
    }

    @Override
    public BigDecimal getDeathSum(BigInteger importID){
        String sql =
                "SELECT SUM(d.quantity)\n" +
                "FROM import i\n" +
                "LEFT JOIN death d on i.importid = d.importid\n" +
                "WHERE i.importid = ?\n" +
                "GROUP BY d.importid";
        BigDecimal result = jdbcTemplate.queryForObject(sql, BigDecimal.class, importID);
        return (result != null) ? result : new BigDecimal(0);
    }

}

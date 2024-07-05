package freetime.porkyapi.deathreport.dao;

import freetime.porkyapi.deathreport.model.DeathRptRequestModel;
import freetime.porkyapi.deathreport.model.DeathRptResponseModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DeathRptDAOImpl implements DeathRptDAO {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<?> findDeathReport(DeathRptRequestModel requestModel) {
        StringBuilder sql = new StringBuilder(
                "SELECT i.importcode, i.date, i.quanity AS importQuantity, SUM(d.quantity) AS totalDeath,\n" +
                        "    CONCAT(CAST(ROUND((SUM(d.quantity) / i.quanity) * 100, 2) AS VARCHAR), '%') AS percentage\n" +
                        "FROM death d\n" +
                        "LEFT JOIN import i on i.importid = d.importid\n" +
                        "WHERE 1=1\n"
        );
        String endLine = "GROUP BY i.importid";
        List<Object> params = new ArrayList<>();
        if (requestModel != null) {
            if ((requestModel.getStartDate() != null && !requestModel.getStartDate().isEmpty()) &&
                    (requestModel.getEndDate() != null && !requestModel.getEndDate().isEmpty())) {
                sql.append("AND i.date BETWEEN ? AND ? \n");
                params.add(requestModel.getStartDate());
                params.add(requestModel.getEndDate());
            } else if (requestModel.getStartDate() != null && !requestModel.getStartDate().isEmpty()) {
                sql.append("AND i.date >= ? \n");
                params.add(requestModel.getStartDate());
            } else if (requestModel.getEndDate() != null && !requestModel.getEndDate().isEmpty()) {
                sql.append("AND i.date <= ? \n");
                params.add(requestModel.getEndDate());
            }
            if (requestModel.getImportCode() != null) {
                sql.append("AND i.importid = ? \n");
                params.add(requestModel.getImportCode());
            }
        }
        sql.append(endLine);

        return jdbcTemplate.query(sql.toString(), params.toArray(), new BeanPropertyRowMapper<>(DeathRptResponseModel.class));
    }

}

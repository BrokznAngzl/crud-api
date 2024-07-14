package freetime.porkyapi.report.adgreport.dao;

import freetime.porkyapi.report.adgreport.model.ADGRptRequestModel;
import freetime.porkyapi.report.adgreport.model.ADGRptResponseModel;
import freetime.porkyapi.report.deathreport.model.DeathRptResponseModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ADGRptDAOImpl implements ADGRptDAO {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<?> findAverageDailyGrain(ADGRptRequestModel requestModel) {
        StringBuilder sql = new StringBuilder(
                "SELECT e.exportcode, e.avgweight-i.avgweight AS \"grain weight\",\n" +
                        "       EXTRACT(DAY FROM AGE(CAST(e.date AS timestamp), CAST(i.date AS timestamp))) AS \"day\",\n" +
                        "       ROUND( e.avgweight-i.avgweight / EXTRACT(DAY FROM AGE(CAST(e.date AS timestamp), CAST(i.date AS timestamp))), 2)\n" +
                        "        AS \"adg rate\"\n" +
                        "FROM export e\n" +
                        "         LEFT JOIN import i on i.importid = e.importid \n" +
                        "WHERE 1=1 \n"
        );
        List<Object> params = new ArrayList<>();
        if (requestModel != null) {
            if (requestModel.getExportID() != null) {
                sql.append("AND e.exportid = ? \n");
                params.add(requestModel.getExportID());
            }
        }

        return jdbcTemplate.query(sql.toString(), params.toArray(), new BeanPropertyRowMapper<>(ADGRptResponseModel.class));
    }

}

package freetime.porkyapi.export.dao;


import freetime.porkyapi.export.model.ExportRequestModel;
import freetime.porkyapi.export.model.ExportResponseModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

@Service
public class ExportDAOImpl implements ExportDAO {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<?> getAllExport(ExportRequestModel export) {
        StringBuilder sql = new StringBuilder(
                "SELECT e.exportid, e.exportcode, i.importcode, e.date, e.avgweight, e.quantity,\n" +
                        "       c.customername\n" +
                        "FROM export e\n" +
                        "         LEFT JOIN import i on i.importid = e.importid\n" +
                        "         LEFT JOIN customer c on c.customerid = e.costomerid\n" +
                        "WHERE 1=1"
        );

        List<Object> params = new ArrayList<>();
//        if (death != null) {
//            if ((death.getStartDate() != null && !death.getStartDate().isEmpty()) &&
//                    (death.getEndDate() != null && !death.getEndDate().isEmpty())) {
//                sql.append("AND d.date BETWEEN ? AND ? \n");
//                params.add(death.getStartDate());
//                params.add(death.getEndDate());
//            } else if (death.getStartDate() != null && !death.getStartDate().isEmpty()) {
//                sql.append("AND d.date >= ? \n");
//                params.add(death.getStartDate());
//            } else if (death.getEndDate() != null && !death.getEndDate().isEmpty()) {
//                sql.append("AND d.date <= ? \n");
//                params.add(death.getEndDate());
//            }
//
//            if (death.getCause() != null) {
//                sql.append("AND c.causeid = ? \n");
//                params.add(death.getCause());
//            }
//            if (death.getImportCode() != null) {
//                sql.append("AND i.importid = ? \n");
//                params.add(death.getImportCode());
//            }
//            if (death.getQuantity() != null) {
//                sql.append("AND d.quantity = ? \n");
//                params.add(death.getQuantity());
//            }
//        }

        return jdbcTemplate.query(sql.toString(), params.toArray(), new BeanPropertyRowMapper<>(ExportResponseModel.class));
    }

    @Override
    public BigDecimal getExportSum(BigInteger exportID){
        String sql =
                "SELECT SUM(d.quantity)\n" +
                "FROM import i\n" +
                "LEFT JOIN death d on i.importid = d.importid\n" +
                "WHERE i.importid = ?\n" +
                "GROUP BY d.importid";

        BigDecimal result = jdbcTemplate.queryForObject(sql, BigDecimal.class, exportID);
        return (result != null) ? result : new BigDecimal(0);
    }

}

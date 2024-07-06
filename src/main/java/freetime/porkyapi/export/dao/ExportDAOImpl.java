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
                        "WHERE 1=1\n"
        );

        List<Object> params = new ArrayList<>();
        if (export != null) {
            if ((export.getStartDate() != null && !export.getStartDate().isEmpty()) &&
                    (export.getEndDate() != null && !export.getEndDate().isEmpty())) {
                sql.append("AND e.date BETWEEN ? AND ? \n");
                params.add(export.getStartDate());
                params.add(export.getEndDate());
            } else if (export.getStartDate() != null && !export.getStartDate().isEmpty()) {
                sql.append("AND e.date >= ? \n");
                params.add(export.getStartDate());
            } else if (export.getEndDate() != null && !export.getEndDate().isEmpty()) {
                sql.append("AND e.date <= ? \n");
                params.add(export.getEndDate());
            }
            if (export.getExportCode() != null && !export.getExportCode().isEmpty()) {
                sql.append("AND e.exportcode = ? \n");
                params.add(export.getExportCode());
            }
            if (export.getQuantity() != null) {
                sql.append("AND e.quantity = ? \n");
                params.add(export.getQuantity());
            }
            if (export.getImportID() != null) {
                sql.append("AND i.importid = ? \n");
                params.add(export.getImportID());
            }
            if (export.getCustomerID() != null) {
                sql.append("AND e.costomerid =  ? \n");
                params.add(export.getCustomerID());
            }
        }

        return jdbcTemplate.query(sql.toString(), params.toArray(), new BeanPropertyRowMapper<>(ExportResponseModel.class));
    }

    @Override
    public BigDecimal getExportSum(BigInteger exportID){
        String sql =
                "SELECT SUM(d.quantity)\n" +
                "FROM import i\n" +
                "LEFT JOIN export d on i.importid = d.importid\n" +
                "WHERE i.importid = ?\n" +
                "GROUP BY d.importid";

        BigDecimal result = jdbcTemplate.queryForObject(sql, BigDecimal.class, exportID);
        return (result != null) ? result : new BigDecimal(0);
    }

}

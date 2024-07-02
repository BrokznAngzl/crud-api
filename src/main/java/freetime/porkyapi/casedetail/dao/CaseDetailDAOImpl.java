package freetime.porkyapi.casedetail.dao;

import freetime.porkyapi.casedetail.model.CaseDetailEntity;
import freetime.porkyapi.consts.SQLAssistant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CaseDetailDAOImpl implements CaseDetailDAO {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<?> getCaseDetailWhere(CaseDetailEntity caseDetail) {
        StringBuilder sql = new StringBuilder(
                "SELECT c.causeid, c.cause \n" +
                        "FROM casecause c \n" +
                        "WHERE 1=1 \n");

        List<Object> params = new ArrayList<>();
        if (caseDetail != null) {
            if (caseDetail.getCause() != null && !caseDetail.getCause().isEmpty()) {
                sql.append("AND LOWER(c.cause) LIKE LOWER(?) \n");
                params.add(SQLAssistant.likeAll(caseDetail.getCause()));
            }
        }
        return jdbcTemplate.query(sql.toString(), params.toArray(), new BeanPropertyRowMapper<>(CaseDetailEntity.class));
    }
}

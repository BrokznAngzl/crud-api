package freetime.porkyapi.customer.dao;

import freetime.porkyapi.consts.SQLAssistant;
import freetime.porkyapi.customer.model.CustomerEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CustomerDAOImpl implements CustomerDAO {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<?> findCustomer(CustomerEntity customer) {
        StringBuilder sql = new StringBuilder(
                        "SELECT * \n" +
                        "FROM customer \n" +
                        "WHERE 1=1 \n");

        List<Object> params = new ArrayList<>();
        if (customer != null) {
            if (customer.getCustomerName() != null && !customer.getCustomerName().isEmpty()) {
                sql.append("AND LOWER(customername) LIKE LOWER(?) \n");
                params.add(SQLAssistant.likeAll(customer.getCustomerName()));
            }
            if (customer.getEmail() != null && !customer.getEmail().isEmpty()) {
                sql.append("AND LOWER(email) LIKE LOWER(?) \n");
                params.add(SQLAssistant.likeAll(customer.getEmail()));
            }
            if (customer.getPhone() != null && !customer.getPhone().isEmpty()) {
                sql.append(" AND LOWER(phone) LIKE LOWER(?) \n");
                params.add(SQLAssistant.likeAll(customer.getPhone()));
            }
        }

        return jdbcTemplate.query(sql.toString(), params.toArray(), new BeanPropertyRowMapper<>(CustomerEntity.class));
    }
}

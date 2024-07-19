package freetime.porkyapi.breeds.dao;

import freetime.porkyapi.breeds.model.BreedsEntity;
import freetime.porkyapi.consts.SQLAssistant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class BreedsDAOImpl implements BreedsDAO {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<?> findBreeds(BreedsEntity breeds) {
        StringBuilder sql = new StringBuilder("SELECT * FROM breeds WHERE 1=1");

        List<Object> params = new ArrayList<>();
        if (breeds != null) {
            if (breeds.getBreedsName() != null && !breeds.getBreedsName().isEmpty()) {
                sql.append(" AND LOWER(breedsname) LIKE LOWER(?)");
                params.add(SQLAssistant.likeAll(breeds.getBreedsName()));
            }
        }

        return jdbcTemplate.query(sql.toString(), params.toArray(), new BeanPropertyRowMapper<>(BreedsEntity.class));
    }
}
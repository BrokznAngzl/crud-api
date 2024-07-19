package freetime.porkyapi.customer.dao;

import freetime.porkyapi.customer.model.CustomerEntity;
import freetime.porkyapi.housing.model.HousingEntity;

import java.util.List;

public interface CustomerDAO {
    public List<?> findCustomer(CustomerEntity customer);
}

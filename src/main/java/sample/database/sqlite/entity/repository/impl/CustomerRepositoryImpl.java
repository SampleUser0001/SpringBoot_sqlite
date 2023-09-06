package sample.database.sqlite.entity.repository.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.DataClassRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import sample.database.sqlite.entity.Customer;
import sample.database.sqlite.entity.repository.CustomerRepository;

@Repository
public class CustomerRepositoryImpl implements CustomerRepository {

    private final Logger logger = LoggerFactory.getLogger(CustomerRepositoryImpl.class);
    
    public static final String TABLE_NAME = "username";

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<Customer> findByLastName(String lastName) {
        String sql = "select * from " + TABLE_NAME + " where last_name = ?";
        List<Customer> returnList = jdbcTemplate.query(sql, new DataClassRowMapper(Customer.class), lastName);
        return returnList;
    }

    @Override
    public Customer findById(long id) {
        String sql = "select * from " + TABLE_NAME + " where id = ?";
        Customer model = jdbcTemplate.queryForObject(sql, new DataClassRowMapper<>(Customer.class), id);
        return model;
    }

    @Override
    public void save(Customer customer) {
        String sql = "insert into " + TABLE_NAME + " (first_name, last_name) values (?, ?)";
        logger.info("insert into Customer : {}", customer);
        jdbcTemplate.update(sql, customer.getFirstName(), customer.getLastName());
    }

    @Override
    public List<Customer> findAll() {
        String sql = "select * from " + TABLE_NAME +" ";
        List<Customer> returnList = jdbcTemplate.query(sql, new DataClassRowMapper(Customer.class));
        return returnList;
    }
    
}

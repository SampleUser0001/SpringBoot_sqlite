package sample.database.sqlite.entity.repository;

import java.util.List;

import sample.database.sqlite.entity.Customer;

public interface CustomerRepository {

    List<Customer> findByLastName(String lastName);
    List<Customer> findAll();

    Customer findById(long id);

    void save(Customer customer);
}
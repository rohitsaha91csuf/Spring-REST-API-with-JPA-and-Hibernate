package org.example.repository;

import org.example.entity.Employee;

import java.util.List;

public interface EmployeeRepository {

    List<Employee> findAll();
    Employee findOne(String id);
    Employee findByEmail(String email);
    Employee create(Employee emp);
    void delete(Employee emp);
    Employee update(String id,Employee employee);

}

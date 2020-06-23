package org.example.service;

import org.example.entity.Employee;
import org.example.exception.EmployeeNotFoundException;
import org.example.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.HttpClientErrorException;

import java.util.ArrayList;
import java.util.List;

@Service
public class EmployeeServiceImpl implements serviceEmployee {
    @Autowired
    private EmployeeRepository repo;

    @Override
    public List<Employee> findAll() {
        return repo.findAll();
    }

    @Override
    public Employee findOne(String id) {
        Employee employee= repo.findOne(id);
        if(employee==null)
            throw new EmployeeNotFoundException("The employee "+id+" isNOTFOUND");
        return employee;
    }

    @Override
    @Transactional
    public Employee create(Employee emp) {
        Employee existing= repo.findOne(emp.getEmail());
        if(existing!=null)
            throw new EmployeeNotFoundException("Employee with email"+emp.getEmail()+"already Exists");

        return repo.create(emp);

    }

    @Override
    @Transactional
    public void delete(String id) {
        Employee existing =repo.findOne(id);
        if(existing==null)
            throw new EmployeeNotFoundException("Employee "+id+" NOT FOUND");

        repo.delete(existing);
    }

    @Override
    @Transactional
    public Employee update(String id, Employee employee) {
        Employee existing =repo.findOne(id);
        if(existing==null)
            throw new EmployeeNotFoundException("Employee "+id+" NOT FOUND");
        return repo.update(id,employee);
        }
}

package org.example.repository;

import org.example.entity.Employee;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;

@Repository
public class EmployeeRepositoryImpl implements EmployeeRepository{

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Employee> findAll() {
        List<Employee> employee = new ArrayList<>();
        TypedQuery<Employee> query = entityManager.createNamedQuery("Employee.findAll", Employee.class);
        List<Employee> result= query.getResultList();

        return result;
    }

    @Override
    public Employee findOne(String id) {
        return entityManager.find(Employee.class,id);
    }

    @Override
    public Employee findByEmail(String email) {
        TypedQuery<Employee> q1= entityManager.createNamedQuery("Employee.findByEmail",Employee.class);
        q1.setParameter("paramEmail",email);
        List<Employee> list = q1.getResultList();
        if(list!=null && list.size()==1)
            return list.get(0);
        else
            return null;
    }

    @Override
    public Employee create(Employee emp) {

        entityManager.persist(emp);

        return emp;
    }

    @Override
    public void delete(Employee emp) {

        entityManager.remove(emp);
    }

    @Override
    public Employee update(String id, Employee employee) {
       entityManager.merge(employee);
        return employee;
    }
}

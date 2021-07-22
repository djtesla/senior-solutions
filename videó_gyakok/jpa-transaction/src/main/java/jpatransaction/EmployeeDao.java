package jpatransaction;

import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

@Repository
public class EmployeeDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public void saveEmployee(Employee employee) {
        entityManager.persist(employee);
        if (employee.getName().length()< 3) {
            throw new IllegalArgumentException("Name must be longer");
        }
    }

    public Employee findEmployeeById(long id) {
        return entityManager.find(Employee.class, id);
    }

}

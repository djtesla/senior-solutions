package employees;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

public class EmployeesMain {

    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("pu");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        Employee employee = new Employee("John Doe");


        entityManager.persist(employee);
        entityManager.persist(new Employee("Jimmy Doe"));
        entityManager.getTransaction().commit();

        long id = employee.getId();
        employee = entityManager.find(Employee.class, id);
        System.out.println(employee);


        entityManager.getTransaction().begin();
        Employee anotherEmployee = entityManager.find(Employee.class, id);
        anotherEmployee.setName("Jane Doe");
        Employee employeeToBeDeleted = entityManager.find(Employee.class, id);
        entityManager.remove(employeeToBeDeleted);
        entityManager.getTransaction().commit();

        List<Employee> employees = entityManager.createQuery("select e from Employee e", Employee.class).getResultList();
        System.out.println(employees);


        entityManager.close();
        entityManagerFactory.close();




    }
}

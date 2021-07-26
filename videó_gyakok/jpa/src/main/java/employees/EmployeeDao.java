package employees;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class EmployeeDao {

    private EntityManagerFactory entityManagerFactory;

    public EmployeeDao(EntityManagerFactory entityManagerFactory) {
        this.entityManagerFactory = entityManagerFactory;
    }

    public void save(Employee employee) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.persist(employee);
        entityManager.getTransaction().commit();
        entityManager.close();

    }

    public Employee findById(long id) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        Employee employee = entityManager.find(Employee.class, id);
        entityManager.close();
        return employee;
    }

    public List<Employee> listAll() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        List<Employee> employees = entityManager.createQuery("select e from Employee e order by e.name", Employee.class).getResultList();
        entityManager.close();
        return  employees;
    }

    public void updateEmployeeName(long id, String name) { //ez a jó gyakorlat; nem a merge; betöltöm és explicit megadom, mit akarok beállítani
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        Employee employee = entityManager.find(Employee.class, id);
        employee.setName(name);
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    public void delete(long id) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        Employee employee = entityManager.find(Employee.class, id);
        entityManager.remove(employee);
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    public void updateEmployee(Employee employee) { // nem jó gyak; az employeen lévő össze változás felülírja majd az eredeti objektumot
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        Employee merged = entityManager.merge(employee); // új entitást ad vissza!
        merged.setName(merged.getName() + " ***");
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    public void updateEmployeeNames() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        List<Employee> employees = entityManager.createQuery("select e from Employee e order by e.name").getResultList();
        entityManager.getTransaction().begin();
        for (Employee employee :employees) {
            employee.setName(employee.getName()+"***");
            System.out.println("Módosítva");
            entityManager.flush();
        }
        entityManager.getTransaction().commit();
    }

    public Employee findEmployeeByIdWithNickNames(long id) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        return entityManager.createQuery("select e from Employee e join fetch e.nickNames where e.id =:id",Employee.class )
                .setParameter("id", id).getSingleResult();

    }

    public Employee findEmployeeByIdWithVacations(long id){
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        return entityManager.createQuery("select e from Employee e join fetch e.vacationBookings where e.id=:id",
                Employee.class).setParameter("id", id).getSingleResult();
    }

    public Employee findEmployeeByIdWithPhoneNumbers(long id){
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        return entityManager.createQuery("select e from Employee e join fetch e.phoneNumbers where e.id=:id",
                Employee.class).setParameter("id", id).getSingleResult();
    }



    public void addPhoneNumber(long id, PhoneNumber phoneNumber) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        //Employee employee = entityManager.find(Employee.class, id);
        Employee employee = entityManager.getReference(Employee.class, id);
        phoneNumber.setEmployee(employee);
        entityManager.persist(phoneNumber);
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    public Employee findEmployeeByName(String name) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Employee> c = cb.createQuery(Employee.class);
        Root<Employee> emp = c.from(Employee.class);
        c.select(emp).where(cb.equal(emp.get("name"), "John Doe"));
        Employee employee = entityManager.createQuery(c).getSingleResult();
        entityManager.close();
        return employee;
    }

    public List<Employee>listEmployees(int start, int maxResult) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        List<Employee> employees = entityManager.createQuery("select e from Employee e order by e.id", Employee.class).setFirstResult(start).setMaxResults(maxResult).getResultList();
        entityManager.close();;
        return employees;
    }

    public List<Employee>listEmployeesWithNamedQuery() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        List<Employee> employees = entityManager.createNamedQuery("listEmployees", Employee.class).getResultList();
        entityManager.close();
        return employees;
    }

    public int findParkingPlaceNumberByEmployeeName(String name) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        int number =entityManager.createQuery("select p.number from Employee e join e.parkingPlace p where e.name=:name", Integer.class)
                .setParameter("name", name).getSingleResult();
        entityManager.close();
        return number;
    }

    public List<Object[]> listEmployeeBaseData(){
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        List<Object[]> data = entityManager.createQuery("select e.id, e.name from Employee e").getResultList();
        entityManager.close();
        return data;
    }

    public List<EmpBaseDataDto> listEmployeeDto() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        List<EmpBaseDataDto> employeeDtoList =entityManager.createQuery("select new employees.EmpBaseDataDto(e.id, e.name) from Employee e order by e.name").getResultList();
        entityManager.close();
        return  employeeDtoList;
    }
}




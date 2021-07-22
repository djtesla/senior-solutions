package employees;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;


@Entity
@NamedQuery(name = "listEmployees", query = "select e from Employee e order by e.id")
@Table(name = "employees")
public class Employee {
    public enum EmployeeType {
        FULL_TIME, HALF_TIME
    }

    ;


    @Column(name = "emp_name", length = 200, nullable = false)
    private String name;
    //@TableGenerator(name = "Emp_Gen", table = "emp_id_gen", pkColumnName = "gen_name", valueColumnName = "gen_val", initialValue = 54)
    //private String depName;
    @Id
    @Column(name = "emp_id")
    //@GeneratedValue(generator = "Emp_Gen")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Enumerated(EnumType.STRING)
    private EmployeeType type = EmployeeType.FULL_TIME;

    private LocalDate dateOfBirth;


    @ElementCollection
    @CollectionTable(name = "nicknames", joinColumns = @JoinColumn(name = "emp_id"))
    @Column(name = "nickname")
    private Set<String> nickNames;


    @ElementCollection
    @CollectionTable(name = "bookings", joinColumns = @JoinColumn(name = "emp_id"))
    @AttributeOverride(name = "dateOfVacation", column = @Column(name = "start_date"))
    @AttributeOverride(name = "days", column = @Column(name = "days_taken"))
    private Set<VacationEntry> vacationBookings;


   /* @ElementCollection
    @CollectionTable(name = "phone_numbers", joinColumns = @JoinColumn(name = "emp_id"))
    @MapKeyColumn(name ="phone_type")
    @Column(name ="phone_number")
    private Map<String, String> phoneNumbers;*/


    @OneToOne
    private ParkingPlace parkingPlace;

    @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.REMOVE}, mappedBy = "employee")
    @OrderBy("type")
    @OrderColumn(name = "pos")
    private List<PhoneNumber> phoneNumbers;

    public Employee() {
    }

    public Employee(String name) {
        this.name = name;
    }

    public Employee(String name, EmployeeType type, LocalDate dateOfBirth) {
        this.name = name;
        this.type = type;
        this.dateOfBirth = dateOfBirth;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public EmployeeType getType() {
        return type;
    }

    public void setType(EmployeeType type) {
        this.type = type;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }


    public Set<String> getNickNames() {
        return nickNames;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setNickNames(Set<String> nickNames) {
        this.nickNames = nickNames;
    }

    public Set<VacationEntry> getVacationBookings() {
        return vacationBookings;
    }

    public void setVacationBookings(Set<VacationEntry> vacationBookings) {
        this.vacationBookings = vacationBookings;
    }

    public List<PhoneNumber> getPhoneNumbers() {
        return phoneNumbers;
    }

    public void setPhoneNumbers(List<PhoneNumber> phoneNumbers) {
        this.phoneNumbers = phoneNumbers;
    }

    public ParkingPlace getParkingPlace() {
        return parkingPlace;
    }

    public void setParkingPlace(ParkingPlace parkingPlace) {
        this.parkingPlace = parkingPlace;
    }

    public void addPhoneNumber(PhoneNumber phoneNumber) {
        if (phoneNumbers == null) {
            phoneNumbers = new ArrayList<>();
        }
        phoneNumbers.add(phoneNumber);
        phoneNumber.setEmployee(this);
    }

    @Override
    public String toString() {
        return "Employee{" +
                "name='" + name + '\'' +
                ", id=" + id +
                '}';
    }

    @PostPersist
    void debugPersist() {
        System.out.println(name + ", " + id);
    }
}

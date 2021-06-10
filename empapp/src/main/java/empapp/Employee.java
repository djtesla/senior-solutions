package empapp;

import java.util.Objects;

public class Employee {

    private  String name;
    private int yearOfBirth;

    public Employee(String name, int yearOfBirth) {
        if (yearOfBirth < 1940) {
            throw new IllegalArgumentException("Guy is too old.");
        }
        this.name = name;
        this.yearOfBirth = yearOfBirth;
    }

    public String getName() {
        return name;
    }

    public int getYearOfBirth() {
        return yearOfBirth;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setYearOfBirth(int yearOfBirth) {
        this.yearOfBirth = yearOfBirth;
    }

    public int getAge(int atYear) {
        return atYear-yearOfBirth;

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Employee)) return false;
        Employee employee = (Employee) o;
        return yearOfBirth == employee.yearOfBirth && Objects.equals(name, employee.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, yearOfBirth);
    }
}

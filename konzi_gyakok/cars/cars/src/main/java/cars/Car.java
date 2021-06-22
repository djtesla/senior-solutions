package cars;

import java.util.List;

public class Car {
    private String brand;
    private String type;
    private int age;
    private State state;
    private List<KmState> kmStates;

    public Car(String brand, String type, int age, State state, List<KmState> kmStates) {
        this.brand = brand;
        this.type = type;
        this.age = age;
        this.state = state;
        this.kmStates = kmStates;
    }

    public String getBrand() {
        return brand;
    }

    public String getType() {
        return type;
    }

    public int getAge() {
        return age;
    }

    public State getState() {
        return state;
    }

    public List<KmState> getKmStates() {
        return kmStates;
    }
}

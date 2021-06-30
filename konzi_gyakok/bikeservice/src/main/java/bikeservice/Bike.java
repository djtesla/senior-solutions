package bikeservice;

import java.time.LocalDateTime;

public class Bike {
    private String id;
    private String lastUserId;
    private LocalDateTime timeOfLastDeposit;
    private double distanceOfLasRide;

    public Bike(String id, String lastUserId, LocalDateTime timeOfLastDeposit, double distanceOfLasRide) {
        this.id = id;
        this.lastUserId = lastUserId;
        this.timeOfLastDeposit = timeOfLastDeposit;
        this.distanceOfLasRide = distanceOfLasRide;
    }

    public String getId() {
        return id;
    }

    public String getLastUserId() {
        return lastUserId;
    }

    public LocalDateTime getTimeOfLastDeposit() {
        return timeOfLastDeposit;
    }

    public double getDistanceOfLasRide() {
        return distanceOfLasRide;
    }
}




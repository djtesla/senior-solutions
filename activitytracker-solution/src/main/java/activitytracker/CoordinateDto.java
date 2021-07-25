package activitytracker;

public class CoordinateDto {

    private double lat;
    private double lon;

    public double getLon() {
        return lon;
    }

    public void setLon(double lon) {
        this.lon = lon;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public CoordinateDto() {
    }

    public CoordinateDto(double lat, double lon) {
        this.lon = lon;
        this.lat = lat;
    }

    @Override
    public String toString() {
        return "CoordinateDto{" +
                "lat=" + lat +
                ", lon=" + lon +
                '}';
    }
}

package locations;

import java.util.Objects;

public class Location {

    private String name;
    private double lat;
    private double lon;

    public Location(String name, double lat, double lon) {

        if (isNotValidLat(lat) || isNotValidLon(lon)) {
            throw new IllegalArgumentException("Wrong parameter: latitude must be between -90 and 90, longitude must be between -180 and 180");
        }
        this.name = name;
        this.lat = lat;
        this.lon = lon;
    }

    private boolean isNotValidLon(double lon) {
        return lon > 180 || lon < -180;
    }

    private boolean isNotValidLat(double lat) {
        return lat > 90 || lat < -90;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLon() {
        return lon;
    }

    public void setLon(double lon) {
        this.lon = lon;
    }

    public boolean isOnEquator(){
        return lon == 0;
    }

    public boolean isOnPrimeMeridian(){
        return lat == 0;
    }

    public double distanceFrom(Location another) {

        double lat1 = lat;
        double lat2 =another.getLat();
        double lon1 =lon;
        double lon2 =another.getLon();
        final int R = 6371; // Radius of the earth

        double latDistance = Math.toRadians(lat2 - lat1);
        double lonDistance = Math.toRadians(lon2 - lon1);
        double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
                + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2))
                * Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        double distance = R * c * 1000; // convert to meters

        double height = 0;

        distance = Math.pow(distance, 2) + Math.pow(height, 2);

        return Math.sqrt(distance);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Location)) return false;
        Location location = (Location) o;
        return Double.compare(location.lat, lat) == 0 && Double.compare(location.lon, lon) == 0 && Objects.equals(name, location.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, lat, lon);
    }

    @Override
    public String toString() {
        return "Location{" +
                "name='" + name + '\'' +
                ", lat=" + lat +
                ", lon=" + lon +
                '}';
    }
}


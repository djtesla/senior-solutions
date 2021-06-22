package locations;

public class LocationParser {

    public Location parse(String text) {
        //Budapest,47.497912,19.040235
        try {
            String name = text.split(",")[0];
            double lat = Double.parseDouble(text.split(",")[1]);
            double lon = Double.parseDouble(text.split(",")[2]);
            return new Location(name, lat, lon);
        } catch (NumberFormatException nfe) {
            throw new IllegalArgumentException("Invalid coordinate", nfe);
        }
    }
}

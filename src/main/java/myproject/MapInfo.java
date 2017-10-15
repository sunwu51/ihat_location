package myproject;

public class MapInfo {
    private double longitude;
    private double latitude;
    private double length;
    private double width;

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLength() {
        return length;
    }

    public void setLength(double length) {
        this.length = length;
    }

    public double getWidth() {
        return width;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    @Override
    public String toString() {
        return "MapInfo [longitude=" + longitude + ", latitude=" + latitude
                + ", length=" + length + ", width=" + width + "]";
    }

    public MapInfo(double longitude, double latitude, double length,
                   double width) {
        super();
        this.longitude = longitude;
        this.latitude = latitude;
        this.length = length;
        this.width = width;
    }

    public MapInfo() {
    }
}
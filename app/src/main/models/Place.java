public class Place {
    private int id;
    private LatLng latLng;
    private String name;
    private String type;
    private DetailPlace detailPlace;

    public int getId() {
        return id;
    }

    public void setId(int newId) {
        this.id = newId;
    }

    public LatLng getLatLng() {
        return latLng;
    }

    public void setLatLngLatitude(double newLatitude) {
        this.latLng.latitude = newLatitude;
    }

    public void setLatLngLongitude(double newLongitude) {
        this.latLng.longitude = newLongitude;
    }

    public String getName() {
        return name;
    }

    public void setName(String newName) {
        this.name = newName;
    }

    public DetailPlace getDetailPlace() {
        return detailPlace;
    }

    public void setDetailPlace(DetailPlace newDetailPlace) {
        this.detailPlace = newDetailPlace;
    }
}

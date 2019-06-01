import java.util.Collection;

public interface MapView extends BaseView {
    void showMap();
    void showBButtons();
    void displaySpots(Collection<Place> places);
    void displayPosition();
}
import java.util.Collection;

public interface FilterView extends BaseView {
    void colorButton();
    void showPlaces(Collection<Place> places);
    void showWheel();
}

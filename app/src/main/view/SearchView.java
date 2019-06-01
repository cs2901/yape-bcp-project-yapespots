import java.util.Collection;

public interface SearchView extends BaseView {
    void displayResult(Collection<Place> places);
    void showButtonsOfSearch();
    void colorButton();
    void recentSearchedPlaces(Collection<Place> places);
}

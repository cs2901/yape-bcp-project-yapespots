import java.util.Collection;

public interface SearchPresenter extends BasePresenter {
    Collection<Place> applyFilter(Collection<Place> places);
    Collection<Place> applySearch(String search);
    void chageToAdvancedFilter();
    void onButtonClicked();
}

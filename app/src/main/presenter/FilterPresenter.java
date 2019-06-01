import java.util.Collection;

public interface FilterPresenter extends BasePresenter {
    Collection<Place> applyFilter(Collection<Place> places);
}

import java.util.Collection;

public interface AdvancedFIlterPresenter extends FilterPresenter {
    Collection<Place> modifyRadius(float newRadius);
    Collection<Place> applyHours(Collection<int> newHours);
    Collection<Place> resetFilter();
}

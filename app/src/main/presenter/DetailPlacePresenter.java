public interface DetailPlacePresenter extends BasePresenter {
    void getData(Place place);
    void like(Place place);
    void favorite(Place place);
    void report(Place place);
    void call(Place place);
    void goTo(Place place);
    void share(Place place);
    void moreInfo(Place place);
}

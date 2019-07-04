package com.software.yapespots.ui.search.view;
import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.software.yapespots.model.Place;

import com.software.yapespots.core.BaseView;

import java.util.ArrayList;
import java.util.Collection;

public interface SearchView extends BaseView {
    Context getBaseContext();
    void onClick(View view);
    void recentSearchedPlaces(Collection<Place> places);
    void ShowSearchResult(ArrayList<Place> places);
    String getCurrentType();
    View findView(int resID);
    void showFavorites();
    void SearchSpots(TextView textView);
}
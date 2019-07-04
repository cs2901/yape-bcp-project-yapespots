package com.software.yapespots.ui.search;

import android.arch.persistence.room.Room;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.software.yapespots.R;
import com.software.yapespots.R.layout;
import com.software.yapespots.model.Place;
import com.software.yapespots.model.Search;
import com.software.yapespots.model.local.FavoritePlace;
import com.software.yapespots.repository.firebase.functions.FirebaseFunction;
import com.software.yapespots.repository.local.LocalDatabase;
import com.software.yapespots.ui.advancedFilter.AdvancedFilterActivity;
import com.software.yapespots.ui.login.LoginActivity;
import com.software.yapespots.ui.map.MapActivity;
import com.software.yapespots.ui.search.listener.SearchPlacesListener;
import com.software.yapespots.ui.search.presenter.SearchPresenter;
import com.software.yapespots.ui.search.presenter.SearchPresenterImpl;
import com.software.yapespots.ui.search.view.SearchView;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

import static java.security.AccessController.getContext;

public class SearchActivity extends AppCompatActivity implements SearchView {
    HashMap<String, Object> jason;
    SearchPresenter presenter;
    double location[];
    LocalDatabase db;
    ImageButton btFavorite;
    boolean log;
    List<FavoritePlace> favorites;
    private FirebaseFunction firebaseInteractor = new FirebaseFunction();
    public ArrayList<Place> places = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(layout.activity_search);
        presenter = new SearchPresenterImpl(this);
        log = getIntent().getBooleanExtra("logged", false);
        places = new ArrayList<>();
        jason = new HashMap<String, Object>();
        location = new double[2];
        jason.put("region", "pe");
        EditText myTextBox = findViewById(R.id.editText2);
        TextView.OnEditorActionListener listenerText = new SearchPresenterImpl(this);
        myTextBox.setOnEditorActionListener(listenerText);

        // Favorites
        db = Room.databaseBuilder(this, LocalDatabase.class, FavoritePlace.DATABASE_NAME).allowMainThreadQueries().build();
        btFavorite = findViewById(R.id.imageButton5);
        Intent intent = getIntent();
        boolean mapa = intent.getBooleanExtra("favorite",false);
        if(mapa){
            onClick(btFavorite);
            showFavorites();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    public void onClick(View view) {
        if(view.getId() == btFavorite.getId()){
            if(!isLogged(view)){
                return;
            }
        }
        presenter.onButtonPressed(view, places);
        String type = presenter.getActualType();
        jason.put("type", type);


    }

    public boolean isLogged(View view){
        if (!log) {
            //Al precionar favoritos
            TextView aceptar;
            TextView cancelar;

            LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
            View popupView = inflater.inflate(R.layout.popup, null);
            aceptar = (TextView) popupView.findViewById(R.id.aceptar_pop);
            cancelar = (TextView) popupView.findViewById(R.id.cancelar_pop);

            int width = LinearLayout.LayoutParams.WRAP_CONTENT;
            int height = LinearLayout.LayoutParams.WRAP_CONTENT;
            boolean focusable = true;

            final PopupWindow popupWindow = new PopupWindow(popupView, width, height, focusable);

            popupWindow.showAtLocation(view, Gravity.CENTER, 0, 0);

            aceptar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intentLogin =new Intent(SearchActivity.this, LoginActivity.class);
                    startActivity(intentLogin);
                }
            });
            cancelar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    presenter.cleanFavorite(btFavorite);
                    popupWindow.dismiss();
                }
            });
            return false;
        }
        return true;
    }

    public void showFavorites(){
        favorites = db.getFavoritePlaceDao().getAll();
        ArrayList<Place> favoritePlaces = new ArrayList<>();
        for(int i =0; i<favorites.size();i++){
            favoritePlaces.add(new Place(favorites.get(i)));
        }
        ShowSearchResult(favoritePlaces);
    }

    public String getCurrentType() {
        return presenter.getActualType();
    }

    public void ShowSearchResult(ArrayList<Place> places) {
        RecyclerView rv = findViewById(R.id.recyclerView);
        rv.setNestedScrollingEnabled(false);
        rv.setLayoutManager(new LinearLayoutManager(this));
        rv.setAdapter(new PlaceAdapter(places));
    }

    public void onMoreFiltersButtonPressed(View view) {
        Intent intent = new Intent(this, AdvancedFilterActivity.class);
        startActivityForResult(intent, 1);
    }

    public void recentSearchedPlaces(Collection<Place> places) {

    }

    @Override
    public View findView(int resID) {
        return findViewById(resID);
    }

    @Override
    public void SearchSpots(TextView textView) {

        String text = textView.getText().toString();
        jason.put("query", text);
        presenter.cleanFavorite(btFavorite);
        SearchPlacesListener listener = new SearchPlacesListener(this);
        firebaseInteractor.withData(jason).searchPlaces(listener);
    }

    public boolean displayView() {
        return false;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            if (resultCode == SearchActivity.RESULT_OK) {
                String text = data.getStringExtra("radius") + data.getStringExtra("type") + data.getStringExtra("opennow");
                jason.put("radius", Integer.valueOf(data.getStringExtra("radius")));
                jason.put("type", data.getStringExtra("type"));
                jason.put("opennow", Boolean.valueOf(data.getStringExtra("opennow")));
            }
            if (resultCode == SearchActivity.RESULT_CANCELED) {
                jason.put("type", getCurrentType());
            }


        }
    }

    public void goBackMap(View view) {
        Intent intent = new Intent();
        setResult(this.RESULT_OK, intent);
        finish();
    }
}

package com.software.yapespots.ui.detailPlace;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.api.ApiException;
import com.google.android.libraries.places.api.Places;
import com.google.android.libraries.places.api.model.OpeningHours;
import com.google.android.libraries.places.api.model.PhotoMetadata;
import com.google.android.libraries.places.api.model.Place;
import com.google.android.libraries.places.api.net.FetchPhotoRequest;
import com.google.android.libraries.places.api.net.FetchPlaceRequest;
import com.google.android.libraries.places.api.net.PlacesClient;
import com.software.yapespots.R;
import com.software.yapespots.ui.detailPlace.view.DetailPlaceView;
import com.software.yapespots.ui.search.SecondListenerHolder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DetailPlaceActivity extends AppCompatActivity implements DetailPlaceView {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detailplace);

        Button buttonOpenBottomSheet = findViewById(R.id.button_open_bottom_sheet);
    }

    public void OnClick(View view) {
        DetailPlaceBottomSheetDialog bottomSheet = new DetailPlaceBottomSheetDialog();
        bottomSheet.show(getSupportFragmentManager(), "detailPlaceBottomSheet");
    }

    public void placeStatus(String placeId, View view) {

    }

    public void showPhotos() {


    }

    public void showPlaceDetail() {

    }

    public void showButtons() {

    }

    public void showError() {

    }

    public boolean displayView() {
        return false;
    }
}

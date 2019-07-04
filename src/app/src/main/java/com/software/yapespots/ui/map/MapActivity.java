package com.software.yapespots.ui.map;

import android.Manifest;
import android.arch.persistence.room.Room;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.location.LocationManager;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MapStyleOptions;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.oguzdev.circularfloatingactionmenu.library.FloatingActionButton;
import com.oguzdev.circularfloatingactionmenu.library.FloatingActionMenu;
import com.oguzdev.circularfloatingactionmenu.library.SubActionButton;
import com.software.yapespots.R;
import com.software.yapespots.model.Place;
import com.software.yapespots.model.local.FavoritePlace;
import com.software.yapespots.repository.firebase.functions.FirebaseFunction;
import com.software.yapespots.repository.local.LocalDatabase;
import com.software.yapespots.ui.detailPlace.DetailPlaceBottomSheetDialog;
import com.software.yapespots.ui.home.HomeActivity;
import com.software.yapespots.ui.login.LoginActivity;
import com.software.yapespots.ui.map.listener.FiltersListener;
import com.software.yapespots.ui.map.listener.NearbyPlacesListener;
import com.software.yapespots.ui.search.SearchActivity;
import com.software.yapespots.utils.Image;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;


public class MapActivity extends AppCompatActivity implements OnMapReadyCallback, GoogleMap.OnMarkerClickListener {

    public static final String TAG = "MapActivity";
    public static final String FINE_LOCATION = Manifest.permission.ACCESS_FINE_LOCATION;
    public static final String COURSE_LOCATION = Manifest.permission.ACCESS_COARSE_LOCATION;
    public static final int LOCATION_PERMISSION_REQUEST_CODE = 1234;
    //WIDGETS
    private ImageView mGps;
    private ImageView favo;
    private ImageView goback;
    private ImageView search;
    private ImageView filterOnMap;
    //VARIABLES
    private LocationManager lm;
    protected LatLng lastPositionOfCamera;
    protected float lastZoomOfCamara;
    Bitmap smallMarker;
    public Marker currentUserLocationMarker;
    private Marker prueba;
    public Boolean mLocationPermissionsGranted = false;
    public GoogleMap mMap;
    public FusedLocationProviderClient mFusedLocationProviderClient;
    private FirebaseFunction firebaseInteractor = new FirebaseFunction();
    public ArrayList<Place> places;
    MapPresenter presenter;
    boolean log = false;
    //Buttons
    ImageView barIconMenu;
    ImageView hotelIconMenu;
    ImageView restauranteIconMenu;
    ImageView storeIconMenu;
    ImageView floristIconMenu;
    public String tipoactual;

    public void initMap() {
        Log.d(TAG, "initMap: initializing map");
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);

        mapFragment.getMapAsync(MapActivity.this);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        log = getIntent().getBooleanExtra("logged", false);
        places = new ArrayList<>();
        setContentView(R.layout.activity_map);
        presenter = new MapPresenter(this);
        goback = findViewById(R.id.back);
        search = findViewById(R.id.search);
        mGps = findViewById(R.id.ic_gps);
        favo = findViewById(R.id.favorite);
        filterOnMap = findViewById(R.id.filters);
        presenter.getLocationPermission();
        barIconMenu = new ImageView(this);
        hotelIconMenu = new ImageView(this);
        restauranteIconMenu = new ImageView(this);
        storeIconMenu = new ImageView(this);
        floristIconMenu = new ImageView(this);
        tipoactual = new String("all");

        //List<FavoritePlace> favorites = db.getFavoritePlaceDao().loadById();
    }

    public int getRadioFromZoom(double zoom) {
        return (int) ((20 - zoom) * 541.666666666666666 - 1316.6666666666666648);
    }

    private void init() {
        Intent intent = getIntent();
        Place placeFromSearch = new Place();
        String placeID = intent.getStringExtra("placeID");
        if (placeID != null) {
            Boolean placeOpenNow = intent.getBooleanExtra("placeOpenNow", false);
            String placeName = intent.getStringExtra("placeName");
            String placeLat = intent.getStringExtra("placeLat");
            String placeLng = intent.getStringExtra("placeLng");
            ArrayList<String> placeType = intent.getStringArrayListExtra("placeType");
            placeFromSearch.setId(placeID);
            placeFromSearch.setType(placeType);
            placeFromSearch.setLat(placeLat);
            placeFromSearch.setLng(placeLng);
            placeFromSearch.setName(placeName);
            placeFromSearch.setOpennow(placeOpenNow);
            LatLng coordinate = new LatLng(Double.parseDouble(placeLat), Double.parseDouble(placeLng));
            mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(coordinate, (float) 17.2));
            Marker marker = mMap.addMarker(new MarkerOptions()
                    .position(coordinate).title(placeName));
            marker.setTag(placeFromSearch);
            DetailPlaceBottomSheetDialog bottomSheet = new DetailPlaceBottomSheetDialog();
            bottomSheet.withPlaceId(placeID, placeOpenNow, 0.0, 0.0, Double.parseDouble(placeLat), Double.parseDouble(placeLng), placeType.get(0)).show(getSupportFragmentManager(), "detailPlaceBottomSheet");
        }

        mGps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "onClick: clicked gps icon");
                if (presenter.checkIfLocationOpened()) {
                    presenter.getDeviceLocation();
                } else {
                    startActivity(new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS));
                }
            }
        });


        favo.setOnClickListener(view -> {
            Log.d(TAG, "onClick: clicked gps icon");
            if (!log) {
                Log.d(TAG, "onClick: clicked favo icon");
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
                        startActivity(new Intent(MapActivity.this, LoginActivity.class));
                    }
                });
                cancelar.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        popupWindow.dismiss();
                    }
                });
            }
            else{
                Intent intent1 = new Intent(MapActivity.this, SearchActivity.class);
                intent1.putExtra("favorite", true);
                intent1.putExtra("logged", log);
                startActivity(intent1);
            }
        });

        mMap.setOnCameraIdleListener(() -> {
            if (presenter.movetorequest(mMap.getCameraPosition().target, mMap.getCameraPosition().zoom)) {
                //aqui se debe hacer el request a la base de datos
                lastPositionOfCamera = mMap.getCameraPosition().target;
                lastZoomOfCamara = mMap.getCameraPosition().zoom;

                // Create data object
                HashMap<String, Object> location = new HashMap<>();
                location.put("latitude", lastPositionOfCamera.latitude);
                location.put("longitude", lastPositionOfCamera.longitude);
                location.put("radio", getRadioFromZoom(lastZoomOfCamara));

                NearbyPlacesListener listener = new NearbyPlacesListener(MapActivity.this, getApplicationContext(), mMap, lastPositionOfCamera);
                firebaseInteractor.withData(location).getPlaces(listener);
            }
        });

        search.setOnClickListener(v -> {
            Intent intent12 = new Intent(MapActivity.this, SearchActivity.class);
            intent12.putExtra("logged", log);
            /*intent.putExtra("latitude",currentUserLocationMarker.getPosition().latitude);
            intent.putExtra("longitude",currentUserLocationMarker.getPosition().longitude);*/
            startActivity(intent12);
        });

        goback.setOnClickListener(v -> {
            if(!log){ startActivity(new Intent(MapActivity.this,LoginActivity.class)); }
            else{
                Intent intent13 = new Intent(MapActivity.this, HomeActivity.class);
                intent13.putExtra("logged", log);
                System.out.println("valor de log: " + log);
                startActivity(intent13);
            }
        });

        FiltersListener filterListener = FiltersListener.getInstance();
        filterOnMap.setOnClickListener(filterListener);

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
        }
        LocationOnMove locationmove = new LocationOnMove(this);
        lm.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1000, 0, locationmove);

        /* FLOATING ACTION BUTTON */
        ImageView icon = new ImageView(this);
        icon.setImageDrawable(getResources().getDrawable(R.drawable.ic_more_horiz_black));
        FloatingActionButton.LayoutParams layoutParams = new FloatingActionButton.LayoutParams(120, 120);
        layoutParams.setMargins(layoutParams.leftMargin, layoutParams.topMargin, layoutParams.rightMargin + 10, layoutParams.bottomMargin);
        FloatingActionButton actionButton = new FloatingActionButton.Builder(this)
                .setContentView(icon)
                .setPosition(FloatingActionButton.POSITION_RIGHT_CENTER)
                .setLayoutParams(layoutParams)
                .build();

        // buttons
        SubActionButton.Builder itemBuilder = new SubActionButton.Builder(this);
        FloatingActionButton.LayoutParams buttonsParams = new FloatingActionButton.LayoutParams(110, 110);


        barIconMenu.setImageDrawable(this.getResources().getDrawable(R.drawable.icon_circle_bar_selector));
        SubActionButton barActionMenu = itemBuilder.setLayoutParams(buttonsParams).setContentView(barIconMenu).build();
        barIconMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearButtons(v);
                mMap.clear();
                if (v.isSelected()) {
                    tipoactual = "bar";
                    showFIlterPlaces("bar");
                } else {
                    tipoactual = "all";
                    showAll();
                }


            }
        });

        hotelIconMenu.setImageDrawable(this.getResources().getDrawable(R.drawable.icon_circle_hotel_selector));
        SubActionButton hotelActionMenu = itemBuilder.setLayoutParams(buttonsParams).setContentView(hotelIconMenu).build();
        hotelIconMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearButtons(v);
                mMap.clear();
                if (v.isSelected()) {
                    tipoactual = "lodging";
                    showFIlterPlaces("lodging");
                } else {
                    tipoactual = "all";
                    showAll();
                }
            }
        });

        restauranteIconMenu.setImageDrawable(this.getResources().getDrawable(R.drawable.icon_circle_restaurante_selector));
        SubActionButton restauranteActionMenu = itemBuilder.setLayoutParams(buttonsParams).setContentView(restauranteIconMenu).build();
        restauranteIconMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearButtons(v);
                mMap.clear();
                if (v.isSelected()) {
                    tipoactual = "restaurant";
                    showFIlterPlaces("restaurant");
                } else {
                    tipoactual = "all";
                    showAll();
                }
            }
        });

        storeIconMenu.setImageDrawable(this.getResources().getDrawable(R.drawable.icon_circle_tienda_selector));
        SubActionButton storeActionMenu = itemBuilder.setLayoutParams(buttonsParams).setContentView(storeIconMenu).build();
        storeIconMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearButtons(v);
                mMap.clear();
                if (v.isSelected()) {
                    tipoactual = "store";
                    showFIlterPlaces("store");
                } else {
                    tipoactual = "all";
                    showAll();
                }
            }
        });

        floristIconMenu.setImageDrawable(this.getResources().getDrawable(R.drawable.icon_circle_floreria_selector));
        SubActionButton floristActionMenu = itemBuilder.setLayoutParams(buttonsParams).setContentView(floristIconMenu).build();
        floristIconMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearButtons(v);
                mMap.clear();
                if (v.isSelected()) {
                    tipoactual = "florist";
                    showFIlterPlaces("florist");
                } else {
                    tipoactual = "all";
                    showAll();
                }
            }
        });
        // Final
        FloatingActionMenu actionMenu = new FloatingActionMenu.Builder(this)
                .addSubActionView(barActionMenu)
                .addSubActionView(hotelActionMenu)
                .addSubActionView(restauranteActionMenu)
                .addSubActionView(storeActionMenu)
                .addSubActionView(floristActionMenu)
                .attachTo(actionButton)
                .setStartAngle(100)
                .build();
        /* /FLOATING ACTION BUTTON */
    }

    public void clearButtons(View view) {
        view.setSelected(!view.isSelected());
        if (!view.equals(floristIconMenu)) {
            floristIconMenu.setSelected(false);
        }
        if (!view.equals(barIconMenu)) {
            barIconMenu.setSelected(false);
        }
        if (!view.equals(hotelIconMenu)) {
            hotelIconMenu.setSelected(false);
        }
        if (!view.equals(restauranteIconMenu)) {
            restauranteIconMenu.setSelected(false);
        }
        if (!view.equals(storeIconMenu)) {
            storeIconMenu.setSelected(false);
        }
    }

    public void showFIlterPlaces(String type) {
        if (!places.isEmpty()) {
            for (Place place : places) {
                if (place.searchType(type)) {
                    LatLng position = new LatLng(Double.parseDouble(place.getLat()), Double.parseDouble(place.getLng()));
                    Marker marker = mMap.addMarker(new MarkerOptions().position(position).title((String) place.getName()));
                    marker.setTag(place);
                    marker.setIcon(Image.getIcon(getBaseContext(), place.getType()));
                }

            }
        }
    }

    public void showAll() {
        if (!places.isEmpty()) {
            for (Place place : places) {
                LatLng position = new LatLng(Double.parseDouble(place.getLat()), Double.parseDouble(place.getLng()));
                Marker marker = mMap.addMarker(new MarkerOptions().position(position).title((String) place.getName()));
                marker.setTag(place);
                marker.setIcon(Image.getIcon(getBaseContext(), place.getType()));
            }
        }
    }

    public BitmapDescriptor bitmapDescriptorFromVector(Context context, int vectorResId) {
        Drawable vectorDrawable = ContextCompat.getDrawable(context, vectorResId);
        vectorDrawable.setBounds(0, 0, vectorDrawable.getIntrinsicWidth(), vectorDrawable.getIntrinsicHeight());
        Bitmap bitmap = Bitmap.createBitmap(vectorDrawable.getIntrinsicWidth(), vectorDrawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        vectorDrawable.draw(canvas);
        return BitmapDescriptorFactory.fromBitmap(bitmap);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        Log.d(TAG, "onRequestPermissionsResult: called.");
        mLocationPermissionsGranted = false;

        switch (requestCode) {
            case LOCATION_PERMISSION_REQUEST_CODE: {
                if (grantResults.length > 0) {
                    for (int i = 0; i < grantResults.length; i++) {
                        if (grantResults[i] != PackageManager.PERMISSION_GRANTED) {
                            mLocationPermissionsGranted = false;
                            Log.d(TAG, "onRequestPermissionsResult: permission failed");
                            return;
                        }
                    }
                    Log.d(TAG, "onRequestPermissionsResult: permission granted");
                    mLocationPermissionsGranted = true;
                    //initialize our map
                    initMap();
                }
            }
        }
    }

    public void searchOnClick(View view) {
        Intent intent = new Intent(MapActivity.this, SearchActivity.class);
        intent.putExtra("places", places);
        intent.putExtra("location", currentUserLocationMarker.getPosition());
        startActivityForResult(intent, 2);
    }

    @Override
    public boolean onMarkerClick(Marker marker) {

        Place place = (Place) marker.getTag();
        if(place == null){
            return false;
        }
        DetailPlaceBottomSheetDialog bottomSheet = new DetailPlaceBottomSheetDialog();
        Lock l = new ReentrantLock();
        l.lock();
        try {
            getSupportFragmentManager().executePendingTransactions();
            if (getSupportFragmentManager().findFragmentByTag("detailPlaceBottomSheet") == null) {
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                if(place.getId() == null){
                    return false;
                }
                if (currentUserLocationMarker == null) {
                    bottomSheet.withPlaceId(place.getId(), place.getOpennow(), 0.0, 0.0, Double.parseDouble(place.getLat()), Double.parseDouble(place.getLng()), place.getType().get(0)).show(getSupportFragmentManager(), "detailPlaceBottomSheet");
                }else{
                    bottomSheet.withPlaceId(place.getId(), place.getOpennow(), currentUserLocationMarker.getPosition().latitude, currentUserLocationMarker.getPosition().longitude, Double.parseDouble(place.getLat()), Double.parseDouble(place.getLng()), place.getType().get(0)).show(getSupportFragmentManager(), "detailPlaceBottomSheet");
                }
                transaction.commit();
            }
        } finally {
            l.unlock();
        }
        return true;
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        mMap.setMapStyle(MapStyleOptions.loadRawResourceStyle(getApplicationContext(), R.raw.map_style));
        mMap.setOnMarkerClickListener(this);

        if (mLocationPermissionsGranted) {
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                return;
            }
            lm = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
            mMap.setMyLocationEnabled(true);
            mMap.getUiSettings().setMyLocationButtonEnabled(false);

            lastPositionOfCamera = mMap.getCameraPosition().target;
            lastZoomOfCamara = mMap.getCameraPosition().zoom;
            mMap.animateCamera(CameraUpdateFactory.newLatLng(new LatLng(-12.0749168, -76.9656708)));
            mMap.setMaxZoomPreference((float) 17.2);
            mMap.setMinZoomPreference((float) 14.8);
            init();
        }
    }
}

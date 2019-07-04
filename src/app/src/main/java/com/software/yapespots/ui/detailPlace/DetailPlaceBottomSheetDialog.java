package com.software.yapespots.ui.detailPlace;

import android.arch.persistence.room.Room;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.FontRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetDialogFragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.api.ApiException;
import com.google.android.libraries.places.api.Places;
import com.google.android.libraries.places.api.model.PhotoMetadata;
import com.google.android.libraries.places.api.model.Place;
import com.google.android.libraries.places.api.net.FetchPhotoRequest;
import com.google.android.libraries.places.api.net.FetchPlaceRequest;
import com.google.android.libraries.places.api.net.PlacesClient;
import com.software.yapespots.R;
import com.software.yapespots.model.local.FavoritePlace;
import com.software.yapespots.repository.firebase.functions.FirebaseFunction;
import com.software.yapespots.repository.local.LocalDatabase;
import com.software.yapespots.ui.detailPlace.listener.LikeListener;
import com.software.yapespots.ui.detailPlace.listener.ReportListener;
import com.software.yapespots.ui.search.FirstListenerHolder;
import com.software.yapespots.ui.search.SecondListenerHolder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class DetailPlaceBottomSheetDialog extends BottomSheetDialogFragment {
    ArrayList<PhotosClass> photos;
    PhotosAdapter adapter;
    FirebaseFunction firebaseFunction = new FirebaseFunction();
    String placeId;
    Double latitudeUser;
    Double longitudeUser;
    Double latitudePlace;
    Double longitudePlace;
    Boolean opennow;
    TextView opennowStatus;
    TextView distance;
    TextView typeText;
    String type;
    ImageView opennowImage;
    ImageButton favoritePlace;
    Place actualPlace;
    Place testPlace;
    PlacesClient placesClient;
    View view;
    LocalDatabase db;

    private TextView tvPlaceName;

    public DetailPlaceBottomSheetDialog withPlaceId(String placeId, Boolean opennow, Double latUser, Double lonUser, Double latPlace, Double lonPlace, String type) {
        this.opennow = opennow;
        this.placeId = placeId;
        this.latitudeUser = latUser;
        this.longitudeUser = lonUser;
        this.latitudePlace = latPlace;
        this.longitudePlace = lonPlace;
        this.type = type;
        return this;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.detailplace_bottom_sheet_layout, container, false);
        db = LocalDatabase.getInstance(getContext());
        String Placeid = this.placeId;
        photos = new ArrayList<>();
        GetImage(view, Placeid);
        adapter = new PhotosAdapter(this.getContext(), photos);
        RecyclerView recyclerView = view.findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext(), LinearLayoutManager.HORIZONTAL, false));
        recyclerView.setAdapter(adapter);
        tvPlaceName = view.findViewById(R.id.editText);
        opennowStatus = view.findViewById(R.id.textOpenStatus);
        distance = view.findViewById(R.id.detailplace_distance);
        opennowImage = view.findViewById(R.id.openImage);
        typeText = view.findViewById(R.id.detailplace_typetext);
        setType();
        ImageButton CallPlaceButton = view.findViewById(R.id.detailplace_call_icon);
        setOpenNow();
        setDistance(getDistance(latitudePlace, longitudePlace, latitudeUser, longitudeUser));
        CallPlaceButton.setOnClickListener(v -> CallSpot(view, Placeid));

        ImageButton SharePlaceButton = view.findViewById(R.id.detailplace_share_icon);

        SharePlaceButton.setOnClickListener(v -> {
            Share(view, Placeid);
        });

        ImageButton getDirectionToPlace = view.findViewById(R.id.directionsButton);

        getDirectionToPlace.setOnClickListener(v -> Goto());

        ImageButton getViewOnGoogle = view.findViewById(R.id.detailplace_googlemaps_icon);

        getViewOnGoogle.setOnClickListener(v -> showOnGoogle(view, Placeid));

        ImageButton likePlace = view.findViewById(R.id.detailplace_like);
        likePlace.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LikeListener likeListener = new LikeListener(v.getContext());
                HashMap<String, Object> data = new HashMap<>();
                data.put("id", Placeid);
                data.put("factor", 1);
                firebaseFunction.withData(data).likePlace(likeListener);
            }
        });

        ImageButton reportPlace = view.findViewById(R.id.detailplace_report);
        reportPlace.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ReportListener reportListener = new ReportListener(v.getContext());
                HashMap<String, Object> data = new HashMap<>();
                data.put("id", Placeid);
                firebaseFunction.withData(data).reportPlace(reportListener);
            }
        });

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        loadPlace(getContext());
        favoritePlace = view.findViewById(R.id.detailplace_favorite);
        favoritePlace.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO: Implement favorites
                if (v.isSelected()) {
                    FavoritePlace favoritePlace1 = new FavoritePlace();
                    favoritePlace1.id = placeId;
                    db.getFavoritePlaceDao().delete(favoritePlace1);
                    Log.d("agregando","no");
                } else {
                    Log.d("agregando","si "+placeId);
                    FavoritePlace favoritePlace1 = new FavoritePlace();
                    favoritePlace1.id = placeId;
                    favoritePlace1.name = testPlace.getName();
                    favoritePlace1.lat = latitudePlace;
                    favoritePlace1.lng = longitudePlace;
                    favoritePlace1.opennow = opennow;
                    favoritePlace1.phone = testPlace.getPhoneNumber();
                    favoritePlace1.type = testPlace.getTypes().get(0).toString();
                    db.getFavoritePlaceDao().insertAll(favoritePlace1);
                }
                v.setSelected(!v.isSelected());
            }
        });
    }

    private void isFavoritePlace() {
        List<FavoritePlace> favorites = db.getFavoritePlaceDao().loadById(placeId);
        if (favorites.isEmpty()) {
            Log.d("isempty","si");
            favoritePlace.setSelected(false);
            return;
        }
        Log.d("isempty","no");
        favoritePlace.setSelected(true);
    }

    private void loadPlace(Context context) {
        Places.initialize(context, context.getString(R.string.google_maps_key));
        placesClient = Places.createClient(context);
        List<Place.Field> placeFields = Arrays.asList(Place.Field.ADDRESS, Place.Field.NAME, Place.Field.LAT_LNG, Place.Field.PHONE_NUMBER, Place.Field.PHOTO_METADATAS,Place.Field.TYPES);
        FetchPlaceRequest request = FetchPlaceRequest.builder(placeId, placeFields).build();
        placesClient.fetchPlace(request).addOnSuccessListener((response) -> {
            testPlace = response.getPlace();
            prepareUI(testPlace);
            isFavoritePlace();
        }).addOnFailureListener((exception) -> {
            Log.e(DetailPlaceBottomSheetDialog.class.getSimpleName(), exception.toString());
        });
    }

    private void prepareUI(Place place) {
        String placeName = place.getName();
        if (placeName != null) {
            setupInfo(placeName);
        }
    }

    private void setupInfo(String placeName) {
        if (placeName.length() > 25) {
            placeName = placeName.substring(0, 25) + "...";
        }
        tvPlaceName.setText(placeName);
    }

    public void Share(View view, String placeid) {
        Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
        sharingIntent.setType("text/plain");
        String shareBody = "Vamos a este lugar, aceptan Yape:\n" + testPlace.getName() + "\n" + testPlace.getAddress() + "\n" + "https://www.google.com/maps/search/?api=1&query=Google&query_place_id=" + placeId + "\n" + "#Yape";
        sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, shareBody);
        startActivity(Intent.createChooser(sharingIntent, "Share via"));
    }


    public void CallSpot(View view, String placeId) {
        SecondListenerHolder secondListenerHolder = new SecondListenerHolder(view.getContext(), testPlace.getPhoneNumber());
        secondListenerHolder.onClick(view);
        Log.d("Place found", testPlace.getPhoneNumber());
    }


    public void GetImage(View view, String placeId) {
        Places.initialize(view.getContext(), view.getContext().getString(R.string.google_maps_key));
        PlacesClient placesClient = Places.createClient(view.getContext());
        ImageView photoImage = view.findViewById(R.id.photo_image);
        List<Place.Field> placeFields = Arrays.asList(Place.Field.PHOTO_METADATAS);
        FetchPlaceRequest placeRequest = FetchPlaceRequest.builder(placeId, placeFields).build();
        placesClient.fetchPlace(placeRequest).addOnSuccessListener((response) -> {
            Place place = response.getPlace();
            addImage(place, placesClient);

        });
    }

    public void addImage(Place place, PlacesClient placesClient) {
        if (place == null) {
            return;
        }
        if (place.getPhotoMetadatas() == null) {

            return;
        }

        // Get the photo metadata.
        for (int i = 0; i < place.getPhotoMetadatas().size(); i++) {
            PhotoMetadata photoMetadata = place.getPhotoMetadatas().get(i);
            // Get the attribution text.
            String attributions = photoMetadata.getAttributions();

            // Create a FetchPhotoRequest.
            FetchPhotoRequest photoRequest = FetchPhotoRequest.builder(photoMetadata)
                    .setMaxWidth(500) // Optional.
                    .setMaxHeight(300) // Optional.
                    .build();
            placesClient.fetchPhoto(photoRequest).addOnSuccessListener((fetchPhotoResponse) -> {
                Bitmap bitmap = fetchPhotoResponse.getBitmap();
                PhotosClass photo = new PhotosClass(bitmap);
                photo.getResizedBitmap(120, 120);
                photos.add(photo);
                Log.d("IANNNN", bitmap.toString());
                adapter.notifyDataSetChanged();
                //photoImage.setImageBitmap(bitmap);
            }).addOnFailureListener((exception) -> {
                if (exception instanceof ApiException) {
                    ApiException apiException = (ApiException) exception;
                    int statusCode = apiException.getStatusCode();
                    // Handle error with given status code.
                    Log.d("Place not found: ", exception.getMessage());
                }
            });
        }

    }

    public void Goto() {
        FirstListenerHolder firstListenerHolder = new FirstListenerHolder(view.getContext(), String.valueOf(testPlace.getLatLng().latitude), String.valueOf(testPlace.getLatLng().longitude));
        firstListenerHolder.onClick(view);
    }


    public void showOnGoogle(View view, String placeId) {
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.google.com/maps/search/?api=1&query=Google&query_place_id=" + placeId));
        startActivity(intent);
    }

    public Boolean isAvailableOpennow() {
        if (this.opennow == null) {
            return false;
        }
        return true;
    }

    public void setOpenNow() {
        if (!isAvailableOpennow()) {
            return;
        }
        if (this.opennow) {
            opennowImage.setImageResource(R.drawable.icon_detailplace_open);
            opennowStatus.setText("Abierto");
            opennowStatus.setTextColor(Color.parseColor("#4CAF50"));
        } else if (!this.opennow) {
            opennowImage.setImageResource(R.drawable.icon_detailplace_close);
            opennowStatus.setText("Cerrado");
            opennowStatus.setTextColor(Color.parseColor("#DB3725"));
        }
    }

    public void setType() {
        typeText.setText(type);
    }

    public void setDistance(double newDistance) {
        if (this.latitudeUser == 0 && this.longitudeUser == 0) {
            distance.setText("");
            return;
        }
        String placeDistance = String.format("%.2f", newDistance) + "km";
        distance.setText(placeDistance);

    }

    double getDistance(double lat1, double lon1, double lat2, double lon2) {
        final double R = 6372.8; // In kilometers
        double dLat = Math.toRadians(lat2 - lat1);
        double dLon = Math.toRadians(lon2 - lon1);
        lat1 = Math.toRadians(lat1);
        lat2 = Math.toRadians(lat2);

        double a = Math.pow(Math.sin(dLat / 2), 2) + Math.pow(Math.sin(dLon / 2), 2) * Math.cos(lat1) * Math.cos(lat2);
        double c = 2 * Math.asin(Math.sqrt(a));
        return R * c;
    }
}

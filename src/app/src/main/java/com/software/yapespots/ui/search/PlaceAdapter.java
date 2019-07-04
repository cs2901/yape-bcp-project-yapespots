package com.software.yapespots.ui.search;

import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.api.ApiException;
import com.google.android.libraries.places.api.Places;
import com.google.android.libraries.places.api.model.Place;
import com.google.android.libraries.places.api.net.FetchPlaceRequest;
import com.google.android.libraries.places.api.net.PlacesClient;
import com.software.yapespots.R;
import com.software.yapespots.ui.map.MapActivity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PlaceAdapter extends RecyclerView.Adapter<PlaceAdapter.ViewHolder> {
    private ArrayList<com.software.yapespots.model.Place> myPlaces;
    private Context thisContext;

    public PlaceAdapter(ArrayList<com.software.yapespots.model.Place> places) {
        myPlaces = places;
    }

    @NonNull
    @Override
    public PlaceAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        thisContext = viewGroup.getContext();
        return new ViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.placeviewer, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull PlaceAdapter.ViewHolder viewHolder, int position) {
        String placeName = myPlaces.get(position).getName();
        if (placeName.length() > 21) {
            placeName = placeName.substring(0, 21) + "...";
        }
        viewHolder.name.setText(placeName);
        FirstListenerHolder firstListenerHolder = new FirstListenerHolder(thisContext, myPlaces.get(position).getLat(), myPlaces.get(position).getLng());
        viewHolder.directions.setOnClickListener(firstListenerHolder);
        setPhone(myPlaces.get(position).getId(), viewHolder, viewHolder.getView());
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(thisContext, MapActivity.class);
                intent.putExtra("placeID", myPlaces.get(position).getId());
                intent.putExtra("placeName", myPlaces.get(position).getName());
                intent.putExtra("placeOpenNow", myPlaces.get(position).getOpennow());
                intent.putExtra("placeLat", myPlaces.get(position).getLat());
                intent.putExtra("placeLng", myPlaces.get(position).getLng());
                intent.putExtra("placeType", myPlaces.get(position).getType());
                intent.putExtra("logged", true);
                thisContext.startActivity(intent);
            }
        });
    }

    void setPhone(String placeId, ViewHolder viewHolder, View convertView) {
        Places.initialize(thisContext, thisContext.getString(R.string.google_maps_key));
        PlacesClient placesClient = Places.createClient(thisContext);
        List<Place.Field> placeFields = Arrays.asList(Place.Field.PHONE_NUMBER);
        FetchPlaceRequest request = FetchPlaceRequest.builder(placeId, placeFields).build();
        placesClient.fetchPlace(request).addOnSuccessListener((response) -> {
            Place place = response.getPlace();
            viewHolder.call = (ImageButton) convertView.findViewById(R.id.call);
            SecondListenerHolder secondListenerHolder = new SecondListenerHolder(thisContext, place.getPhoneNumber());
            viewHolder.call.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (place.getPhoneNumber() == null) {
                        Toast.makeText(thisContext, "Este Spot no cuenta con número telefónico", Toast.LENGTH_SHORT).show();
                        return;
                    } else {
                        secondListenerHolder.onClick(convertView);
                    }
                }
            });
        }).addOnFailureListener((exception) -> {
            if (exception instanceof ApiException) {
                ApiException apiException = (ApiException) exception;
                int statusCode = apiException.getStatusCode();
            }
        });
    }

    @Override
    public int getItemCount() {
        return myPlaces.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView name;
        private ImageButton call;
        private ImageButton directions;
        View thisView;

        public ViewHolder(View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.textView4);
            call = itemView.findViewById(R.id.call);
            directions = itemView.findViewById(R.id.directions);
            thisView = itemView;
        }

        public View getView() {
            return thisView;
        }
    }
}
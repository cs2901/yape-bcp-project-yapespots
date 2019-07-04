package com.software.yapespots.ui.map;

import android.location.Location;
import android.support.annotation.NonNull;
import android.util.Log;
import android.widget.Toast;

//import androidx.annotation.NonNull;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.software.yapespots.R;

public class CompleteListener implements OnCompleteListener {

    public MapActivity mapview;

    public CompleteListener(MapActivity view){
        mapview = view;
    }

    @Override
    public void onComplete(@NonNull Task task) {
        if (task.isSuccessful()) {
            Log.d(mapview.TAG, "onComplete: found location!");
            Location currentLocation = (Location) task.getResult();
            if(currentLocation!=null){
                if (mapview.currentUserLocationMarker != null)
                {
                    mapview.currentUserLocationMarker.remove();
                }
                LatLng latLng = new LatLng(currentLocation.getLatitude(), currentLocation.getLongitude());

                MarkerOptions markerOptions = new MarkerOptions();
                markerOptions.position(latLng);
                markerOptions.title("Your Location");
                markerOptions.icon(mapview.bitmapDescriptorFromVector(mapview, R.drawable.marcador));
                mapview.currentUserLocationMarker = mapview.mMap.addMarker(markerOptions);



                CameraPosition newCamPos = new CameraPosition(latLng,15,mapview.mMap.getCameraPosition().tilt,mapview.mMap.getCameraPosition().bearing);

                //mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
                mapview.mMap.animateCamera(CameraUpdateFactory.newCameraPosition(newCamPos),1000,null);
                //moveCamera(new LatLng(currentLocation.getLatitude(), currentLocation.getLongitude()), DEFAULT_ZOOM, "My Location");
            }
            else{
                Toast.makeText(mapview, "Localizando tu ubicación...", Toast.LENGTH_SHORT).show();
            }
        } else {
            Log.d(mapview.TAG, "onComplete: current location is null");
            Toast.makeText(mapview, "unable to get current location", Toast.LENGTH_SHORT).show();
        }
    }
}

package com.software.yapespots.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;

import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.software.yapespots.R;

import java.util.ArrayList;

public class Image {
    /* Types */
    private static String lodging = "lodging";
    private static String florist = "florist";
    private static String gas_station = "gas_station";
    private static String store = "store";
    private static String hardware_store = "hardware_store";
    private static String museum = "museum";
    private static String bar = "bar";
    private static String restaurant = "restaurant";

    private static BitmapDescriptor bitmapDescriptorFromVector(Context context, int vectorResId) {
        Drawable vectorDrawable = ContextCompat.getDrawable(context, vectorResId);
        vectorDrawable.setBounds(0, 0, vectorDrawable.getIntrinsicWidth(), vectorDrawable.getIntrinsicHeight());
        Bitmap bitmap = Bitmap.createBitmap(vectorDrawable.getIntrinsicWidth(), vectorDrawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        vectorDrawable.draw(canvas);
        return BitmapDescriptorFactory.fromBitmap(bitmap);
    }
    public static String getImageType (ArrayList<String> types){
        String type = "any";
        for(int i=0; i<types.size();i++){
            if(restaurant.equals(types.get(i))){
                type = restaurant;
                break;
            }
            if(lodging.equals(types.get(i))){
                type = lodging;
                break;
            }
            if(gas_station.equals(types.get(i))){
                type = gas_station;
                break;
            }
            if(museum.equals(types.get(i))){
                type = museum;
                break;
            }
            if(hardware_store.equals(types.get(i))){
                type = hardware_store;
                break;
            }
            if(florist.equals(types.get(i))){
                type = florist;
                break;
            }
            if(store.equals(types.get(i))){
                type = store;
                break;
            }
        }
        return type;
    }
    public static BitmapDescriptor getIcon(Context context, ArrayList<String> types) {
        String principalType = getImageType(types);

        if (principalType.equalsIgnoreCase(restaurant)) {
            return bitmapDescriptorFromVector(context, R.drawable.pin_restaurant);
        } else if (principalType.equalsIgnoreCase(lodging)) {
            return bitmapDescriptorFromVector(context, R.drawable.pin_hotel);
        } else if (principalType.equalsIgnoreCase(bar)) {
            return bitmapDescriptorFromVector(context, R.drawable.pin_bar);
        } else if (principalType.equalsIgnoreCase(gas_station)) {
            return bitmapDescriptorFromVector(context, R.drawable.pin_gas_station);
        } else if (principalType.equalsIgnoreCase(museum)) {
            return bitmapDescriptorFromVector(context, R.drawable.pin_museum);
        } else if (principalType.equalsIgnoreCase(hardware_store)) {
            return bitmapDescriptorFromVector(context, R.drawable.pin_hardware_store);
        } else if (principalType.equalsIgnoreCase(florist)) {
            return bitmapDescriptorFromVector(context, R.drawable.pin_florist);
        }else if (principalType.equalsIgnoreCase(store)) {
            return bitmapDescriptorFromVector(context, R.drawable.pin_store);
        } else {
            return bitmapDescriptorFromVector(context, R.drawable.pin_anyplace);
        }
    }

}

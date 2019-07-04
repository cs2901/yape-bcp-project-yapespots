package com.software.yapespots.model.local;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

@Entity
public class FavoritePlace {
    public static String DATABASE_NAME = "favorites-place";

    @PrimaryKey
    @NonNull
    public String id;
    public Double lat;
    public Double lng;
    public String name;
    public Boolean opennow;
    public String phone;
    public String type;
}

package com.software.yapespots.repository.local;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import com.software.yapespots.model.local.FavoritePlace;

@Database(entities = {FavoritePlace.class}, version = 1)
public abstract class LocalDatabase extends RoomDatabase {
    static LocalDatabase database;

    public static LocalDatabase getInstance(Context context) {
        if (database != null) {
            return database;
        }

        database = Room.databaseBuilder(context, LocalDatabase.class, FavoritePlace.DATABASE_NAME).allowMainThreadQueries().build();
        return database;
    }

    public abstract FavoritePlaceDao getFavoritePlaceDao();
}

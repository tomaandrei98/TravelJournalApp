package com.example.traveljournal.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;


import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {Trip.class}, version = 2)

public abstract class TripRoomDatabase extends RoomDatabase {
    public abstract TripDao tripDao();

    private static volatile TripRoomDatabase INSTANCE;

    static TripRoomDatabase getInstance(Context context) {
        if (INSTANCE == null) {
            synchronized (TripRoomDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            TripRoomDatabase.class,
                            "journal")
                            .allowMainThreadQueries()
                            .build();
                }
            }
        }
        return INSTANCE;
    }

    static final ExecutorService databaseExecutor = Executors.newFixedThreadPool(4);
}

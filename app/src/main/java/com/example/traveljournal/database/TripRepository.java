package com.example.traveljournal.database;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;

public class TripRepository {
    private final TripDao tripDao;

    public TripRepository(Application application) {
        TripRoomDatabase tripRoomDatabase = TripRoomDatabase.getInstance(application);
        tripDao = tripRoomDatabase.tripDao();
    }

    public LiveData<List<Trip>> getAllTrips() {
        return tripDao.getTrips();
    }

    public void insert(Trip trip) {
        TripRoomDatabase.databaseExecutor.execute(() -> {
            tripDao.insert(trip);
        });
    }

    public void update(Trip trip) {
        TripRoomDatabase.databaseExecutor.execute(() -> {
            tripDao.update(trip);
        });
    }

    public Trip findTripByName(String name) {
        return tripDao.findTripByName(name);
    }

    public void delete(Trip trip) {
        tripDao.delete(trip);
    }
}

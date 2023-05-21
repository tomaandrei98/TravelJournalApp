package com.example.traveljournal.database;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class TripViewModel extends AndroidViewModel {
    private final TripRepository tripRepository;

    public TripViewModel(@NonNull Application application) {
        super(application);
        tripRepository = new TripRepository(application);
    }

    public void insert(Trip trip) {
        tripRepository.insert(trip);
    }

    public LiveData<List<Trip>> getAllTrips() {
        return tripRepository.getAllTrips();
    }

    public void update(Trip trip) {
        Trip tripByName = findTripByName(trip.getName());
        if (tripByName != null) {
            tripRepository.update(trip);
        } else {
            tripRepository.insert(trip);
        }
    }

    public Trip findTripByName(String name) {
        return tripRepository.findTripByName(name);
    }

    public void delete(Trip trip) {
        tripRepository.delete(trip);
    }
}

package com.example.traveljournal.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface TripDao {
    @Insert
    void insert(Trip trip);

    @Update
    void update(Trip trip);

    @Query(value = "SELECT * FROM trips")
    LiveData<List<Trip>> getTrips();

    @Query("SELECT * FROM trips WHERE name = :name")
    Trip findTripByName(String name);

    @Delete
    void delete(Trip trip);
}

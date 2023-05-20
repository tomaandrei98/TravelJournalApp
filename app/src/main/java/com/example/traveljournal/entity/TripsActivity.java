package com.example.traveljournal.entity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.traveljournal.ClickListener;
import com.example.traveljournal.R;
import com.example.traveljournal.RecyclerTouchListener;
import com.example.traveljournal.TripsAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class TripsActivity extends AppCompatActivity {
    private List<Trip> trips = new ArrayList<>();
    private RecyclerView recyclerViewTrips;
    private FloatingActionButton floatingActionButton;
    private TripViewModel tripViewModel;
    private TripsAdapter adapter = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.trips_activity);

        tripViewModel = new ViewModelProvider(this).get(TripViewModel.class);
        adapter = new TripsAdapter(trips);

        Objects.requireNonNull(getSupportActionBar()).setTitle("Recent trips");
        recyclerViewTrips = findViewById(R.id.recyclerViewTrips);


//        populateTrips();
        setupRecyclerView();
        handleRecyclerViewItemClick();

        floatingActionButton = findViewById(R.id.add_trip_fab);
        floatingActionButton.setOnClickListener(
                view -> {
                    Intent intent = new Intent(TripsActivity.this, AddTripActivity.class);
                    startActivity(intent);
                });

        tripViewModel.getAllTrips().observe(this,
                trips -> {
                    TripsActivity.this.trips = trips;
                    recyclerViewTrips.setAdapter(
                            new TripsAdapter(trips));
                });
    }

    private void handleRecyclerViewItemClick() {
        recyclerViewTrips.addOnItemTouchListener(new RecyclerTouchListener(
                this,
                recyclerViewTrips,
                new ClickListener() {
                    @Override
                    public void onClick(View view, int position) {
                        Toast.makeText(TripsActivity.this, "Single Click on position:" + position,
                                Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onLongClick(View view, int position) {
                        Toast.makeText(TripsActivity.this, "Long press on position:" + position,
                                Toast.LENGTH_LONG).show();

                        Intent intent = new Intent(TripsActivity.this, AddTripActivity.class);
                        intent.putExtra("tripName", trips.get(position).getName());
                        intent.putExtra("destination", trips.get(position).getDestination());
                        intent.putExtra("price", trips.get(position).getPrice());
                        startActivity(intent);
                    }
                }));
    }

    @Override
    protected void onResume() {
//        createTrip();
        super.onResume();
    }

    private void setupRecyclerView() {
        recyclerViewTrips.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewTrips.setAdapter(adapter);
    }

/*
    private void createTrip() {
        String tripName = null;
        String destination = null;
        Float price = null;
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            tripName = bundle.getString("tripName");
            destination = bundle.getString("destination");
            price = bundle.getFloat("price");
        }
        if (tripName != null && destination != null && price != 0) {
            trips.add(new Trip(tripName, destination, price));
        }
    }
*/
/*
    private void populateTrips() {
        trips = new ArrayList<>();
        trips.add(new Trip("https://cdn-icons-png.flaticon.com/512/4963/4963211.png",
                "My fav trip",
                "Budapest",
                250.0f,
                false));
        trips.add(new Trip("https://cdn-icons-png.flaticon.com/512/444/444805.png",
                "Vamooss!!",
                "Barcelona",
                400.0f,
                false));
        trips.add(new Trip("https://cdn-icons-png.flaticon.com/512/5972/5972023.png",
                "aliquam non",
                "Madrid",
                450.0f,
                false));
        trips.add(new Trip("https://cdn-icons-png.flaticon.com/512/4507/4507323.png",
                "quam suspendisse",
                "Saint Petersburg",
                350.0f,
                false));
        trips.add(new Trip("https://cdn-icons-png.flaticon.com/512/10726/10726549.png",
                "ultrices vel",
                "Mexico",
                1650.0f,
                false));
        trips.add(new Trip("https://cdn-icons-png.flaticon.com/512/2147/2147102.png",
                "erat nulla",
                "Dublin",
                850.0f,
                false));
        trips.add(new Trip("https://cdn-icons-png.flaticon.com/512/774/774279.png",
                "tempor convallis",
                "Seattle",
                1550.0f,
                false));
        trips.add(new Trip("https://cdn-icons-png.flaticon.com/512/7666/7666061.png",
                "medium place",
                "Iasi",
                100.0f,
                false));
        trips.add(new Trip("https://cdn-icons-png.flaticon.com/512/1282/1282527.png",
                "above average",
                "Vienna",
                230.0f,
                false));
        trips.add(new Trip("https://cdn-icons-png.flaticon.com/512/6312/6312229.png",
                "nice place",
                "Prague",
                280.0f,
                false));
        trips.add(new Trip("https://cdn-icons-png.flaticon.com/512/2147/2147002.png",
                "tres bien",
                "Paris",
                400.0f,
                false));
    }
*/
}

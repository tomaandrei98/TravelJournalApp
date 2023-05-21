package com.example.traveljournal.database;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.ItemTouchHelper;
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

        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(callBackMethod);
        itemTouchHelper.attachToRecyclerView(recyclerViewTrips);

    }

    ItemTouchHelper.SimpleCallback callBackMethod = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.RIGHT) {
        @Override
        public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
            return false;
        }

        @Override
        public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
            int position = viewHolder.getAdapterPosition();
            if (direction == ItemTouchHelper.RIGHT) {
                tripViewModel.delete(trips.get(position));
                recyclerViewTrips.getAdapter().notifyItemRemoved(position);
            }
        }
    };

    private void handleRecyclerViewItemClick() {
        recyclerViewTrips.addOnItemTouchListener(new RecyclerTouchListener(
                this,
                recyclerViewTrips,
                new ClickListener() {
                    @Override
                    public void onClick(View view, int position) {
//                        Toast.makeText(TripsActivity.this, "Single Click on position:" + position,
//                                Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onLongClick(View view, int position) {
                        Intent intent = new Intent(TripsActivity.this, AddTripActivity.class);
                        intent.putExtra("tripName", trips.get(position).getName());
                        intent.putExtra("destination", trips.get(position).getDestination());
                        intent.putExtra("price", trips.get(position).getPrice());
                        startActivity(intent);
                    }
                }));
    }

    private void setupRecyclerView() {
        recyclerViewTrips.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewTrips.setAdapter(adapter);
    }
}

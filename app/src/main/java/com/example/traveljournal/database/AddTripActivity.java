package com.example.traveljournal.database;

import static com.example.traveljournal.utils.Utils.formatPrice;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.traveljournal.R;
import com.google.android.material.slider.Slider;

import java.util.Calendar;
import java.util.Objects;

public class AddTripActivity extends AppCompatActivity {
    private TextView sliderValue;
    private Slider slider;
    private Button pickStartingDateButton;
    private TextView selectedStartingDateTextView;
    private Button pickEndingDateButton;
    private TextView selectedEndingDateTextView;
    private Button saveTripButton;
    private EditText editTextTripName;
    private EditText editTextDestination;
    private TripViewModel tripViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_trip);

        Objects.requireNonNull(getSupportActionBar()).setTitle("Add or Edit a Trip");
        tripViewModel = new ViewModelProvider(this).get(TripViewModel.class);

        editTextTripName = findViewById(R.id.editTextTripName);
        editTextDestination = findViewById(R.id.editTextDestination);
        sliderValue = findViewById(R.id.sliderValue);
        pickStartingDateButton = findViewById(R.id.pickStartDateButton);
        selectedStartingDateTextView = findViewById(R.id.startDateTextView);
        pickEndingDateButton = findViewById(R.id.pickEndDateButton);
        selectedEndingDateTextView = findViewById(R.id.endDateTextView);
        slider = findViewById(R.id.slider);
        saveTripButton = findViewById(R.id.saveTripButton);

        slider.addOnChangeListener((slider, value, fromUser) -> sliderValue.setText(
                formatPrice(String.valueOf(Math.round(value))).concat(" eur")));

        pickStartingDateButton.setOnClickListener(view -> {
            final Calendar c = Calendar.getInstance();

            int year = c.get(Calendar.YEAR);
            int month = c.get(Calendar.MONTH);
            int day = c.get(Calendar.DAY_OF_MONTH);

            DatePickerDialog datePickerDialog = new DatePickerDialog(
                    AddTripActivity.this,
                    (view1, year1, monthOfYear, dayOfMonth) -> {
                        selectedStartingDateTextView
                                .setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year1);
                    },
                    year, month, day);
            datePickerDialog.show();
        });

        pickEndingDateButton.setOnClickListener(view -> {
            final Calendar c = Calendar.getInstance();

            int year = c.get(Calendar.YEAR);
            int month = c.get(Calendar.MONTH);
            int day = c.get(Calendar.DAY_OF_MONTH);

            DatePickerDialog datePickerDialog = new DatePickerDialog(
                    AddTripActivity.this,
                    (view1, year1, monthOfYear, dayOfMonth) -> {
                        selectedEndingDateTextView
                                .setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year1);
                    },
                    year, month, day);
            datePickerDialog.show();
        });

        saveTripButton.setOnClickListener(view -> {
            Intent intent = new Intent(AddTripActivity.this, TripsActivity.class);
//            intent.putExtra("tripName", editTextTripName.getText().toString());
//            intent.putExtra("destination", editTextDestination.getText().toString());
//            intent.putExtra("price", slider.getValue());

            Trip trip = new Trip(editTextTripName.getText().toString(),
                    editTextDestination.getText().toString(),
                    Integer.parseInt(String.valueOf(Math.round(slider.getValue()))));
            tripViewModel.update(trip);

            startActivity(intent);
        });
    }

    @Override
    protected void onResume() {
        populateFieldsForUpdateTrip();
        super.onResume();
    }

    private void populateFieldsForUpdateTrip() {
        String tripName = null;
        String destination = null;
        int price = 0;
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            tripName = bundle.getString("tripName");
            destination = bundle.getString("destination");
            price = Math.round(bundle.getInt("price"));
        }
        editTextTripName.setText(tripName);
        editTextDestination.setText(destination);
        slider.setValue(Float.parseFloat(String.valueOf(price)));
        sliderValue.setText(formatPrice(String.valueOf(price)).concat(" eur"));
    }
}
package com.example.traveljournal;

import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.traveljournal.entity.Trip;

import java.util.List;

public class TripsViewHolder extends RecyclerView.ViewHolder {
    private List<Trip> trips;
    private final ImageView imageViewTrip;
    private final ConstraintLayout itemTrip;
    private final TextView textViewTripName;
    private final TextView textViewTripDestination;
    private final TextView textViewTripPrice;
    private final TextView textViewTripCurrency;
    private final CheckBox checkBoxTripFavorite;

    public TripsViewHolder(@NonNull View itemView, List<Trip> trips) {
        super(itemView);
        this.trips = trips;
        imageViewTrip = itemView.findViewById(R.id.imageViewTrip);
        itemTrip = itemView.findViewById(R.id.itemTrip);
        textViewTripName = itemView.findViewById(R.id.textViewTripName);
        textViewTripDestination = itemView.findViewById(R.id.textViewTripDestination);
        textViewTripPrice = itemView.findViewById(R.id.textViewTripPrice);
        textViewTripCurrency = itemView.findViewById(R.id.textViewTripCurrency);
        checkBoxTripFavorite = itemView.findViewById(R.id.checkBoxTripFavorite);
    }

    public ImageView getImageViewTrip() {
        return imageViewTrip;
    }

    public TextView getTextViewTripName() {
        return textViewTripName;
    }

    public TextView getTextViewTripDestination() {
        return textViewTripDestination;
    }

    public TextView getTextViewTripPrice() {
        return textViewTripPrice;
    }

    public CheckBox getCheckBoxTripFavorite() {
        return checkBoxTripFavorite;
    }

    public TextView getTextViewTripCurrency() {
        return textViewTripCurrency;
    }

    public ConstraintLayout getItemTrip() {
        return itemTrip;
    }
}

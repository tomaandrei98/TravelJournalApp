package com.example.traveljournal;

import static com.example.traveljournal.utils.Utils.formatPrice;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.traveljournal.database.Trip;
import com.squareup.picasso.Picasso;

import java.util.List;

public class TripsAdapter extends RecyclerView.Adapter<TripsViewHolder> {
    private List<Trip> trips;

    public TripsAdapter() {
    }

    public TripsAdapter(List<Trip> trips) {
        this.trips = trips;
    }

    @NonNull
    @Override
    public TripsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView =
                LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.trip_item, parent, false);
        return new TripsViewHolder(itemView, trips);
    }

    @Override
    public void onBindViewHolder(@NonNull TripsViewHolder holder, int position) {
        Trip currentTrip = trips.get(position);
        holder.getTextViewTripName().setText(currentTrip.getName());
        holder.getTextViewTripDestination().setText(currentTrip.getDestination());
        holder.getTextViewTripPrice().setText(formatPrice(String.valueOf(currentTrip.getPrice())));
        holder.getTextViewTripCurrency().setText(R.string.currency);
        holder.getItemTrip().setBackgroundResource(R.color.white);
        if (currentTrip.getPictureUrl() != null && currentTrip.getPictureUrl().length() > 0) {
            Picasso.get().load(currentTrip.getPictureUrl())
//                    .placeholder(R.drawable.android)
                    .error(R.drawable.close)
                    .into(holder.getImageViewTrip());
        }
    }



    @Override
    public int getItemCount() {
        return trips.size();
    }

    public List<Trip> getTrips() {
        return trips;
    }

    public void setTrips(List<Trip> trips) {
        this.trips = trips;
    }
}

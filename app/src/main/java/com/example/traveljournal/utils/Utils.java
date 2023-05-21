package com.example.traveljournal.utils;

import androidx.annotation.NonNull;

import com.example.traveljournal.database.Trip;

public class Utils {
    @NonNull
    public static String formatPrice(String price) {
        return (price.length() > 3) ? price.substring(0, 1).concat(",").concat(price.substring(1)) : price;
    }
}

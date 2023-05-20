package com.example.traveljournal.entity;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import org.jetbrains.annotations.NotNull;

@Entity(tableName = "trips")
public class Trip {
    @PrimaryKey
    @NotNull
    private String name;
    @ColumnInfo(name = "picture_url")
    private String pictureUrl;
    private String destination;
    private Integer price;
    private boolean favourite;

    public Trip(@NonNull String name, String destination, Integer price) {
        pictureUrl = "https://cdn-icons-png.flaticon.com/512/4507/4507323.png";
        favourite = false;
        this.name = name;
        this.destination = destination;
        this.price = price;
    }

    @Ignore
    public Trip(String pictureUrl, @NonNull String name, String destination, Integer price, boolean favourite) {
        this.pictureUrl = pictureUrl;
        this.name = name;
        this.destination = destination;
        this.price = price;
        this.favourite = favourite;
    }

    public String getPictureUrl() {
        return pictureUrl;
    }

    public void setPictureUrl(String pictureUrl) {
        this.pictureUrl = pictureUrl;
    }

    @NonNull
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public boolean isFavourite() {
        return favourite;
    }

    public void setFavourite(boolean favourite) {
        this.favourite = favourite;
    }

    @NonNull
    @Override
    public String toString() {
        return "Trip{" +
                "picture='" + pictureUrl + '\'' +
                ", name='" + name + '\'' +
                ", destination='" + destination + '\'' +
                ", price=" + price +
                ", favourite=" + favourite +
                '}';
    }
}

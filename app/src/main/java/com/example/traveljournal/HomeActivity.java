package com.example.traveljournal;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import com.example.traveljournal.database.TripsActivity;
import com.google.android.material.navigation.NavigationView;

public class HomeActivity extends AppCompatActivity {
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle actionBarDrawerToggle;
    private NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        getSupportActionBar().setTitle("Travel Journal App");

        drawerLayout = findViewById(R.id.my_drawer_layout);
        actionBarDrawerToggle =
                new ActionBarDrawerToggle(
                        this,
                        drawerLayout,
                        R.string.nav_open,
                        R.string.nav_close);

        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        navigationView = findViewById(R.id.nav_view);
        navigationView.getMenu()
                .findItem(R.id.nav_home)
                .setOnMenuItemClickListener(menuItem -> {
                    if (menuItem.getTitle().equals("Home")) {
                        Intent intent = new Intent(HomeActivity.this, TripsActivity.class);
                        startActivity(intent);
                    }
                    return true;
                });
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (actionBarDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
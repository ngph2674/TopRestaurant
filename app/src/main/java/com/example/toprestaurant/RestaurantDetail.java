package com.example.toprestaurant;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class RestaurantDetail extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurantdetail);

        if (getIntent().hasExtra(getString(R.string.restaurantName))) {
            TextView resName = findViewById(R.id.restaurantName);
            resName.setText(getIntent().getStringExtra(getString(R.string.restaurantName)));
        }
        if (getIntent().hasExtra(getString(R.string.restaurantLocation))) {
            TextView resAddress = findViewById(R.id.address);
            resAddress.setText(getIntent().getStringExtra(getString(R.string.restaurantLocation)));
        }

        if (getIntent().hasExtra(getString(R.string.restaurantRating))) {
            TextView resRating = findViewById(R.id.resRate);
            resRating.setText(getIntent().getStringExtra(getString(R.string.restaurantRating)));
        }
    }
}
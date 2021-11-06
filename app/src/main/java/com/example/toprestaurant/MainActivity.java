package com.example.toprestaurant;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView edtText = (TextView) findViewById(R.id.cityName);
        ImageButton btnSearch = findViewById(R.id.imageButton);
        btnSearch.setOnClickListener(v -> {
            String city = edtText.getText().toString().trim();
            if (!city.isEmpty()) {
                Intent intent = new Intent(MainActivity.this, RestaurantActivity.class);
                intent.putExtra(getString(R.string.city), city);
                startActivity(intent);
            } else {
                Toast.makeText(MainActivity.this, "Please Enter A City", Toast.LENGTH_SHORT).show();
            }
        });
    }
}

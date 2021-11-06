package com.example.toprestaurant;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class RestaurantAdapter extends ArrayAdapter<Restaurant> {

    public RestaurantAdapter(@NonNull Context context, ArrayList<Restaurant> restaurantsList) {
        super(context,0, restaurantsList);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.list_item, parent, false);
        }
        Restaurant currentRestaurant = getItem(position);

        TextView name = (TextView) listItemView.findViewById(R.id.name);
        name.setText(currentRestaurant.getName());

        TextView location = (TextView) listItemView.findViewById(R.id.location);
        location.setText(currentRestaurant.getLocation());

        TextView rating = (TextView) listItemView.findViewById(R.id.rating);
        rating.setText(String.valueOf(currentRestaurant.getRating()));

        return listItemView;
    }
}

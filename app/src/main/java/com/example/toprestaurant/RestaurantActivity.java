package com.example.toprestaurant;

import androidx.appcompat.app.AppCompatActivity;

import android.app.LoaderManager;

import android.content.Context;
import android.content.Intent;
import android.content.Loader;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class RestaurantActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<List<Restaurant>> {

    private static final String REQUEST_URL = "https://maps.googleapis.com/maps/api/place/textsearch/json?";
    private static String cityName = "";
    private static final int RESTAURANT_LOADER_ID = 1;
    private RestaurantAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant);

        if (getIntent().hasExtra(getString(R.string.city))) {
            cityName = getIntent().getStringExtra(getString(R.string.city));
        }
        ListView restaurantListView = (ListView) findViewById(R.id.list);

        TextView mEmptyStateTextView = (TextView) findViewById(R.id.empty_view);
        restaurantListView.setEmptyView(mEmptyStateTextView);

        mAdapter = new RestaurantAdapter(this, new ArrayList<>());
        restaurantListView.setAdapter(mAdapter);

        restaurantListView.setOnItemClickListener((adapterView, view, position, l) -> {
            Restaurant currentRestaurant = mAdapter.getItem(position);
            Intent intent = new Intent(RestaurantActivity.this, RestaurantDetail.class);
            intent.putExtra(getString(R.string.restaurantName), currentRestaurant.getName());
            intent.putExtra(getString(R.string.restaurantLocation), currentRestaurant.getLocation());
            intent.putExtra(getString(R.string.restaurantRating), currentRestaurant.getRating().toString());
            startActivity(intent);
        });

        ConnectivityManager connMgr = (ConnectivityManager)
                getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();

        if (networkInfo != null && networkInfo.isConnected()) {
            LoaderManager loaderManager = getLoaderManager();
            loaderManager.initLoader(RESTAURANT_LOADER_ID, null, this);
        } else {
            View loadingIndicator = findViewById(R.id.loading_indicator);
            loadingIndicator.setVisibility(View.GONE);
            mEmptyStateTextView.setText(R.string.no_internet_connection);
        }
    }

    @Override
    public Loader<List<Restaurant>> onCreateLoader(int i, Bundle bundle) {

        Uri base = Uri.parse(REQUEST_URL);
        Uri.Builder uriBuilder = base.buildUpon();
        uriBuilder.appendQueryParameter(getString(R.string.query), getString(R.string.restaurant) + cityName);
        String keyapi = getString(R.string.mykey);
        uriBuilder.appendQueryParameter(getString(R.string.key), keyapi);
        return new RestaurantLoader(this, uriBuilder.toString());
    }

    @Override
    public void onLoadFinished(Loader<List<Restaurant>> loader, List<Restaurant> restaurants) {

        mAdapter.clear();
        View loadingIndicator = findViewById(R.id.loading_indicator);
        loadingIndicator.setVisibility(View.GONE);
        if (restaurants != null && !restaurants.isEmpty()) {
            mAdapter.addAll(restaurants);
        }
    }

    @Override
    public void onLoaderReset(Loader<List<Restaurant>> loader) {
        mAdapter.clear();
    }
}
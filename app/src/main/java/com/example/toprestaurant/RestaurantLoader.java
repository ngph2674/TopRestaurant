package com.example.toprestaurant;

import android.content.Context;

import java.util.List;

import android.content.AsyncTaskLoader;

public class RestaurantLoader extends AsyncTaskLoader<List<Restaurant>> {

    private final String mUrl;

    public RestaurantLoader(Context context, String url) {
        super(context);
        mUrl = url;
    }

    @Override
    protected void onStartLoading() {
        forceLoad();
    }

    @Override
    public List<Restaurant> loadInBackground() {
        if (mUrl == null) {
            return null;
        }
        return QueryUtils.fetchRestaurantData(mUrl);
    }
}
